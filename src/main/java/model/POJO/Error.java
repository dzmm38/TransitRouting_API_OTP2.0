package model.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {
    private String id;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
