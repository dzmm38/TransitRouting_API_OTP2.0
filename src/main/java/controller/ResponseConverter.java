package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.POJO.Root;
import org.json.simple.JSONObject;

import java.io.IOException;

/**
 * Class with the purpose to change the JsonObject given by the OpenTripPlanner Response into some
 * Pojo Classes representing the RoutingResponse Structure of OpenTripPlanner
 */
public class ResponseConverter {
    //------------------------------------------ Variable -------------------------------------------//
    //----------------------------------------- Constructor -----------------------------------------//

    /**
     * Standard Constructor use if normal usage
     * (JSON to Pojo to Routemodel)
     */
    public ResponseConverter() {

    }

    //------------------------------------------- Methods -------------------------------------------//

    /**
     * Method which takes the OpenTripPlanner Response as JSONObject and then transforms (reads) them into
     * an equally build POJO Class (representing the RoutingResponse form OTP)
     *
     * @param JsonResponse an JSONObject representing the response from OpenTripPlanner
     * @return a model.POJO.Plan which has the information of the JSON Response loaded
     */
    //TODO: Einzige Methode Funktion der Klasse -- vllt. woanders hin
    public Root JSONObjectToPojo(JSONObject JsonResponse) {

        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT);

        Root Pojo = null;        //null because it has to be set in case the JSONObject could not be read correctly

        try {
            Pojo = om.readValue(JsonResponse.toJSONString(), Root.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Pojo;        //returns the Pojo which contains the information of the JSONObject / or null if the JSONObject could not be loaded correctly
    }
    //--------------------------------------- Getter & Setter ---------------------------------------//
    //----------------------------------------- Additional ------------------------------------------//
}
