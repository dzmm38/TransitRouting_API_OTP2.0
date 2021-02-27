package model.Routemodel;

import model.POJO.DebugOutput;
import model.POJO.ElevationMetadata;
import model.POJO.RequestParameters;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model Class which contains the calculated Routes (Routemodel) as well as some further
 * information about the request / response
 */
public class Plan implements Serializable {

    //------------------------------------------ Variable -------------------------------------------//
    private DebugOutput debugOutput;
    private RequestParameters requestParameters;
    private ElevationMetadata elevationMetadata;

    private ArrayList<Route> routes;

    //----------------------------------------- Constructor -----------------------------------------//
    public Plan() {
        //For JSON Serialization
    }

    public Plan(DebugOutput debugOutput, RequestParameters requestParameters, ElevationMetadata elevationMetadata, ArrayList<Route> routes) {
        this.debugOutput = debugOutput;
        this.requestParameters = requestParameters;
        this.elevationMetadata = elevationMetadata;
        this.routes = routes;
    }

    public Plan(DebugOutput debugOutput, RequestParameters requestParameters, ElevationMetadata elevationMetadata) {
        this.debugOutput = debugOutput;
        this.requestParameters = requestParameters;
        this.elevationMetadata = elevationMetadata;

        this.routes = new ArrayList<>();
    }

    //------------------------------------------- Methods -------------------------------------------//
    public void addRoute(Route route) {
        routes.add(route);
    }

    //--------------------------------------- Getter & Setter ---------------------------------------//
    public DebugOutput getDebugOutput() {
        return debugOutput;
    }

    public void setDebugOutput(DebugOutput debugOutput) {
        this.debugOutput = debugOutput;
    }

    public RequestParameters getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(RequestParameters requestParameters) {
        this.requestParameters = requestParameters;
    }

    public ElevationMetadata getElevationMetadata() {
        return elevationMetadata;
    }

    public void setElevationMetadata(ElevationMetadata elevationMetadata) {
        this.elevationMetadata = elevationMetadata;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }
    //----------------------------------------- Additional ------------------------------------------//
}
