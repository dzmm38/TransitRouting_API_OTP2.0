package model.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A POJO Class defining the Root Class from OpenTripPlanner
 * read the Root from information from the response Object of OTP
 */
public class Root {
    //------------------------------------------ Variable -------------------------------------------//
    private DebugOutput debugOutput;                //Changed for OTP2 Version
    private RequestParameters requestParameters;
    private Plan plan;
    private ElevationMetadata elevationMetadata;

    //Added for OTP2 Version
    private Metadata metadata;
    private Error error;

    //----------------------------------------- Constructor -----------------------------------------//
    public Root() {
        //For JSON
    }

    //------------------------------------------- Methods -------------------------------------------//
    //--------------------------------------- Getter & Setter ---------------------------------------//
    @JsonProperty("debugOutput")
    public DebugOutput getDebugOutput() {
        return this.debugOutput;
    }

    public void setDebugOutput(DebugOutput debugOutput) {
        this.debugOutput = debugOutput;
    }

    @JsonProperty("requestParameters")
    public RequestParameters getRequestParameters() {
        return this.requestParameters;
    }

    public void setRequestParameters(RequestParameters requestParameters) {
        this.requestParameters = requestParameters;
    }

    @JsonProperty("plan")
    public Plan getPlan() {
        return this.plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @JsonProperty("elevationMetadata")
    public ElevationMetadata getElevationMetadata() {
        return this.elevationMetadata;
    }

    public void setElevationMetadata(ElevationMetadata elevationMetadata) {
        this.elevationMetadata = elevationMetadata;
    }

    //Added for OTP2 Version
    @JsonProperty("metadata")
    public Metadata getMetadata() {
        return this.metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    @JsonProperty("error")
    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    //----------------------------------------- Additional ------------------------------------------//
}
