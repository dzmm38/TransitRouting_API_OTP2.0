package model.Routemodel;

/**
 * Enumeration that represents all options how to deal with a routing request
 */
public enum Actions {
    /**
     * Only delivers the String which normally would be send to the OpenTripPlanner server for a routing request
     */
    REQUEST_URL_STRING("URL"),
    /**
     * does routing based on the routingRequest and saves the Response from OpenTripPlanner as Json File of the Routemodel
     */
    ROUTEMODEL_AS_JSON("ROUTEMODEL"),
    /**
     * does routing based on the routingRequest and saves the complete Response from OpenTripPlanner as Json File of an normal JSONObject
     */
    JSONOBEJCT_AS_JSON("JSONOBJECT"),

    /**
     * Prints all available Action Option to the Console for better Explanation
     */
    EXPLANATION("");

    private final String code;


    Actions(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
