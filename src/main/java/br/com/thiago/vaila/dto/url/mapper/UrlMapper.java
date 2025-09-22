package br.com.thiago.vaila.dto.url.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import br.com.thiago.vaila.dto.url.UrlDTO;
import br.com.thiago.vaila.url.entity.UrlEntity;
import br.com.thiago.vaila.util.Base62ConversionUtil;

@Mapper(componentModel = "spring")
public interface UrlMapper {
    @Mapping(target = "hash", source = "id", qualifiedByName = "idToHash")
    @Mapping(target = "shortUrl", source = "urlEntity", qualifiedByName = "toShortUrl")
    UrlDTO urlEntityToUrlDTO(UrlEntity urlEntity, @Context String baseUrl);

    @Mapping(target = "id", source = "hash", qualifiedByName = "hashToId")
    UrlEntity urlDTOToUrlEntity(UrlDTO urlDTO);

    @Named("hashToId")
    default Long hashToId(String hash) {
        if(hash != null && !hash.isEmpty())
            return Base62ConversionUtil.decode(hash);
        
        return null;
    }

    @Named("idToHash")
    default String idToHash(Long id) {
        if(id != null)
            return Base62ConversionUtil.encode(id);
        
        return null;
    }

    @Named("toShortUrl")
    default String toShortUrl(UrlEntity urlEntity, @Context String baseUrl) {
        if(urlEntity == null || urlEntity.getId() == null) return null;
        return baseUrl + "/" + idToHash(urlEntity.getId());
    }
}
