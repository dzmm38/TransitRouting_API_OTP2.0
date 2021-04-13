package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.TimeZone;

/**
 * Class to Convert the epochal Time in either a time of this day or in a Date
 * both as a String
 */
public class DateConverter {
    //------------------------------------------ Variable -------------------------------------------//
    TimeZone timeZone;      //time zone for Expample "Europe/Berlin" to get the right time offset

    //----------------------------------------- Constructor -----------------------------------------//

    /**
     * Constructor of the class
     * needs a ZoneId of the acutal Zone in which the routing was done
     *
     * @param zoneId the id of the Zone in which routing was done
     */
    public DateConverter(ZoneId zoneId) {
        this.timeZone = TimeZone.getTimeZone(zoneId);
    }

    //------------------------------------------- Methods -------------------------------------------//

    /**
     * Method to convert a long which indicates the epochalTime into a String representing a Date
     *
     * @param epochalTime the date and time in milliseconds ind epochalTime Format
     * @return the Date of the epochalTime representing in a String
     */
    public String properDate(long epochalTime) {
        String date;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");     //here the format is defined which represents the date
        dateFormat.setTimeZone(timeZone);
        date = dateFormat.format(epochalTime);

        return date;
    }

    /**
     * Method to convert a long which indicates the epochalTime into a String representing the associated time
     *
     * @param epochalTime the date and time in milliseconds ind epochalTime Format
     * @return the Time of the epochalTime representing in a String
     */
    public String properTime(long epochalTime) {
        String time;

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");      //here the format is defined which represents the time
        dateFormat.setTimeZone(timeZone);
        time = dateFormat.format(epochalTime);

        return time;
    }
    //--------------------------------------- Getter & Setter ---------------------------------------//
    //----------------------------------------- Additional ------------------------------------------//
}
