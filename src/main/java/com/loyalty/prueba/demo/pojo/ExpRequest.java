
package com.loyalty.prueba.demo.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "exp"
})
public class ExpRequest {

    @JsonProperty("exp")
    private String exp;

    @JsonProperty("exp")
    public String getExp() {
        return exp;
    }

    @JsonProperty("exp")
    public void setExp(String exp) {
        this.exp = exp;
    }

}
