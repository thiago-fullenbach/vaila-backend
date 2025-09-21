package br.com.thiago.vaila.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.thiago.vaila.components.BaseUrlResolver;
import br.com.thiago.vaila.dto.url.UrlDTO;
import br.com.thiago.vaila.dto.url.UrlPageDTO;
import br.com.thiago.vaila.dto.url.mapper.UrlMapper;
import br.com.thiago.vaila.url.entity.UrlEntity;
import br.com.thiago.vaila.util.Base62ConversionUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    private UrlRepository mUrlRepository;

    @Autowired
    private UrlMapper mUrlMapper;

    @Autowired
    private BaseUrlResolver mBaserUrlResolver;

    @Override
    public String resolveOriginalUrl(String hash) throws EntityNotFoundException {
        log.debug("Called UrlService.resolveOriginalUrl(" + hash + ")");
        long id = Base62ConversionUtil.decode(hash);
        UrlEntity url = mUrlRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("URL not found for specified hash"));
        log.info(hash + " resolved for " + url.getOriginalUrl());
        return url.getOriginalUrl();
    }

    @Override
    public UrlPageDTO readUrls(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UrlEntity> urlPages = mUrlRepository.findAll(pageable);
        return new UrlPageDTO(
            urlPages.getContent().stream().map(entity -> mUrlMapper.urlEntityToUrlDTO(entity, mBaserUrlResolver.getBaseUrl())).toList(), 
            urlPages.getNumber(), 
            urlPages.getTotalPages(), 
            urlPages.getTotalElements());
    }

    @Override
    public UrlDTO createUrl(UrlDTO urlDTO) {
        log.debug("Called UrlService.createUrl(" + urlDTO + ")");
        UrlEntity savedUrlEntity = mUrlRepository.saveAndFlush(mUrlMapper.urlDTOToUrlEntity(urlDTO));
        return mUrlMapper.urlEntityToUrlDTO(savedUrlEntity, mBaserUrlResolver.getBaseUrl());
    }

    @Override
    public void deleteUrlByHash(String hash) {
        log.debug("Called UrlService.deleteUrlByHash(" + hash + ")");
        long id = Base62ConversionUtil.decode(hash);
        mUrlRepository.deleteById(id);
    }
}
