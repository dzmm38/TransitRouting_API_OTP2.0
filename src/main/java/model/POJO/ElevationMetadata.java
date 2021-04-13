package model.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A POJO Class defining the ElevationMetadata Class from OpenTripPlanner
 * read the ElevationMetadata information from the response Object of OTP
 */
public class ElevationMetadata {
    //------------------------------------------ Variable -------------------------------------------//
    private double ellipsoidToGeoidDifference;
    private boolean geoidElevation;

    //----------------------------------------- Constructor -----------------------------------------//
    public ElevationMetadata() {
        // For JSON
    }

    //------------------------------------------- Methods -------------------------------------------//
    //--------------------------------------- Getter & Setter ---------------------------------------//
    @JsonProperty("ellipsoidToGeoidDifference")
    public double getEllipsoidToGeoidDifference() {
        return this.ellipsoidToGeoidDifference;
    }

    public void setEllipsoidToGeoidDifference(double ellipsoidToGeoidDifference) {
        this.ellipsoidToGeoidDifference = ellipsoidToGeoidDifference;
    }

    @JsonProperty("geoidElevation")
    public boolean getGeoidElevation() {
        return this.geoidElevation;
    }

    public void setGeoidElevation(boolean geoidElevation) {
        this.geoidElevation = geoidElevation;
    }
    //----------------------------------------- Additional ------------------------------------------//
}
