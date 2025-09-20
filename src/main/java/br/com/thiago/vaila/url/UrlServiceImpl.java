package br.com.thiago.vaila.url;

import org.springframework.stereotype.Service;

import br.com.thiago.vaila.dto.url.UrlDTO;

@Service
public class UrlServiceImpl implements UrlService {

    @Override
    public void redirectByHash(String hash) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'redirectByHash'");
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
