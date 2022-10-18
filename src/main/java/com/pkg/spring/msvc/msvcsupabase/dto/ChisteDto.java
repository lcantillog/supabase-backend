package com.pkg.spring.msvc.msvcsupabase.dto;

import javax.annotation.Generated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "idChuckNorris",
        "iconoUrl",
        "urlChuckNorris",
        "valorChuckNorris"
})
@Generated("jsonschema2pojo")
@Builder
public class ChisteDto {

    @NotBlank
    @JsonProperty("idChuckNorris")
    private String idChuckNorris;
    @NotBlank
    @JsonProperty("iconoUrl")
    private String iconoUrl;
    @NotBlank
    @JsonProperty("urlChuckNorris")
    private String urlChuckNorris;
    @NotBlank
    @JsonProperty("valorChuckNorris")
    private String valorChuckNorris;

    @JsonProperty("idChuckNorris")
    public String getIdChuckNorris() {
        return idChuckNorris;
    }

    @JsonProperty("idChuckNorris")
    public void setIdChuckNorris(String idChuckNorris) {
        this.idChuckNorris = idChuckNorris;
    }

    @JsonProperty("iconoUrl")
    public String getIconoUrl() {
        return iconoUrl;
    }

    @JsonProperty("iconoUrl")
    public void setIconoUrl(String iconoUrl) {
        this.iconoUrl = iconoUrl;
    }

    @JsonProperty("urlChuckNorris")
    public String getUrlChuckNorris() {
        return urlChuckNorris;
    }

    @JsonProperty("urlChuckNorris")
    public void setUrlChuckNorris(String urlChuckNorris) {
        this.urlChuckNorris = urlChuckNorris;
    }

    @JsonProperty("valorChuckNorris")
    public String getValorChuckNorris() {
        return valorChuckNorris;
    }

    @JsonProperty("valorChuckNorris")
    public void setValorChuckNorris(String valorChuckNorris) {
        this.valorChuckNorris = valorChuckNorris;
    }
}
