package model.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {
    private String id;
    private String msg;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("msg")
        public String getMsg(){
            return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }
}
