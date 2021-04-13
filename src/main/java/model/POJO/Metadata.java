package model.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A POJO Class defining the Metadata Class from OpenTripPlanner
 * read the Metadata information from the response Object of OTP
 */
//Class Added for OTP2 Version
public class Metadata {
    //------------------------------------------ Variable -------------------------------------------//
    int searchWindowUsed;
    long nextDateTime;
    long prevDateTime;

    //----------------------------------------- Constructor -----------------------------------------//
    public Metadata() {
        //For Json
    }

    //------------------------------------------- Methods -------------------------------------------//
    //--------------------------------------- Getter & Setter ---------------------------------------//
    @JsonProperty("searchWindowUsed")
    public int getSearchWindowUsed() {
        return this.searchWindowUsed;
    }

    public void setSearchWindowUsed(int searchWindowUsed) {
        this.searchWindowUsed = searchWindowUsed;
    }

    @JsonProperty("nextDateTime")
    public long getNextDateTime() {
        return this.nextDateTime;
    }

    public void setNextDateTime(long nextDateTime) {
        this.nextDateTime = nextDateTime;
    }

    @JsonProperty("prevDateTime")
    public long getPrevDateTime() {
        return this.prevDateTime;
    }

    public void setPrevDateTime(long prevDateTime) {
        this.prevDateTime = prevDateTime;
    }
    //----------------------------------------- Additional ------------------------------------------//
}
