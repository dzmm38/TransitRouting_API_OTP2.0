package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Routemodel.Plan;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;

public class RouteAndResponseLoader {
    //------------------------------------------ Variable -------------------------------------------//
    //----------------------------------------- Constructor -----------------------------------------//

    /**
     * Plain Constructor for normal Use
     */
    public RouteAndResponseLoader() {
        //Plain Constructor
    }

    //------------------------------------------- Methods -------------------------------------------//

    /**
     * Method to load a Route from a JsonFile
     * the Route is then loaded and cached in an ConnectionPlan Object to then be able to
     * work with
     *
     * @param FilePath Name of the Route which should be loaded (src/main/resources/Routes)
     * @return the Plan in which the Route is loaded to
     */
    public Plan loadRoute(String FilePath) {
        ObjectMapper om = createMapper();

        Plan ConnectionPlan = null;     //null because it has to be set in case the Plan could not be read correctly

        try {
            ConnectionPlan = om.readValue(new File("src\\main\\resources\\Routes\\" + FilePath), Plan.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ConnectionPlan;      //returns the ConnectionPlan which contains the loaded Routes / or null if Route couldn't be loaded correctly
    }

    /**
     * Method to load a JSON File into a JSON Object representing a Plan / Response from OpenTripPlanner
     *
     * @param FilePath FilePath Name of the JSONObject which should be loaded (src/main/resources/Routes)
     * @return the Object in which the Route is loaded to
     */
    public JSONObject loadJsonObjectRouteFile(String FilePath) {
        ObjectMapper om = createMapper();

        JSONObject JSONResponse = null;     //null because it has to be set in case the JSONFile could not be read correctly

        try {
            JSONResponse = om.readValue(new File("src\\main\\resources\\Routes\\" + FilePath), JSONObject.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JSONResponse;        //returns the JSONObject which contains the loaded JSONFile / or null if File couldn't be loaded correctly
    }

    /**
     * Method which creates and returns an ObjectMapper
     *
     * @return an initialised ObjectMapper
     */
    private ObjectMapper createMapper() {
        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT);
        return om;
    }
    //--------------------------------------- Getter & Setter ---------------------------------------//
    //----------------------------------------- Additional ------------------------------------------//
}
