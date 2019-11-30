package com.loyalty.prueba.demo.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "infix",
        "postfix",
        "result"
})
public class ExpResponse {

    @JsonProperty("infix")
    private String infix;
    @JsonProperty("postfix")
    private String postfix;
    @JsonProperty("result")
    private Double result;
    private ExpResponse(){}
    @JsonProperty("infix")
    public String getInfix() {
        return infix;
    }

    @JsonProperty("infix")
    public void setInfix(String infix) {
        this.infix = infix;
    }

    @JsonProperty("postfix")
    public String getPostfix() {
        return postfix;
    }

    @JsonProperty("postfix")
    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    @JsonProperty("result")
    public Double getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(Double result) {
        this.result = result;
    }

    public static class Builder {

        private String infix;
        private String postfix;
        private Double result;

        public Builder(String infix) {
            this.infix = infix;
        }

        public Builder withPostfix(String postfix) {
            this.postfix = postfix;
            return this;
        }

        public Builder withResult(Double result) {
            this.result = result;
            return this;
        }

        public ExpResponse build() {
            ExpResponse resp = new ExpResponse();
            resp.infix = this.infix;
            resp.postfix = this.postfix;
            resp.result = this.result;
            return resp;
        }
    }
}