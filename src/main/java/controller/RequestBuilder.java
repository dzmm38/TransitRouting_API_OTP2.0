package controller;

import model.RequestMap;
import org.opentripplanner.routing.api.request.RoutingRequest;      //TODO HERE

import java.time.ZoneId;

/**
 * Class to to build a URL requestString for sending it to the OpenTripPlanner Server
 * Converts a custom RoutingRequest from OpenTripPlanner into an String with instructions
 */
public class RequestBuilder {
    //------------------------------------------ Variable -------------------------------------------//
    private RequestMap requestMap;          //request Map to inherit all changes of an RoutingRequest compared to a default one
    private DateConverter dateConverter;    //to convert the epochalTime given from OTP´s Routing Request to a better Format for the String request
    private RoutingRequest _default;        //the default RoutingRequest to compare the custom one to, so the different settings can be identified

    //----------------------------------------- Constructor -----------------------------------------//

    /**
     * Constructor which needs a ZoneId of the Zone the routing is done in to identify the correct Time and Date
     *
     * @param zoneId id of an Zone the routing is done in
     */
    public RequestBuilder(ZoneId zoneId) {
        _default = new RoutingRequest();
        requestMap = new RequestMap();
        dateConverter = new DateConverter(zoneId);
    }

    //------------------------------------------- Methods -------------------------------------------//

    /**
     * Method to create an RequestString as an URL which can be send to the OTP Server as an request
     * The Method builds this String from an given OpenTripPlanner RoutingRequest.
     *
     * @param routingRequest an already defined RoutingRequest (OTP) with all required informations
     * @return A String which defines a Request for the OpenTripPlanner Server
     */
    public String buildRequestString(RoutingRequest routingRequest) {
        String RequestString;
        checkChanges(routingRequest, _default);      //first its needed to check which parameters are set by the user, so only they get extracted because defaults are always given
        RequestString = requestMap.getString();

        return RequestString;
    }

    /**
     * Method to check which changes were made to the custom Request compared to a default one and adds those to the requestMap.
     * The Method only adds the different entries of the RoutingRequest to the requestMap because the not defined rest gets set with default values and would
     * unnecessarily blow up the Request String
     *
     * @param custom   RoutingRequest (OTP) with the desired settings
     * @param _default RoutingRequest (OTP) with default settings
     */
    private void checkChanges(RoutingRequest custom, RoutingRequest _default) {
        /*
        Taking the Basic Parameters of an RoutingQuery and transfers them in an requestMap
        to be converted as an QueryString
         */
        if (custom.from != _default.from) {             //TODO HERE
            requestMap.add("fromPlace", custom.from.lat + "," + custom.from.lng);
        }
        if (custom.to != _default.to) {                 //TODO HERE
            requestMap.add("toPlace", custom.to.lat + "," + custom.to.lng);
        }
        if (custom.getDateTime() != _default.getDateTime()) {
            requestMap.add("date", dateConverter.properDate(custom.getDateTime().getTime()));
            requestMap.add("time", dateConverter.properTime(custom.getDateTime().getTime()));
        }
        if (custom.arriveBy != _default.arriveBy) {
            requestMap.add("arriveBy", Boolean.toString(custom.arriveBy));
        }
        if (custom.wheelchairAccessible != _default.wheelchairAccessible) {
            requestMap.add("wheelchair", Boolean.toString(custom.wheelchairAccessible));
        }
        if (custom.getNumItineraries() != _default.getNumItineraries()) {
            requestMap.add("numItineraries", Integer.toString(custom.getNumItineraries()));
        }
        //TODO hier vllt nocheinmal schauen !!!!!! ghet das anders ? -- notfalllösung geht aber
        if (!custom.modes.equals(_default.modes)) {
            String modeString = "";
            if (custom.transitAllowed()) {
                modeString += "TRANSIT,";
            }
            requestMap.add("mode", modeString + "WALK");      //Walk expilicit because no Route can be done without any walking //TODO HERE
        }
        if (custom.getMaxWalkDistance() != _default.getMaxWalkDistance()) {
            requestMap.add("maxWalkDistance", Double.toString(custom.getMaxWalkDistance()));
        }
        if (custom.showIntermediateStops != _default.showIntermediateStops) {
            requestMap.add("showIntermediateStops", Boolean.toString(custom.showIntermediateStops));
        }

        //TODO: Für einige Einstellung vllt. extra Klasse da diese möglicherweise Permanent gewünscht sind (Router/Graph Einstellungen)
        /*
        Advanced Query Settings which can be set additionally to the Basic Parameters
        which then append to the QueryString
         */
        if (custom.maxPreTransitTime != _default.maxPreTransitTime) {
            requestMap.add("maxPreTransitTime", Integer.toString(custom.maxPreTransitTime));
        }
        if (custom.walkReluctance != _default.walkReluctance) {
            requestMap.add("walkReluctance", Double.toString(custom.walkReluctance));
        }
        if (custom.waitReluctance != _default.waitReluctance) {
            requestMap.add("waitReluctance", Double.toString(custom.waitReluctance));
        }
        if (custom.waitAtBeginningFactor != _default.waitAtBeginningFactor) {
            requestMap.add("waitAtBeginningFactor", Double.toString(custom.waitAtBeginningFactor));
        }
        if (custom.walkSpeed != _default.walkSpeed) {
            requestMap.add("walkSpeed", Double.toString(custom.walkSpeed));
        }
        if (custom.bikeSpeed != _default.bikeSpeed) {
            requestMap.add("bikeSpeed", Double.toString(custom.bikeSpeed));
        }
        if (custom.bikeSwitchTime != _default.bikeSwitchTime) {
            requestMap.add("bikeSwitchTime", Integer.toString(custom.bikeSwitchTime));
        }
        if (custom.bikeSwitchCost != _default.bikeSwitchCost) {
            requestMap.add("bikeSwitchCost", Integer.toString(custom.bikeSwitchCost));
        }
//        if (custom.triangleSafetyFactor != _default.triangleSafetyFactor){
//            requestMap.add("triangleSafetyFactor", Double.toString(custom.triangleSafetyFactor));
//        }
//        if (custom.triangleSlopeFactor != _default.triangleSlopeFactor){                              //TODO HERE
//            requestMap.add("triangleSafteyFactor", Double.toString(custom.triangleSlopeFactor));
//        }
//        if (custom.triangleTimeFactor != _default.triangleTimeFactor){
//            requestMap.add("triangleTimeFactor", Double.toString(custom.triangleTimeFactor));
//        }
        if (custom.optimize != _default.optimize) {
            requestMap.add("optimize", custom.optimize.toString());
        }
//        if (custom.preferredRoutes != _default.preferredRoutes){                      //TODO HERE
//            requestMap.add("preferredRoutes",custom.preferredRoutes.asString());
//        }
        if (custom.otherThanPreferredRoutesPenalty != _default.otherThanPreferredRoutesPenalty) {
            requestMap.add("otherThanPreferredRoutesPenalty", Integer.toString(custom.otherThanPreferredRoutesPenalty));
        }
//        if (!custom.preferredAgencies.equals(_default.preferredAgencies)){                    //TODO HERE
//            requestMap.add("preferredAgencies",custom.preferredAgencies.toString());
//        }
        //TODO Die restlichen Parameter noch übernehmen und in RequestMap schreiben (noch 34)
    }
    //--------------------------------------- Getter & Setter ---------------------------------------//
    //----------------------------------------- Additional ------------------------------------------//
}
