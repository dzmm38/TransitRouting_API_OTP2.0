package controller;

import model.POJO.*;
import model.Routemodel.Location;
import model.Routemodel.Plan;
import model.Routemodel.Route;
import model.Routemodel.Stop;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;

/**
 * Class to build a ConnectionPlan which then can be saved as a JSON File.
 * The Class builds a ConnectionPlan with some Routes along its legs and stops which contains all important Informations
 * given by the POJO Root Class (JSON Object given from OTP)
 * <p>
 * Needs the calculated Response POJO class, a zoneId and the simulation start time to construct a ConnectionPlan
 */
public class OTPResponseHandler {
    //------------------------------------------ Variable -------------------------------------------//
    private Root OtpPojoRoute;                      //The POJO class of the JSON response Object from the OTP Server
    private Plan ConnectionPlan;                    //Class which represents the own Route Model
    private ZoneId zoneId;
    private LocalDateTime startTime;
    private TimeController calculator;              //to calculate the Ticks of the given times and other time related calculations
    private ZoneOffset offset;                      //time difference between the given zone and the UTC (Coordinated Universal Time)
    private int routeCounter = -1;                  //Counter which is used to get the right route from the ConnectionPlan, to build the rest of the model

    //----------------------------------------- Constructor -----------------------------------------//

    /**
     * Constructor for the OTPResponseHandler class
     *
     * @param OtpPojoRoute Pojo Class which is needed to extract the information to the Routemodell
     * @param zoneId       Zone Id which defines the Zone in which the Routing was done
     * @param startTime    the time at which the simulation starts
     */
    public OTPResponseHandler(Root OtpPojoRoute, ZoneId zoneId, LocalDateTime startTime) {
        this.OtpPojoRoute = OtpPojoRoute;
        this.zoneId = zoneId;
        this.startTime = startTime;

        calculator = new TimeController();
    }

    //------------------------------------------- Methods -------------------------------------------//

    /**
     * Builds the ConnectionPlan an returns it, then can be saved as a JSON file
     *
     * @return Plan for the given POJO Response of the JSON object from OpenTripPlanner
     */
    public Plan build() {
        DebugOutput debugOutput;
        RequestParameters requestParameters;
        ElevationMetadata elevationMetadata;

        debugOutput = OtpPojoRoute.getDebugOutput();                //gets the debugOutput from the Pojo class for the ConnectionPlan (information could be helpful for Benchmarks)
        requestParameters = OtpPojoRoute.getRequestParameters();    //gets the requestParameters from the Pojo class for the ConnectionPlan (helpful to analyse the Request Settings)
        elevationMetadata = OtpPojoRoute.getElevationMetadata();    //gets the elevationData from the Pojo class from the OTP Response

        ConnectionPlan = new Plan(debugOutput, requestParameters, elevationMetadata);

        buildRoute();           //creates and builds the ConnectionPlan als well as the associated Routes / Legs and Stops
        deleteDoubleStops();    //checks the stops list and delete doubles

        return ConnectionPlan;
    }

    /**
     * Build and creates the base concept of a route class, the Route itself without any legs or stops
     * The criteria to differ between routes are set here
     */
    private void buildRoute() {
        offset = zoneId.getRules().getOffset(startTime);        //sets the offset of the given zone

        //for every itinerary of the OpenTripPlanner response create a route
        for (Itinerary itinerary : OtpPojoRoute.getPlan().getItineraries()) {
            long duration = calculator.Sec_To_Min(itinerary.getDuration());
            LocalTime arrivalTime = LocalDateTime.ofEpochSecond(((long) itinerary.getEndTime() / 1000), 0, offset).toLocalTime(); //the time which is given by the itinerary is given in milliseconds, divide with 1000 to get seconds
            arrivalTime = calculator.roundToMinute(arrivalTime);        //Round Up the Time to a full Minute    (further explanation look at line 179-184 and later)
            double walkDistance = Math.round(itinerary.getWalkDistance());
            int transfers = itinerary.getTransfers();

            BigDecimal cost = null; //TODO Kosten hier noch nicht vorhanden -- im nachhinein noch machen

            ConnectionPlan.addRoute(new Route(arrivalTime, duration, walkDistance, transfers, cost));
            routeCounter++;

            buildLegs(itinerary);   //builds the legs for the given itinerary
        }
    }

