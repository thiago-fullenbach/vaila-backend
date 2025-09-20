package br.com.thiago.vaila.dto.url;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UrlDTO {
    @JsonProperty
    private Long id;

    @URL(message = "Invalid URL")
    @NotNull(message = "The original URL must not be null")
    @NotBlank(message = "The original URL must not be blank")
    @JsonProperty(required = true)
    private String originalUrl;
}
