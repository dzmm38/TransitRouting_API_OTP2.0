package model.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A POJO Class defining the RequestParameters Class from OpenTripPlanner
 * read the RequestParameters from information from the response Object of OTP
 */
public class RequestParameters {

    //------------------------------------------ Variable -------------------------------------------//
    private String date;
    private String showIntermediateStops;
    private String fromPlace;
    private String toPlace;
    private String time;
    private String numItineraries;

    //Added for OTP2.0
    private String mode;

    //----------------------------------------- Constructor -----------------------------------------//
    public RequestParameters() {
        //For JSON
    }

    //------------------------------------------- Methods -------------------------------------------//
    //--------------------------------------- Getter & Setter ---------------------------------------//
    @JsonProperty("date")
    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("showIntermediateStops")
    public String getShowIntermediateStops() {
        return this.showIntermediateStops;
    }

    public void setShowIntermediateStops(String showIntermediateStops) {
        this.showIntermediateStops = showIntermediateStops;
    }

    @JsonProperty("fromPlace")
    public String getFromPlace() {
        return this.fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    @JsonProperty("toPlace")
    public String getToPlace() {
        return this.toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    @JsonProperty("time")
    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("numItineraries")
    public String getNumItineraries() {
        return this.numItineraries;
    }

    public void setNumItineraries(String numItineraries) {
        this.numItineraries = numItineraries;
    }

    //Added for OTP2 Version
    @JsonProperty("mode")
    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
    //----------------------------------------- Additional ------------------------------------------//
}
