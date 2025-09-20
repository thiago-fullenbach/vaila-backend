package br.com.thiago.vaila.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiago.vaila.dto.url.UrlDTO;
import br.com.thiago.vaila.url.entity.UrlEntity;
import br.com.thiago.vaila.util.Base62ConversionUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    private UrlRepository mUrlRepository;

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
    public UrlDTO createUrl(UrlDTO urlDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUrl'");
    }

    @Override
    public void deleteUrlById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUrlById'");
    }

    @Override
    public void deleteUrlByHash(String hash) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUrlByHash'");
    }

}
