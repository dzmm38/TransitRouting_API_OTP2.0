package model.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A POJO Class defining the To Class from OpenTripPlanner
 * read the To from information from the response Object of OTP
 */
public class To {
    //------------------------------------------ Variable -------------------------------------------//
    private String name;
    private double lon;
    private double lat;
    private String orig;
    private String vertexType;
    private String platformCode;
    private Object arrival;
    private Object departure;
    private int stopIndex;
    private int stopSequence;
    private String boardAlightType;
    private String stopId;

    //----------------------------------------- Constructor -----------------------------------------//
    public To() {
        //For JSON
    }

    //------------------------------------------- Methods -------------------------------------------//
    //--------------------------------------- Getter & Setter ---------------------------------------//
    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    @JsonProperty("orig")
    public String getOrig() {
        return this.orig;
    }

    public void setOrig(String orig) {
        this.orig = orig;
    }

    @JsonProperty("vertexType")
    public String getVertexType() {
        return this.vertexType;
    }

    public void setVertexType(String vertexType) {
        this.vertexType = vertexType;
    }

    @JsonProperty("platformCode")
    public String getPlatformCode() {
        return this.platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    @JsonProperty("arrival")
    public Object getArrival() {
        return this.arrival;
    }

    public void setArrival(Object arrival) {
        this.arrival = arrival;
    }

    @JsonProperty("departure")
    public Object getDeparture() {
        return this.departure;
    }

    public void setDeparture(Object departure) {
        this.departure = departure;
    }

    @JsonProperty("stopIndex")
    public int getStopIndex() {
        return this.stopIndex;
    }

    public void setStopIndex(int stopIndex) {
        this.stopIndex = stopIndex;
    }

    @JsonProperty("stopSequence")
    public int getStopSequence() {
        return this.stopSequence;
    }

    public void setStopSequence(int stopSequence) {
        this.stopSequence = stopSequence;
    }

    @JsonProperty("boardAlightType")
    public String getBoardAlightType() {
        return this.boardAlightType;
    }

    public void setBoardAlightType(String boardAlightType) {
        this.boardAlightType = boardAlightType;
    }

    @JsonProperty("stopId")
    public String getStopId() {
        return this.stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }
    //----------------------------------------- Additional ------------------------------------------//
}
