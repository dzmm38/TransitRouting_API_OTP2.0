package model.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A POJO Class defining the Fare Class from OpenTripPlanner
 * read the Fare information from the response Object of OTP
 */
//Class Added for OTP2 Version
public class Fare {
    //------------------------------------------ Variable -------------------------------------------//
    private Fare fare;
    private Details details;

    //----------------------------------------- Constructor -----------------------------------------//
    public Fare() {
        //For Json
    }

    //------------------------------------------- Methods -------------------------------------------//

    //--------------------------------------- Getter & Setter ---------------------------------------//
    @JsonProperty("fare")
    public Fare getFare() {
        return this.fare;
    }

    public void setFare(Fare fare) {
        this.fare = fare;
    }

    @JsonProperty("details")
    public Details getDetails() {
        return this.details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
    //----------------------------------------- Additional ------------------------------------------//
}
