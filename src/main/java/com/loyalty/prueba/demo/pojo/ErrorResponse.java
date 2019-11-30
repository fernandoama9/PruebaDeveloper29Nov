package com.loyalty.prueba.demo.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "message"
})
public class ErrorResponse {

    @JsonProperty("message")
    private String message;
    private ErrorResponse(){}
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    public static class Builder {

        private String message;

        public Builder(String message) {
            this.message = message;
        }

        public ErrorResponse build() {
            ErrorResponse resp = new ErrorResponse();
            resp.message = this.message;
            return resp;
        }
    }

}