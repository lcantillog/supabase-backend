package com.pkg.spring.msvc.msvcsupabase.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Generated;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data",
        "created_at",
        "icon_url",
        "id",
        "updated_at",
        "url",
        "value"
})
@Generated("jsonschema2pojo")
public class Respuestas {
    @JsonProperty("data")
    private Object data;
    @JsonProperty("status")
    private int status;
    @JsonProperty("msg")
    private String msg;

    public Respuestas(String msg, HttpStatus httpStatus) {
        this.data= "";
        this.msg = msg;
        this.status = httpStatus.value();
    }

    public Respuestas(Object object, String msg, HttpStatus httpStatus) {
        this.data = object;
        this.msg = msg;
        this.status = httpStatus.value();
    }
}
