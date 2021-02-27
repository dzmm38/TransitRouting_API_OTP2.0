package service;

import controller.OTPController;
import controller.RouteAndResponseLoader;
import controller.RoutingClientHandler;
import controller.TimeController;
import model.Routemodel.Actions;
import model.Routemodel.Location;
import model.Routemodel.Plan;
import org.json.simple.JSONObject;
import org.opentripplanner.routing.api.request.RoutingRequest;
import org.opentripplanner.routing.core.TraverseMode;
import org.opentripplanner.routing.core.TraverseModeSet;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Top Class (Facade Class)
 * Contains all necessary Methods to use the "API"
 * <p>
 * Possible:    to create a graph which is then saved in resources/var/otp/graphs/ (static)
 * to load a existing graph form a graph folder in resources/var/otp/graphs/ and start an OTP server(static)
 * to make queries from a location to another location at a specific time
 * to load an existing Route an do queries on it
 */
public class OTPFacade {

    //------------------------------------------ Variable -------------------------------------------//
    private final ZoneId zoneId;
    private final LocalDateTime dateTime;
    private final RoutingClientHandler routingClientHandler;

    //----------------------------------------- Constructor -----------------------------------------//

    /**
     * Constructor of the Facade Class
     *
     * @param zoneId ZoneId of the Zone for which Routing should be done. For Example Germany = "Europe/Berlin"
     * @param year   the year for which the routing is set. (Please check the year of your gtfs data or it might not be working correctly)
     * @param month  the actual month for which the Simulation is set
     * @param day    the actual day for which the Simulation is set
     * @param hour   the actual hour for which the Simulation is set
     * @param min    the actual minute for which the Simulation is set
     */
    public OTPFacade(String zoneId, int year, int month, int day, int hour, int min) {
        this.zoneId = ZoneId.of(zoneId);
        this.dateTime = LocalDateTime.of(year, month, day, hour, min);
        this.routingClientHandler = new RoutingClientHandler(this.zoneId, year, month, day, hour, min);

        new TimeController().calculateTickZero(dateTime.toLocalTime());
    }

    //------------------------------------------- Methods -------------------------------------------//

    /**
     * Static Method to build a graph from an Osm and Gtfs File
     * Please make sure that both of the files match in the routing area to avoid any problems
     *
     * @param OsmFileName    String: the Name of the Osm File located in resources/Osm to build the street aspect of the graph
     * @param GtfsFileName   String: the Name of the Gtfs File located in resources/Gtfs to build the transit aspect of the graph
     * @param fileFolderName String: the Name of an Folder with is created to contain both Files an the created graph (naming: please use number to name)
     */
    public static void buildGraph(String OsmFileName, String GtfsFileName, String fileFolderName) {
        OTPController.buildGraph(OsmFileName, GtfsFileName, fileFolderName);
    }

    /**
     * Static Method to load an existing graph from a directory (resources/var/otp/graphs/) and start an OTP Server with the graph
     * Note that the graph need to be build within the same version of the Programm in which it is loaded
     *
     * @param graphFolderName the name of the Folder which contains the graph.obj you want to load
     *                        (folder has to be under resources/var/otp/graphs/)
     */
    public static void startOTPServer(String graphFolderName) {
        OTPController.loadGraph(graphFolderName);
    }