    /**
     * Method to build all legs an associated information plus stops to the leg and route for the
     * given itinerary
     *
     * @param itinerary the itinerary for which the legs etc. should be created and added to the route
     */
    private void buildLegs(Itinerary itinerary) {
        for (Leg leg : itinerary.getLegs()) {
            Location start;
            Location end;
            String legType;

            /*
            Arrival an departure times from the legs are given in milliseconds and in epochal time
            It´s needed to cast it to long (cause it´s given as Object) then divide it with 1000 to get seconds
            and then create a LocalDateTime of the epochal seconds with extra method
            The offset is set above and since there isn´t any nano seconds given it is set to 0.
            Last its needed to get the LocalTime from the LocalDateTime and set it as either start / end / departure or arrival time.
             */
            LocalTime startTime = LocalDateTime.ofEpochSecond(((long) leg.getStartTime()) / 1000, 0, offset).toLocalTime();
            startTime = calculator.roundToMinute(startTime);    //Round Up the Time to a full Minute    (further explanation look at line 179-184 and later)
            LocalTime endTime = LocalDateTime.ofEpochSecond(((long) leg.getEndTime()) / 1000, 0, offset).toLocalTime();
            endTime = calculator.roundToMinute(endTime);        //Round Up the Time to a full Minute    (further explanation look at line 179-184 and later)

            int startTick = calculator.calculateSimulationTick(startTime);
            int endTick = calculator.calculateSimulationTick(endTime);


            //Creating the start and end Location of the leg
            start = new Location(leg.getFrom().getLat(), leg.getFrom().getLon());
            start.setLocationName(leg.getFrom().getName());

            end = new Location(leg.getTo().getLat(), leg.getTo().getLon());
            end.setLocationName(leg.getTo().getName());

            /*
            Checks all the legs for its type / mode and then either sets it to Walk or pt (Transit)
            then creates the appropriate legs with information
             */
            if (leg.getTransitLeg()) {
                legType = "pt";
                String vehicle = leg.getMode();
                String lineName = leg.getRoute();       //Name of the Connection Line for example "S7" -- only if the leg is an transit leg

                model.Routemodel.Leg routeLeg = new model.Routemodel.Leg(start, end, startTime, endTime, legType, 0, startTick, endTick, vehicle,lineName);
                buildStops(leg, routeLeg);
                ConnectionPlan.getRoutes().get(routeCounter).addLeg(routeLeg);
            } else {
                legType = leg.getMode();
                ConnectionPlan.getRoutes().get(routeCounter).addLeg(start, end, startTime, endTime, legType, startTick, endTick, "FOOT",""); //lineName empty because it´s an Walk Leg so no Transit is used
            }
        }
    }

