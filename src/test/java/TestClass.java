import model.Routemodel.*;
import org.json.simple.JSONObject;
import org.junit.Test;
import service.OTPFacade;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * TestClass for the OTPFacade Class
 * Example Class to how to handle the module and it´s functions
 * - has examples for every function of the OTPFacade Class
 * - creating a Graph
 * - loading a Graph and starting a OTP Server with it
 * - creating a Query
 * - loading a Route form a File
 * <p>
 * Testing files can be Found in the Resources Folder
 * <p>
 * below are some URL´s for testing (these needed to be downloaded an put in an extra Folder
 * under resources/var/otp/graphs/
 */
public class TestClass {

    //---------------------------------------- Main Methode -----------------------------------------//
    @Test
    public void performCompleteTest() {
        //TODO: hier einmal eine ganzen ablauf eines kompletten tests
    }

    //----------------------------------------- Test Methods ----------------------------------------//
    @Test
    public void createGraph() {
        String OsmFileName = "berlin-latest.osm.pbf";
        String GtfsFileName = "gtfs.zip";
        String dataFolderName = "3";    //the Name of the Folder under src/main/resources/var/otp/graphs/ where the osm/gtfs Files are
        OTPFacade.buildGraph(OsmFileName, GtfsFileName, dataFolderName);
    }

    @Test
    public void loadGraphAndStartServer() {
        String graphFolderName = "3";   //the Name of the Folder under src/main/resources/var/otp/graphs wehre the graph is which needs to be load
        OTPFacade.startOTPServer(graphFolderName);
    }

    @Test
    public void routeQuerySaveRoutemodel() {
        Location from = new Location(52.518112, 13.44189);   //Computerspiel Museum
        Location to = new Location(52.514518, 13.350306);    //Siegessäule
        LocalDateTime queryTime = LocalDateTime.of(2020, 11, 10, 15, 0);
        Actions actions = Actions.ROUTEMODEL_AS_JSON;
        int routeAmount = 3;

        OTPFacade otpFacade = new OTPFacade("Europe/Berlin", 2020, 11, 10, 13, 0);
        otpFacade.createSimpleRoute(from, to, queryTime, routeAmount, actions);
    }

    /*
    Ist noch nicht völlig implementiert, kann zwar geschrieben werden, aber nicht richtig geladen !!!!!
     */
    @Test
    public void routeQuerySaveJsonObject() {
        Location from = new Location(52.518112, 13.44189);   //Computerspiel Museum
        Location to = new Location(52.514518, 13.350306);    //Siegessäule
        LocalDateTime queryTime = LocalDateTime.of(2020, 11, 10, 15, 0);
        Actions actions = Actions.JSONOBEJCT_AS_JSON;
        int routeAmount = 3;

        OTPFacade otpFacade = new OTPFacade("Europe/Berlin", 2020, 11, 10, 13, 0);
        otpFacade.createSimpleRoute(from, to, queryTime, routeAmount, actions);
    }

    @Test
    public void routeQueryGetURL() {
        Location from = new Location(52.518112, 13.44189);   //Computerspiel Museum
        Location to = new Location(52.514518, 13.350306);    //Siegessäule
        LocalDateTime queryTime = LocalDateTime.of(2020, 11, 10, 15, 0);
        Actions actions = Actions.REQUEST_URL_STRING;
        int routeAmount = 4;

        OTPFacade otpFacade = new OTPFacade("Europe/Berlin", 2020, 11, 10, 13, 0);
        otpFacade.createSimpleRoute(from, to, queryTime, routeAmount, actions);
    }

    @Test
    public void ExplanationToActions() {
        Location from = new Location(52.518112, 13.44189);   //Computerspiel Museum
        Location to = new Location(52.514518, 13.350306);    //Siegessäule
        LocalDateTime queryTime = LocalDateTime.of(2020, 11, 10, 15, 0);
        Actions actions = Actions.EXPLANATION;
        int routeAmount = 3;

        OTPFacade otpFacade = new OTPFacade("Europe/Berlin", 2020, 11, 10, 13, 0);
        otpFacade.createSimpleRoute(from, to, queryTime, routeAmount, actions);
    }

    @Test
    public void loadRoute() {
        String FileName = "Route_52.518112,13.44189_52.514518,13.350306.json";
        Plan plan = OTPFacade.loadRoute(FileName);

        ArrayList<Route> routes = plan.getRoutes();

        for (Route route : routes) {
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("ArrivalTime:     " + route.getArrivalTime());
            System.out.println("Route Duration:  " + route.getDurationInMin());
            System.out.println("Transfers:       " + route.getTransfers());
            System.out.println("Walk Distance:   " + route.getWalkDistanceInMeters());
            System.out.println("Kosten der Route " + route.getCost());
            System.out.println("Anzahl Stops:    " + route.getStopCounter());
            System.out.println("-------------------------------------------------------------------------------------");
        }
        int routecounter = 1;

        //To test the lineName addition
        for (Route route : routes) {
            System.out.println("Route " + routecounter);
            for (Leg leg : route.getLegs()) {
                System.out.println("-------------------------------------------------------------------------------------");
                System.out.println("lineType: " + leg.getLegType());
                System.out.println("Vehichle: " + leg.getVehicle());
                System.out.println("lineName: " + leg.getLineName());
                System.out.println("-------------------------------------------------------------------------------------");
            }
            routecounter++;
        }
    }

    /*
    Not finished Feature and Test
    Needs more logic in the test to get proper Outputs !!!
     */
    @Test
    public void loadRouteJsonObject() {
        String FileName = "JsonObject_52.518112,13.44189_52.514518,13.350306.json";

        JSONObject jsonObject = OTPFacade.loadJsonObject(FileName);

        System.out.println(jsonObject.toJSONString());
    }
    //--------------------------------------- Getter & Setter ---------------------------------------//
    //----------------------------------------- Additional ------------------------------------------//
}

    /*
    -----------------------------------------------------------------------------------------------------------
    TEST FILES:
    OSM download from --> https://download.geofabrik.de/europe/germany/berlin-latest.osm.pbf                            --> Berlin
                          https://download.geofabrik.de/europe/germany/nordrhein-westfalen-latest.osm.pbf               --> NRW

    GTFS download from -->https://www.vbb.de/media/download/2029/GTFS.zip                                               --> Berlin
                          http://download.vrsinfo.de/gtfs/GTFS_VRS_mit_SPNV.zip                                         --> NRW
     -----------------------------------------------------------------------------------------------------------
     */
