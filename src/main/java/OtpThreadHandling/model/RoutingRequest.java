package OtpThreadHandling.model;

import model.Routemodel.Actions;
import model.Routemodel.Location;
import java.time.LocalDateTime;

/**
 * A Model Class representing a Routing Request for Benchmarking / Testing purposes
 */
public class RoutingRequest {
    //------------------------------------------ Variable -------------------------------------------//
    private Location from;      //the start Location of the Request
    private Location to;        //the end Location of the Request

    private LocalDateTime queryTime;    //the time for which the routing is set
    private Actions actions;            //actions defining what to do with the Request [OTP only]
    private int routeAmount;            //the Amount of possible Routes OTP calculates per Request [OTP only]

    private String routingName;     //a String describing the Request / Origin and destination --- only for the testRequests

    //----------------------------------------- Constructor -----------------------------------------//
    public RoutingRequest(Location from, Location to, LocalDateTime queryTime, Actions actions, String routingName, int routeAmount){
        this.from = from;
        this.to = to;
        this.queryTime = queryTime;
        this.actions = actions;         //[OTP only]
        this.routeAmount = routeAmount; //[OTP only]
        this.routingName = routingName;
    }

    //------------------------------------------- Methods -------------------------------------------//
    //--------------------------------------- Getter & Setter ---------------------------------------//
    public Location getFrom() {
        return from;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public Location getTo() {
        return to;
    }

    public void setTo(Location to) {
        this.to = to;
    }

    public LocalDateTime getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(LocalDateTime queryTime) {
        this.queryTime = queryTime;
    }

    public String getRoutingName(){
        return routingName;
    }

    public Actions getActions() {
        return actions;
    }   //[OTP only]

    public void setActions(Actions actions) {
        this.actions = actions;
    }   //[OTP only]

    public int getRouteAmount() {
        return routeAmount;
    }   //[OTP only]

    public void setRouteAmount(int routeAmount) {
        this.routeAmount = routeAmount;
    }   //[OTP only]
}

    //----------------------------------------- Additional ------------------------------------------//
