package model.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * A POJO Class defining the Step Class from OpenTripPlanner
 * read the Step from information from the response Object of OTP
 */
public class Step {
    //------------------------------------------ Variable -------------------------------------------//
    private double distance;
    private String relativeDirection;
    private String streetName;
    private String absoluteDirection;
    private boolean stayOn;
    private boolean area;
    private boolean bogusName;
    private double lon;
    private double lat;

    //Added / Changed for OTP2 Version use
//    private List<Object> elevation;   //Old OTP 1.5 Version
    private String elevation;           //new OTP2 Version
    private List<Alert> alerts;
    private String exit;

    //----------------------------------------- Constructor -----------------------------------------//
    public Step() {
        //For JSON
    }

    //------------------------------------------- Methods -------------------------------------------//
    //--------------------------------------- Getter & Setter ---------------------------------------//
    @JsonProperty("distance")
    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @JsonProperty("relativeDirection")
    public String getRelativeDirection() {
        return this.relativeDirection;
    }

    public void setRelativeDirection(String relativeDirection) {
        this.relativeDirection = relativeDirection;
    }

    @JsonProperty("streetName")
    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @JsonProperty("absoluteDirection")
    public String getAbsoluteDirection() {
        return this.absoluteDirection;
    }

    public void setAbsoluteDirection(String absoluteDirection) {
        this.absoluteDirection = absoluteDirection;
    }

    @JsonProperty("stayOn")
    public boolean getStayOn() {
        return this.stayOn;
    }

    public void setStayOn(boolean stayOn) {
        this.stayOn = stayOn;
    }

    @JsonProperty("area")
    public boolean getArea() {
        return this.area;
    }

    public void setArea(boolean area) {
        this.area = area;
    }

    @JsonProperty("bogusName")
    public boolean getBogusName() {
        return this.bogusName;
    }

    public void setBogusName(boolean bogusName) {
        this.bogusName = bogusName;
    }

    @JsonProperty("lon")
    public double getLon() {
        return this.lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @JsonProperty("lat")
    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    /*
     * Changed the Parameter etc. for uses with the OTP2 Version
     */
//    @JsonProperty("elevation")
//    public List<Object> getElevation() {
//		 return this.elevation;
//    }
//
//    public void setElevation(List<Object> elevation) {
//		 this.elevation = elevation;
//    }

    //FOR OTP2.0
    @JsonProperty("elevation")
    public String getElevation() {
        return this.elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    @JsonProperty("alerts")
    public List<Alert> getAlerts() {
        return this.alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    @JsonProperty("exit")
    public String getExit() {
        return exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }
    //----------------------------------------- Additional ------------------------------------------//
}
