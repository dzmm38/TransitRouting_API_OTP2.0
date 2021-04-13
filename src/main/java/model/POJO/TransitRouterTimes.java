package model.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A POJO Class defining the TransitRouterTimes Class from OpenTripPlanner
 * read the TransitRouterTimes information from the response Object of OTP
 */
//Class added for the use of the OTP2 Version
//TODO: Vielleicht für Benchmarking gut !!!!
public class TransitRouterTimes {
    //------------------------------------------ Variable -------------------------------------------//
    int tripPatternFilterTime;
    int accessEgressTime;
    int raptorSearchTime;
    int itineraryCreationTime;

    //----------------------------------------- Constructor -----------------------------------------//
    public TransitRouterTimes() {
        //For Json
    }

    //------------------------------------------- Methods -------------------------------------------//
    //--------------------------------------- Getter & Setter ---------------------------------------//
    @JsonProperty("tripPatternFilterTime")
    public int getTripPatternFilterTime() {
        return this.tripPatternFilterTime;
    }

    public void setTripPatternFilterTime(int tripPatternFilterTime) {
        this.tripPatternFilterTime = tripPatternFilterTime;
    }

    @JsonProperty("accessEgressTime")
    public int getAccessEgressTime() {
        return this.accessEgressTime;
    }

    public void setAccessEgressTime(int accessEgressTime) {
        this.accessEgressTime = accessEgressTime;
    }

    @JsonProperty("raptorSearchTime")
    public int getRaptorSearchTime() {
        return this.raptorSearchTime;
    }

    public void setRaptorSearchTime(int raptorSearchTime) {
        this.raptorSearchTime = raptorSearchTime;
    }

    @JsonProperty("itineraryCreationTime")
    public int getItineraryCreationTime() {
        return this.itineraryCreationTime;
    }

    public void setItineraryCreationTime(int itineraryCreationTime) {
        this.itineraryCreationTime = itineraryCreationTime;
    }
    //----------------------------------------- Additional ------------------------------------------//
}
