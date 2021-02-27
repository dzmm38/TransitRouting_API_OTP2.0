package model.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * A POJO Class defining the DebugOutput Class from OpenTripPlanner
 * read the DebugOutput information from the response Object of OTP
 */
public class DebugOutput {
    //------------------------------------------ Variable -------------------------------------------//
    private int precalculationTime;
    //    private int pathCalculationTime;
//    private List<Integer> pathTimes;          //commendted out Variables and Methods are not used by this version of OpenTripPlanner and come from the OTP Version 1.5
    private int renderingTime;
    private int totalTime;
//    private boolean timedOut;

    //Added for OpenTripPlanner 2.0
    private int directStreetRouterTime;
    private int transitRouterTime;
    private int filteringTime;
    private TransitRouterTimes transitRouterTimes;

    //----------------------------------------- Constructor -----------------------------------------//
    public DebugOutput() {
        //For JSON
    }

    //------------------------------------------- Methods -------------------------------------------//
    //--------------------------------------- Getter & Setter ---------------------------------------//
    @JsonProperty("precalculationTime")
    public int getPrecalculationTime() {
        return this.precalculationTime;
    }

    public void setPrecalculationTime(int precalculationTime) {
        this.precalculationTime = precalculationTime;
    }

//    @JsonProperty("pathCalculationTime")
//    public int getPathCalculationTime() {
//		 return this.pathCalculationTime;
//    }
//
//    public void setPathCalculationTime(int pathCalculationTime) {
//		 this.pathCalculationTime = pathCalculationTime;
//    }
//
//    @JsonProperty("pathTimes")
//    public List<Integer> getPathTimes() {
//		 return this.pathTimes;
//    }
//
//    public void setPathTimes(List<Integer> pathTimes) {
//		 this.pathTimes = pathTimes;
//    }

    @JsonProperty("renderingTime")
    public int getRenderingTime() {
        return this.renderingTime;
    }

    public void setRenderingTime(int renderingTime) {
        this.renderingTime = renderingTime;
    }

    @JsonProperty("totalTime")
    public int getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

//    @JsonProperty("timedOut")
//    public boolean getTimedOut() {
//		 return this.timedOut;
//    }
//
//    public void setTimedOut(boolean timedOut) {
//		 this.timedOut = timedOut;
//    }

    //Added for OpenTripPlanner 2.0
    @JsonProperty("directStreetRouterTime")
    public int getDirectStreetRouterTime() {
        return this.directStreetRouterTime;
    }

    public void setDirectStreetRouterTime(int directStreetRouterTime) {
        this.directStreetRouterTime = directStreetRouterTime;
    }

    @JsonProperty("transitRouterTime")
    public int getTransitRouterTime() {
        return this.transitRouterTime;
    }

    public void setTransitRouterTime(int transitRouterTime) {
        this.transitRouterTime = transitRouterTime;
    }

    @JsonProperty("filteringTime")
    public int getFilteringTime() {
        return this.filteringTime;
    }

    public void setFilteringTime(int filteringTime) {
        this.filteringTime = filteringTime;
    }

    @JsonProperty("transitRouterTimes")
    public TransitRouterTimes getTransitRouterTimes() {
        return this.transitRouterTimes;
    }

    public void setTransitRouterTimes(TransitRouterTimes transitRouterTimes) {
        this.transitRouterTimes = transitRouterTimes;
    }
    //----------------------------------------- Additional ------------------------------------------//
}
