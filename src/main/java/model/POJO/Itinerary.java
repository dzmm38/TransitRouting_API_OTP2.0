package model.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * A POJO Class defining the Itinerary Class from OpenTripPlanner
 * read the Itinerary from information from the response Object of OTP
 */
public class Itinerary {
    //------------------------------------------ Variable -------------------------------------------//
    private int duration;
    private Object startTime;
    private Object endTime;
    private int walkTime;
    private int transitTime;
    private int waitingTime;
    private double walkDistance;
    private boolean walkLimitExceeded;
    private double elevationLost;
    private double elevationGained;
    private int transfers;
    private List<Leg> legs;
    private boolean tooSloped;

    //Added For OTP2 Version
    private Fare fare;

    //----------------------------------------- Constructor -----------------------------------------//
    public Itinerary() {
        //For JSON
    }

    //------------------------------------------- Methods -------------------------------------------//
    //--------------------------------------- Getter & Setter ---------------------------------------//
    @JsonProperty("duration")
    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @JsonProperty("startTime")
    public Object getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Object startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("endTime")
    public Object getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }

    @JsonProperty("walkTime")
    public int getWalkTime() {
        return this.walkTime;
    }

    public void setWalkTime(int walkTime) {
        this.walkTime = walkTime;
    }

    @JsonProperty("transitTime")
    public int getTransitTime() {
        return this.transitTime;
    }

    public void setTransitTime(int transitTime) {
        this.transitTime = transitTime;
    }

    @JsonProperty("waitingTime")
    public int getWaitingTime() {
        return this.waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    @JsonProperty("walkDistance")
    public double getWalkDistance() {
        return this.walkDistance;
    }

    public void setWalkDistance(double walkDistance) {
        this.walkDistance = walkDistance;
    }

    @JsonProperty("walkLimitExceeded")
    public boolean getWalkLimitExceeded() {
        return this.walkLimitExceeded;
    }

    public void setWalkLimitExceeded(boolean walkLimitExceeded) {
        this.walkLimitExceeded = walkLimitExceeded;
    }

    @JsonProperty("elevationLost")
    public double getElevationLost() {
        return this.elevationLost;
    }

    public void setElevationLost(double elevationLost) {
        this.elevationLost = elevationLost;
    }

    @JsonProperty("elevationGained")
    public double getElevationGained() {
        return this.elevationGained;
    }

    public void setElevationGained(double elevationGained) {
        this.elevationGained = elevationGained;
    }

    @JsonProperty("transfers")
    public int getTransfers() {
        return this.transfers;
    }

    public void setTransfers(int transfers) {
        this.transfers = transfers;
    }

    @JsonProperty("legs")
    public List<Leg> getLegs() {
        return this.legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    @JsonProperty("tooSloped")
    public boolean getTooSloped() {
        return this.tooSloped;
    }

    public void setTooSloped(boolean tooSloped) {
        this.tooSloped = tooSloped;
    }

    //Added for OTP2.0
    @JsonProperty("fare")
    public Fare getFare() {
        return this.fare;
    }

    public void setFare(Fare fare) {
        this.fare = fare;
    }
    //----------------------------------------- Additional ------------------------------------------//
}
