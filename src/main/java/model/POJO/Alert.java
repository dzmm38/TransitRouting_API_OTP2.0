package model.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A POJO Class defining the Alert Class from OpenTripPlanner
 * read the Alert information from the response Object of OTP
 */
//Class Added for OTP2 Version
public class Alert {

    //------------------------------------------ Variable -------------------------------------------//
    private String alertHeaderText;

    //----------------------------------------- Constructor -----------------------------------------//

    //------------------------------------------- Methods -------------------------------------------//

    //--------------------------------------- Getter & Setter ---------------------------------------//
    @JsonProperty("alertHeaderText")
    public String getAlertHeaderText() {
        return this.alertHeaderText;
    }

    public void setAlertHeaderText(String alertHeaderText) {
        this.alertHeaderText = alertHeaderText;
    }
    //----------------------------------------- Additional ------------------------------------------//
}
