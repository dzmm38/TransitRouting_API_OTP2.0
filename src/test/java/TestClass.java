//import controller.JsonRequestHandler;
//import model.JsonRequest;
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
        String dataFolderName = "1";    //the Name of the Folder under src/main/resources/var/otp/graphs/ where the osm/gtfs Files are
        OTPFacade.buildGraph(OsmFileName, GtfsFileName, dataFolderName);
    }

    @Test
    public void loadGraphAndStartServer() {
        String graphFolderName = "1";   //the Name of the Folder under src/main/resources/var/otp/graphs wehre the graph is which needs to be load
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
    public void testload(){
        loadRoute("Route_52.429779,13.447502_52.516423,13.378749.json");
        loadRoute("Route_52.434861,13.260825_52.480684,13.263863.json");
        loadRoute("Route_52.440791,13.444667_52.437963,13.346589.json");
        loadRoute("Route_52.475446,13.365343_52.514127,13.379614.json");
        loadRoute("Route_52.512281,13.611387_52.52554,13.535886.json");
        loadRoute("Route_52.516423,13.378749_52.517805,13.442522.json");
        loadRoute("Route_52.516998,13.417763_52.526551,13.37809.json");
        loadRoute("Route_52.538662,13.409295_52.483604,13.341451.json");
        loadRoute("Route_52.547906,13.193984_52.529015,13.210079.json");
        loadRoute("Route_52.596076,13.335111_52.525929,13.367809.json");
    }

    public void loadRoute(String name) {
        String FileName = name;
        Plan plan = OTPFacade.loadRoute(FileName);
        System.out.println("Next Route");
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

//        //To test the lineName addition
//        for (Route route : routes) {
//            System.out.println("Route " + routecounter);
//            for (Leg leg : route.getLegs()) {
//                System.out.println("-------------------------------------------------------------------------------------");
//                System.out.println("lineType: " + leg.getLegType());
//                System.out.println("Vehichle: " + leg.getVehicle());
//                System.out.println("lineName: " + leg.getLineName());
//                System.out.println("-------------------------------------------------------------------------------------");
//            }
//            routecounter++;
//        }
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


//    @Test
//    public void testmoreRequests(){
//        JsonRequest request1 = new JsonRequest(new Location(52.518112, 13.44189),new Location(52.514518, 13.350306),
//                                               LocalDateTime.of(2020, 11, 10, 15, 0),Actions.ROUTEMODEL_AS_JSON,5);
//        JsonRequest request2 = new JsonRequest(new Location(52.503731, 13.444316), new Location(52.566677, 13.371333),
//                                               LocalDateTime.of(2020,5,27,13,00),Actions.ROUTEMODEL_AS_JSON,6);
//
//        ArrayList<JsonRequest> test = new ArrayList<>();
//        test.add(request1);
//        test.add(request2);
//
//        JsonRequestHandler handler = new JsonRequestHandler();
//        handler.writeRequests(test);
//    }
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
