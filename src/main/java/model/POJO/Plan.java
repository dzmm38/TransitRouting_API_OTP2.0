package model.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * A POJO Class defining the Plan Class from OpenTripPlanner
 * read the Plan from information from the response Object of OTP
 */
public class Plan {
    //------------------------------------------ Variable -------------------------------------------//
    private long date;
    private From from;
    private To to;
    private List<Itinerary> itineraries;

    //----------------------------------------- Constructor -----------------------------------------//
    public Plan() {
        //For JSON
    }

    //------------------------------------------- Methods -------------------------------------------//
    //--------------------------------------- Getter & Setter ---------------------------------------//
    @JsonProperty("date")
    public long getDate() {
        return this.date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @JsonProperty("from")
    public From getFrom() {
        return this.from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    @JsonProperty("to")
    public To getTo() {
        return this.to;
    }

    public void setTo(To to) {
        this.to = to;
    }

    @JsonProperty("itineraries")
    public List<Itinerary> getItineraries() {
        return this.itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }
    //----------------------------------------- Additional ------------------------------------------//
}
