package model.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A POJO Class defining the LegGeometry Class from OpenTripPlanner
 * read the LegGeometry from information from the response Object of OTP
 */
public class LegGeometry {
    //------------------------------------------ Variable -------------------------------------------//
    private String points;
    private int length;

    //----------------------------------------- Constructor -----------------------------------------//
    public LegGeometry() {
        //For JSON
    }

    //------------------------------------------- Methods -------------------------------------------//
    //--------------------------------------- Getter & Setter ---------------------------------------//
    @JsonProperty("points")
    public String getPoints() {
        return this.points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    @JsonProperty("length")
    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    //----------------------------------------- Additional ------------------------------------------//
}