    /**
     * Method to create a new Query and then finds a route, builds it and then saves the route as a JSON file
     *
     * @param from        location form where the query should start
     * @param to          location to where the query should end
     * @param queryTime   time at which the query is requested
     * @param routeAmount the amount of possible routes the response should have
     * @param actions     the actions which defines what to do with the Request (for further explanation use EXPLANATION as action or look
     *                    at the Actions Enum Class
     */
    //TODO: Die @param modes wieder einfügen um mehrere Modi anzubieten (standard Transit) -- muss checken ob dann alles Fehlerfrei geht -- im nachhinein machen
    public void createSimpleRoute(Location from, Location to, LocalDateTime queryTime, int routeAmount, Actions actions) {
        /*
        Changing the LocalDateTime into a Date Object.
        This is done because the OpenTripPlanner RoutingRouting Request only handels the Date Class Format
        but it´s easier to just build an LocalDateTime Object instead of an Date Object because of some
        Format restrictions
         */
        Date queryTime_DateFormat = new Date();
        queryTime_DateFormat.setMonth(queryTime.getMonthValue() - 1);     //-1 because the Months only set from 0-11 and so if you set a date Time for December (12) the Date cant be set because the max Value is 11
        queryTime_DateFormat.setYear(queryTime.getYear() - 1900);         //-1900 because in the Date Class, it adds the value 1900 to this
        queryTime_DateFormat.setDate(queryTime.getDayOfMonth());
        queryTime_DateFormat.setHours(queryTime.getHour());
        queryTime_DateFormat.setMinutes(queryTime.getMinute());

        /*
        Now to building the RoutingReqeust (from OpenTripPlanner) the parameters above are used to fill the RoutingRequest
        with the desired information.
        The Routing Request is used because it has an already defined set of options one can configure for greater
        settings for a request (so as an alternativ it´s possible to just send an RoutingReqeust like the createAdvacnedRequest Method
        for more settings.
         */
        RoutingRequest routingRequest = new RoutingRequest(new TraverseModeSet(TraverseMode.WALK, TraverseMode.TRANSIT));    //TODO HERE
        routingRequest.setFromString(from.getLat() + "," + from.getLon());
        routingRequest.setToString(to.getLat() + "," + to.getLon());
        routingRequest.setDateTime(queryTime_DateFormat);
        routingRequest.setNumItineraries(routeAmount);
        //       routingRequest.setModes(new TraverseModeSet("TRANSIT,WALK"));     //TODO HERE
        routingRequest.showIntermediateStops = true;

        createAdvancedRequest(routingRequest, actions);
    }

    /**
     * Method to create an complexer Request for OTP.
     * The Method needs an created and defined Request which already contains the desired information.
     * To Create such an Request, use the RoutingRequest given bei OpenTripPlanner.
     * The RoutingRequest class contains setter for all possible settings.
     * <p>
     * The Method then uses this Request to create an RequestString which can be send to OTP
     *
     * @param routingRequest The RoutingRequest Class from OpenTripPlanner
     * @param actions        the actions which defines what to do with the Request (for further explanation use EXPLANATION as action or look
     *                       at the Actions Enum Class
     */
    public void createAdvancedRequest(RoutingRequest routingRequest, Actions actions) {
        routingClientHandler.findRoute(routingRequest, actions);
    }

    /**
     * Method to load Routes (as Plans or JSONObjects) given a File Name
     *
     * @param fileName String which represents the exact File Name Route_xxx.json
     *                 The file needs to be in resources/Routes/
     * @return a loaded Plan from a File
     */
    public static Plan loadRoute(String fileName) {
        RouteAndResponseLoader loader = new RouteAndResponseLoader();

        Plan plan = loader.loadRoute(fileName);
        return plan;
    }

    //TODO: Gibt es eine bessere Methode um vllt. nicht 2 einzelne Methoden mit verschiedenen Rückgabeparametern zu haben ????

    /**
     * Method to load Routes (as a JsonObject) given a File Name
     *
     * @param fileName String which represents the exact FileName JsonObject_<xxxx>.json
     *                 The file needs to be in resources/Routes/
     * @return a loaded JsonObject from a File
     */
    public static JSONObject loadJsonObject(String fileName) {
        RouteAndResponseLoader loader = new RouteAndResponseLoader();

        JSONObject jsonObject = loader.loadJsonObjectRouteFile(fileName);
        return jsonObject;
    }
    //--------------------------------------- Getter & Setter ---------------------------------------//
    //----------------------------------------- Additional ------------------------------------------//
}

//------------------------------------------ Variable -------------------------------------------//
//----------------------------------------- Constructor -----------------------------------------//
//------------------------------------------- Methods -------------------------------------------//
//--------------------------------------- Getter & Setter ---------------------------------------//
//----------------------------------------- Additional ------------------------------------------//