    /**
     * Method to create all Stops of an leg and then add it to both, the leg and the route
     * To build the stops the Method needs an Leg for OpenTripPlanner Response and the leg for which the stops
     * should be added / created
     * The inforamtion form the Leg form OTP are then converted to a Stop
     *
     * @param ptLeg    the Leg form OpenTripPlanner (POJO class createt from the JSON Object from OTP)
     * @param routeLeg the Routemodel Leg
     */
    //TODO viele Redundante Zeilen vllt. mal schauen ob es möglich ist das etwas zu kürzen (extra Methode) -- im nachhinein machen (wenn möglich)
    private void buildStops(Leg ptLeg, model.Routemodel.Leg routeLeg) {
        int stopCounter = 0;

        String stopName;
        String stopId;
        Location location;

        LocalTime departureTime;
        LocalTime arrivalTime;
        int departureTick;
        int arrivalTick;

        //First the first stop of an leg is added -- to do so the stop is given by the fromStop of the Leg
        stopName = ptLeg.getFrom().getName();
        stopId = ptLeg.getFrom().getStopId();
        location = new Location(ptLeg.getFrom().getLat(), ptLeg.getFrom().getLon());

        /*
        Setting the departure an arrival time and tick
        For the Time setting first the it´s needed to get the LocalDateTime from EpochSeconds because the time is only given in this format, then from LocalDateTime the
        toLocalTime Method is used to get the LocalTime
        From there on the roundToMinute Methode from the TimeController is used to cut the seconds of the given Time
         */
        departureTime = LocalDateTime.ofEpochSecond(((long) ptLeg.getFrom().getDeparture()) / 1000, 0, offset).toLocalTime();
        departureTime = calculator.roundToMinute(departureTime);
        arrivalTime = LocalDateTime.ofEpochSecond(((long) ptLeg.getFrom().getArrival()) / 1000, 0, offset).toLocalTime();
        arrivalTime = calculator.roundToMinute(arrivalTime);

        //To get the Ticks of the departure and arrival Time the calculateSimulationTick Method from the TimeController is used
        departureTick = calculator.calculateSimulationTick(departureTime);
        arrivalTick = calculator.calculateSimulationTick(arrivalTime);

        routeLeg.addStop(location, stopName, stopId, departureTime, arrivalTime, departureTick, arrivalTick);
        ConnectionPlan.getRoutes().get(routeCounter).addStop(location, stopName, stopId, departureTime, arrivalTime, departureTick, arrivalTick);
        stopCounter++;

        // Second the stops between the origin and destination stops are added and created
        for (IntermediateStop intermediateStop : ptLeg.getIntermediateStops()) {
            stopName = intermediateStop.getName();
            stopId = intermediateStop.getStopId();
            location = new Location(intermediateStop.getLat(), intermediateStop.getLon());

            /*
            Setting the departure an arrival time and tick
            For the Time setting first the it´s needed to get the LocalDateTime from EpochSeconds because the time is only given in this format, then from LocalDateTime the
            toLocalTime Method is used to get the LocalTime
            From there on the roundToMinute Methode from the TimeController is used to cut the seconds of the given Time
             */
            departureTime = LocalDateTime.ofEpochSecond(((long) intermediateStop.getDeparture()) / 1000, 0, offset).toLocalTime();
            departureTime = calculator.roundToMinute(departureTime);
            arrivalTime = LocalDateTime.ofEpochSecond(((long) intermediateStop.getArrival()) / 1000, 0, offset).toLocalTime();
            arrivalTime = calculator.roundToMinute(arrivalTime);

            //To get the Ticks of the departure and arrival Time the calculateSimulationTick Method from the TimeController is used
            departureTick = calculator.calculateSimulationTick(departureTime);
            arrivalTick = calculator.calculateSimulationTick(arrivalTime);

            routeLeg.addStop(location, stopName, stopId, departureTime, arrivalTime, departureTick, arrivalTick);
            ConnectionPlan.getRoutes().get(routeCounter).addStop(location, stopName, stopId, departureTime, arrivalTime, departureTick, arrivalTick);
            stopCounter++;
        }

        //Now the last stop of the Route is added -- to do so the stop is given by the toStop of the leg
        stopName = ptLeg.getTo().getName();
        stopId = ptLeg.getTo().getStopId();
        location = new Location(ptLeg.getTo().getLat(), ptLeg.getTo().getLon());

        /*
        Setting the departure an arrival time and tick
        For the Time setting first the it´s needed to get the LocalDateTime from EpochSeconds because the time is only given in this format, then from LocalDateTime the
        toLocalTime Method is used to get the LocalTime
        From there on the roundToMinute Methode from the TimeController is used to cut the seconds of the given Time
         */
        departureTime = LocalDateTime.ofEpochSecond(((long) ptLeg.getTo().getDeparture()) / 1000, 0, offset).toLocalTime();
        departureTime = calculator.roundToMinute(departureTime);
        arrivalTime = LocalDateTime.ofEpochSecond(((long) ptLeg.getTo().getArrival()) / 1000, 0, offset).toLocalTime();
        arrivalTime = calculator.roundToMinute(arrivalTime);

        //To get the Ticks of the departure and arrival Time the calculateSimulationTick Method from the TimeController is used
        departureTick = calculator.calculateSimulationTick(departureTime);
        arrivalTick = calculator.calculateSimulationTick(arrivalTime);

        routeLeg.addStop(location, stopName, stopId, departureTime, arrivalTime, departureTick, arrivalTick);
        ConnectionPlan.getRoutes().get(routeCounter).addStop(location, stopName, stopId, departureTime, arrivalTime, departureTick, arrivalTick);
        stopCounter++;

    }

    /**
     * Methode which is called after a Complete ConnectionPlan is build
     * The Method then checks all Stops in an route if some of them are a duplicate then deletes the duplicate.
     */
    //TODO: Nochmal überprüfen ob das überhaut benötigt wird -- im nachhinein machen (Output überprüfen mit Graphhopper checken)
    private void deleteDoubleStops() {
        /*
        Its possible to have duplicates because, all stops of an leg are added. And an stop which is the last stop of an leg
        could also be the start stop of an adjacent leg, and vice versa
        */
        for (Route route : ConnectionPlan.getRoutes()) {
            ArrayList<Stop> deleteList = new ArrayList<>();
            Stop stop;

            RouteIterator routeIterator = new RouteIterator(route);     //To be able to call up some queries on a Route
            for (int i = 0; i < route.getStops().size(); i++) {
                stop = route.getStops().get(i);
                if (i < route.getStops().size() - 1) {
                    //for each stop it is checked if the name of the location is the same as the name of the next stop
                    if (stop.getLocation().getLocationName().equals(routeIterator.getNextStop(stop.getLocation()).getLocation().getLocationName())) {
                        deleteList.add(stop);
                    }
                }
            }

            //for every entry in the list the associated stop is deleted in Route stops list
            for (Stop deleteStop : deleteList) {
                route.getStops().remove(deleteStop);
            }
            route.setStopCounter(route.getStopCounter() - deleteList.size());
        }
    }
    //--------------------------------------- Getter & Setter ---------------------------------------//
    //----------------------------------------- Additional ------------------------------------------//
}
