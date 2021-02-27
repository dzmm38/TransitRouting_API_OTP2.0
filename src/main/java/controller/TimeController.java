package controller;

import java.time.LocalTime;

/**
 * Class to calculate some time format changes and tick calculations
 * <p>
 * Contains a timeConverter to do these calculation
 * Some Advanced Methods for calculations with ticks and times
 */
public class TimeController {
    //------------------------------------------ Variable -------------------------------------------//
    private static int tickZero;

    //----------------------------------------- Constructor -----------------------------------------//
    public TimeController() {
    }

    //------------------------------------------- Methods -------------------------------------------//

    /**
     * Method to convert a time to the decimal time format (7.30 --> 7.50)
     * time has to be in a double format and needs to represent hours and minutes
     *
     * @param hours_minutes time which should be transformed into decimal time
     * @return decimal time as double (represents hours and minutes)
     */
    public double Time_To_DecimalTime(double hours_minutes) {
        int hour = (int) hours_minutes;                                //casted to int to get only the numbers without the decimals
        double min = (int) (Math.round(((hours_minutes % 1) * 100)));       //modulo 1 to get the decimals, multiply with 100 and then round to only get two decimals

        min = min_to_hours(min);                                       //transform minutes in a hour format
        return hour + min;
    }

    /**
     * Method to convert time to the decimal time format (7.30 --> 7.50)
     * time has to be in long an as seconds (hours, minutes as seconds of the day not as epoch time)
     *
     * @param time_in_seconds time which should be transformed into decimal time as long
     * @return decimal time as long (represents the seconds)
     */
    public Long Time_To_DecimalTime(long time_in_seconds) {
        return hours_to_Sec(Time_To_DecimalTime((Sec_to_Hours(time_in_seconds))));
    }

    /**
     * Converts the decimal time in the "normal" time format (7.50 --> 7.30)
     * time has to be in a double format and needs to represent hours and minutes
     *
     * @param decimalTime_hours_minutes decimal time which should be transformed to into "normal" time
     *                                  (needs to be in a double which represents hours and minutes)
     * @return time in the normal time format with hours and minutes as a double
     */
    public double DecimalTime_To_Time(double decimalTime_hours_minutes) {
        int std = (int) decimalTime_hours_minutes;                                 //to get only the hours cast to int
        double min = (Math.round((decimalTime_hours_minutes % 1) * 100.0)) / 100.0;      //modulo 1 to get the decimals, multiply with 100 and then round to only get two decimals

        min = Math.round(min * 60) / 100.0;                                             //transformation in decimal times cause it only differs in minutes and sec
        return std + min;
    }

    /**
     * Converts the decimal time in the "normal" time format (7.50 --> 7.30)
     * time has to be in a long format and needs to represent seconds (hours, minutes as seconds of the day not as epoch time)
     *
     * @param decimalTime_in_seconds decimal time which should be transformed into normal time as long
     * @return normal time as long (represents seconds)
     */
    public Long DecimalTime_To_Time(long decimalTime_in_seconds) {
        return hours_to_Sec(DecimalTime_To_Time(((Sec_to_Hours(decimalTime_in_seconds)))));
    }

    /**
     * Converts seconds into minutes
     *
     * @param seconds time in seconds
     * @return time in minutes
     */
    public int Sec_To_Min(long seconds) {
        return (int) (Math.round((((double) seconds) / 60)));
    }

    /**
     * Converts seconds to hours
     *
     * @param seconds time in seconds
     * @return time in hours + minutes
     */
    public double Sec_to_Hours(long seconds) {
        return min_to_hours(Sec_To_Min(seconds));
    }

    /**
     * Converts minutes to seconds (minutes may also contain seconds as well)
     *
     * @param minutes time in minutes and seconds
     * @return time in seconds
     */
    public int Min_To_Sec(double minutes) {
        return (int) Math.round((minutes * 60));
    }

    /**
     * Converts minutes to hours (minutes may also contain seconds as well but rounds to minutes)
     *
     * @param minutes time in minutes
     * @return time in hours and minutes
     */
    public double min_to_hours(double minutes) {
        return Math.round(((minutes / 60) * 100.0)) / 100.0;         //*100 then /100 to round to the second decimal
    }

    /**
     * Converts hours to seconds
     *
     * @param hours time in hours (may also contain minutes)
     * @return time in seconds
     */
    public Long hours_to_Sec(double hours) {
        return (long) (hours * 3600);
    }

    /**
     * Converts hours in minutes
     *
     * @param hours time in hours (may also contain minutes)
     * @return time in minutes
     */
    public int hours_to_min(double hours) {
        return (int) Math.round(hours * 60);
    }


    /**
     * Method to convert a Time in LocalTime format to a double value
     *
     * @param time Time which should be converted
     * @return the time in a double value which represents only hours and minutes
     */
    public double convertLocalTimeToTime(LocalTime time) {
        double hour = time.getHour();
        double min = time.getMinute();

        return (hour + (min / 100));
    }

    /*
    Private because after the calculator gets initialized the startTime should not be changed
    Uses a LocalTime startTime to set The time at which the simulation starts (time to which the others can be
    compared to, to get the Tick of the simulation
    Sets the TickZero
     */
    public void calculateTickZero(LocalTime startTime) {

        double tickZero_Time;
        tickZero_Time = convertLocalTimeToTime(startTime);

        double result;
        result = hours_to_min(Time_To_DecimalTime(tickZero_Time));        //the time needs to be in Minutes because 1 Tick = 1 Min

        System.out.println("TickZero is set to : " + result + " Minutes of the Day");
        this.tickZero = (int) result;           //result should no longer have decimals so to set to TickZero it needs to be casted to int
    }

    /**
     * Method to convert a LocalTime to an int which represents
     * a tick of the AGADE simulation cycle
     *
     * @param time LocalTime which should be transformed in an tick
     * @return a tick (represents a minute) as an int value
     */
    public int convertTimeToTick(LocalTime time) {            //MUSS NOCH GESCHAUT WERDEN OB DAS SO FUNKTIONIERT WIE ICH MIR DAS VORGESTELLT HABE
        int tick;
        double hour;
        double min;
        double result;
        hour = time.getHour();
        min = time.getMinute();
        min = min / 100;
        result = hour + min;
        result = Time_To_DecimalTime(result);
        tick = hours_to_min(result);
        return tick;
    }

    /**
     * Method which calculates the difference between between the start Time and
     * the given time.
     *
     * @param tick Tick of the day (Time of the day in minutes) which should converted to an
     *             simulation Tick
     * @return int value, represents the simulation cycle in which the time is reached
     */
    public int tickAfterStart(int tick) {
        return tick - tickZero;
    }

    public int calculateSimulationTick(LocalTime time) {
        return tickAfterStart(convertTimeToTick(time));
    }

    public int calculateSimulationTick(String time) {
        return tickAfterStart(convertTimeToTick(LocalTime.parse(time)));
    }


    //--------------------------------------- Getter & Setter ---------------------------------------//
    public int getTickZero_Tick() {
        return tickZero;
    }

    //Here to get TickZero back in double it first needs to be transformed form tick to time format
    public double getTickZero_Time() {
        double result;
        result = DecimalTime_To_Time(min_to_hours(tickZero));
        return result;
    }

    //----------------------------------------- Additional ------------------------------------------//
}
