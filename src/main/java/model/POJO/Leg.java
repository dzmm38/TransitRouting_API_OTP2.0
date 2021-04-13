package model.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * A POJO Class defining the Leg Class from OpenTripPlanner
 * read the Leg from information from the response Object of OTP
 */
public class Leg {
    //------------------------------------------ Variable -------------------------------------------//
    private Object startTime;
    private Object endTime;
    private int departureDelay;
    private int arrivalDelay;
    private boolean realTime;
    private double distance;
    private boolean pathway;
    private String mode;
    private String route;
    private int agencyTimeZoneOffset;
    private boolean interlineWithPreviousLeg;
    private From from;
    private To to;
    private LegGeometry legGeometry;
    private boolean rentedBike;
    private double flexDrtAdvanceBookMin;
    private double duration;
    private boolean transitLeg;
    private List<IntermediateStop> intermediateStops;
    private List<Step> steps;
    private String agencyName;
    private String agencyUrl;
    private int routeType;
    private String routeId;
    private String headsign;
    private String agencyId;
    private String tripId;
    private String serviceDate;
    private String routeShortName;

    //Added for OTP2 Version
    private List<Alert> alerts;
    private String routeColor;
    private String tripBlockId;
    private String routeTextColor;
    private String tripShortName;

    //----------------------------------------- Constructor -----------------------------------------//
    public Leg() {
        //For JSON
    }

    //------------------------------------------- Methods -------------------------------------------//
    //--------------------------------------- Getter & Setter ---------------------------------------//
    @JsonProperty("startTime")
    public Object getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Object startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("endTime")
    public Object getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }

    @JsonProperty("departureDelay")
    public int getDepartureDelay() {
        return this.departureDelay;
    }

    public void setDepartureDelay(int departureDelay) {
        this.departureDelay = departureDelay;
    }

    @JsonProperty("arrivalDelay")
    public int getArrivalDelay() {
        return this.arrivalDelay;
    }

    public void setArrivalDelay(int arrivalDelay) {
        this.arrivalDelay = arrivalDelay;
    }

    @JsonProperty("realTime")
    public boolean getRealTime() {
        return this.realTime;
    }

    public void setRealTime(boolean realTime) {
        this.realTime = realTime;
    }

    @JsonProperty("distance")
    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @JsonProperty("pathway")
    public boolean getPathway() {
        return this.pathway;
    }

    public void setPathway(boolean pathway) {
        this.pathway = pathway;
    }

    @JsonProperty("mode")
    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @JsonProperty("route")
    public String getRoute() {
        return this.route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @JsonProperty("agencyTimeZoneOffset")
    public int getAgencyTimeZoneOffset() {
        return this.agencyTimeZoneOffset;
    }

    public void setAgencyTimeZoneOffset(int agencyTimeZoneOffset) {
        this.agencyTimeZoneOffset = agencyTimeZoneOffset;
    }

    @JsonProperty("interlineWithPreviousLeg")
    public boolean getInterlineWithPreviousLeg() {
        return this.interlineWithPreviousLeg;
    }

    public void setInterlineWithPreviousLeg(boolean interlineWithPreviousLeg) {
        this.interlineWithPreviousLeg = interlineWithPreviousLeg;
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

    @JsonProperty("legGeometry")
    public LegGeometry getLegGeometry() {
        return this.legGeometry;
    }

    public void setLegGeometry(LegGeometry legGeometry) {
        this.legGeometry = legGeometry;
    }

    @JsonProperty("rentedBike")
    public boolean getRentedBike() {
        return this.rentedBike;
    }

    public void setRentedBike(boolean rentedBike) {
        this.rentedBike = rentedBike;
    }

    @JsonProperty("flexDrtAdvanceBookMin")
    public double getFlexDrtAdvanceBookMin() {
        return this.flexDrtAdvanceBookMin;
    }

    public void setFlexDrtAdvanceBookMin(double flexDrtAdvanceBookMin) {
        this.flexDrtAdvanceBookMin = flexDrtAdvanceBookMin;
    }

    @JsonProperty("duration")
    public double getDuration() {
        return this.duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @JsonProperty("transitLeg")
    public boolean getTransitLeg() {
        return this.transitLeg;
    }

    public void setTransitLeg(boolean transitLeg) {
        this.transitLeg = transitLeg;
    }

    @JsonProperty("intermediateStops")
    public List<IntermediateStop> getIntermediateStops() {
        return this.intermediateStops;
    }

    public void setIntermediateStops(List<IntermediateStop> intermediateStops) {
        this.intermediateStops = intermediateStops;
    }

    @JsonProperty("steps")
    public List<Step> getSteps() {
        return this.steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    @JsonProperty("agencyName")
    public String getAgencyName() {
        return this.agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    @JsonProperty("agencyUrl")
    public String getAgencyUrl() {
        return this.agencyUrl;
    }

    public void setAgencyUrl(String agencyUrl) {
        this.agencyUrl = agencyUrl;
    }

    @JsonProperty("routeType")
    public int getRouteType() {
        return this.routeType;
    }

    public void setRouteType(int routeType) {
        this.routeType = routeType;
    }

    @JsonProperty("routeId")
    public String getRouteId() {
        return this.routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    @JsonProperty("headsign")
    public String getHeadsign() {
        return this.headsign;
    }

    public void setHeadsign(String headsign) {
        this.headsign = headsign;
    }

    @JsonProperty("agencyId")
    public String getAgencyId() {
        return this.agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    @JsonProperty("tripId")
    public String getTripId() {
        return this.tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    @JsonProperty("serviceDate")
    public String getServiceDate() {
        return this.serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    @JsonProperty("routeShortName")
    public String getRouteShortName() {
        return this.routeShortName;
    }

    public void setRouteShortName(String routeShortName) {
        this.routeShortName = routeShortName;
    }

    //FOR OTP2.0
    @JsonProperty("alerts")
    public List<Alert> getAlerts() {
        return this.alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    @JsonProperty("routeColor")
    public String getRouteColor() {
        return routeColor;
    }

    public void setRouteColor(String routeColor) {
        this.routeColor = routeColor;
    }

    @JsonProperty("tripBlockId")
    public String getTripBlockId() {
        return tripBlockId;
    }

    public void setTripBlockId(String tripBlockId) {
        this.tripBlockId = tripBlockId;
    }

    @JsonProperty("routeTextColor")
    public String getRouteTextColor() {
        return routeTextColor;
    }

    public void setRouteTextColor(String routeTextColor) {
        this.routeTextColor = routeTextColor;
    }

    @JsonProperty("tripShortName")
    public String getTripShortName() {
        return tripShortName;
    }

    public void setTripShortName(String tripShortName) {
        this.tripShortName = tripShortName;
    }

    //----------------------------------------- Additional ------------------------------------------//
}
