package br.com.thiago.vaila.dto.url;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlPageDTO {
    @JsonProperty
    private List<UrlDTO> urls;

    @JsonProperty
    private int page;

    @JsonProperty
    private int totalPages;

    @JsonProperty
    private long totalUrls;
}
