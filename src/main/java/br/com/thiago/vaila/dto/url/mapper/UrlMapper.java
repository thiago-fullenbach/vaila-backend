package br.com.thiago.vaila.dto.url.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.thiago.vaila.dto.url.UrlDTO;
import br.com.thiago.vaila.url.entity.UrlEntity;

@Mapper(componentModel = "spring")
public interface UrlMapper {
    UrlDTO urlEntityToUrlDTO(UrlEntity urlEntity);

    @Mapping(target = "withId", ignore = true)
    @Mapping(target = "withOriginalUrl", ignore = true)
    UrlEntity urlDTOToUrlEntity(UrlDTO urlDTO);
}
