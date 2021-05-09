package controller;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.POJO.Root;
import model.Routemodel.Actions;
import model.Routemodel.Plan;
import org.json.simple.JSONObject;
import org.opentripplanner.routing.api.request.RoutingRequest;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

/**
 * Routing Client
 * to handle all things related to a REST Client to connect to the server
 * and making invokes
 */
public class RoutingClientHandler {

    //------------------------------------------- Variable -------------------------------------------//
    private static final String OTPConnection = "http://localhost:8086/";      //the URL / URI to the OTP Server
    private static final String ActionPath_Plan = "otp/routers/default/plan";   //the part of the URL at wich the plan Action of OTP is Located
    private Client client;                  //the actual REST Client to handle the request sending for the OTP Server
    private JSONObject jsonObject;          //JsonObject which is given by the OTP response after an request
    private RequestBuilder requestBuilder;  //object to build an requestString for OTP
    private LocalDateTime dateTime;         //simulation start time (or the time at wich should be simulated)
    private ZoneId zoneId;

    //----------------------------------------- Constructor -----------------------------------------//
    public RoutingClientHandler(ZoneId zoneId, int year, int month, int day, int hour, int min) {
        this.zoneId = zoneId;
        client = ClientBuilder.newClient();
        requestBuilder = new RequestBuilder(zoneId);

        this.dateTime = LocalDateTime.of(year, month, day, hour, min);
    }

    //------------------------------------------- Methods -------------------------------------------//

    /**
     * Method which handles all actions containing the actual Routing from OpenTripPlanner and saving the Result
     * For a normal Request, the Request is transformed then send to OTP and the Response then saved or converted to the build Routmodel
     * and then saved
     *
     * @param routingRequest Object of an RoutingRequest from OpenTripPlanner containing all desired data
     * @param actions        a Enumeration which defines what to do with the Request
     *                       for an Explanation use the default (Explanation) or see at the Actions Class
     */
    public void findRoute(RoutingRequest routingRequest, Actions actions) {

        JSONObject responseObject;
        ResponseConverter responseConverter;
        Root rootPlan;
        OTPResponseHandler otpResponseHandler;

        /*
        Here the Programm checks which option is selected and then executes the associated code
        Because of the Enum there are only four option but could be expanded
        For further information on the Actions please look at the Enum
         */
        switch (actions) {
            case REQUEST_URL_STRING:
                getCompletePlanRequestString(routingRequest);
                break;

            case JSONOBEJCT_AS_JSON:
                responseObject = getRoute(routingRequest);
                writingJsonObjectToJSON(responseObject);
                break;

            case ROUTEMODEL_AS_JSON:
                responseObject = getRoute(routingRequest);
                responseConverter = new ResponseConverter();
                rootPlan = responseConverter.JSONObjectToPojo(responseObject);
                otpResponseHandler = new OTPResponseHandler(rootPlan, zoneId, dateTime);
                Plan ConnectionPlan;
                ConnectionPlan = otpResponseHandler.build();
                //writingPlanToJson(ConnectionPlan);
                break;

            default:
                System.out.println("Please choose one of the available Option as Action:");
                System.out.println("Actions.REQUEST_URL_STRING  :  To get only the Request String which the Programm is sending to the OpenTripPlanner Server");
                System.out.println("Actions.JSONOBJECT_AS_JSON  :  To sending the RoutingRequest to OpenTripPlanner server and saving the Response (JSON Object) as .json File");
                System.out.println("Actions.ROUTEMODEL_AS_JSON  :  To sending the RoutingRequest to OpenTripPlanner server and then transforming the information");
                System.out.println("                               of the Response into a Routemodel Object which then is saved");
                System.out.println("");
                System.out.println("The Routemodel is a specific Format which only contains some necessary an the most important information of the Response");
                break;
        }
    }

    /**
     * Method which actually sends the Request to the OpenTripPlanner server and handling the initial Response
     *
     * @param routingRequest the Routing Reqeust which should be send to OpenTripPlanner
     * @return JsonObject which contains the calculated Plans / Routes from OpenTripPlanner as an JSONObject
     */
    private JSONObject getRoute(RoutingRequest routingRequest) {

        JSONObject jsonRequest;
        String RoutingRequest = requestBuilder.buildRequestString(routingRequest);      //creating the Request URL

        //Disabled for cleaner Console if tested with many Requests at once
        //System.out.println("Sending Request to OTPServer");
        //System.out.println("Request: " + RoutingRequest);

        Invocation.Builder request;
        request = client.target(OTPConnection + ActionPath_Plan + RoutingRequest)       //creating the actual server Request
                .request(MediaType.APPLICATION_JSON);

        jsonRequest = request.get(JSONObject.class);        //sending the Request and getting the Response as a JSONObject
        return jsonRequest;
    }

    /**
     * Method which returns the Request URL string which normally should be send to the OTP Server for Routing
     * Could be used to analyse the RoutingRequest
     *
     * @param routingRequest the OpenTripPlanner Routing Request which should be used
     * @return a String which represents the Request String / URL to send a invoke to OTP
     */
    private String getCompletePlanRequestString(RoutingRequest routingRequest) {
        String RoutingRequest = requestBuilder.buildRequestString(routingRequest);      //getting the Routing Parameters of the Request
        System.out.println(OTPConnection + ActionPath_Plan + RoutingRequest);
        return OTPConnection + ActionPath_Plan + RoutingRequest;        //taking all necessary parts of the Request URL and returning them as String
    }

    /**
     * Method to write a JsonObject (containing some Routes) representing a Response as a Json File unter src/main/resources/Routes
     * The name of the File contains the Start and End location of the Request to distinguish between other Requests / Routes
     *
     * @param jsonObject the Response which should be written and saved as a Json File
     */
    private void writingJsonObjectToJSON(JSONObject jsonObject) {
        String extraFileName = "JsonObject_";      //Signature to detect from which Class / Object the Json file is created

        Map map = (Map) jsonObject.get("requestParameters");

        extraFileName = extraFileName + map.get("fromPlace") + "_" +
                map.get("toPlace");

        //System.out.println("Create and Saves " + extraFileName + ".json");


        ObjectMapper om = new ObjectMapper(new JsonFactory());
        om.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            om.writeValue(new File("src\\main\\resources\\Routes\\" + extraFileName + ".json"), jsonObject);         //writing the Object to a File
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to write a Plan (containing some Routes) as a Json File under src/main/resources/Routes
     * The name of the File contains the Start und End location of the Request to distinguish between other Requests / Routes
     *
     * @param ConnectionPlan the Plan which should be written and saved as a Json File
     */
    private void writingPlanToJson(Plan ConnectionPlan) {
        String extraFileName = "Route_";        //Signature to detect from which Class / Object the Json file is created

        extraFileName = extraFileName + ConnectionPlan.getRequestParameters().getFromPlace() + "_" +     //to get the lat and lon and add it to the file name to have better understanding of the Request
                ConnectionPlan.getRequestParameters().getToPlace();

        //System.out.println("Creates and saves " + extraFileName + ".json ..........");

        ObjectMapper om = new ObjectMapper(new JsonFactory());
        om.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            om.writeValue(new File("src\\main\\resources\\Routes\\" + extraFileName + ".json"), ConnectionPlan);     //writing the Object to a File
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
