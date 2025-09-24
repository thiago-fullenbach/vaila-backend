package br.com.thiago.vaila.url;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.thiago.vaila.dto.url.UrlDTO;
import br.com.thiago.vaila.dto.url.UrlPageDTO;
import br.com.thiago.vaila.url.entity.UrlEntity;
import br.com.thiago.vaila.util.Base62ConversionUtil;

@SpringBootTest
@ActiveProfiles("test")
public class UrlServiceIntegrationTest {
    private static final String TEST_URL = "https://www.google.com/";
    private String urlHash;

    @Autowired
    private UrlService mUrlService;

    @Autowired
    private UrlRepository mUrlRepository;

    @BeforeEach
    void setup() {
        mUrlRepository.deleteAll();

        UrlDTO dto = new UrlDTO();
        dto.setOriginalUrl(TEST_URL);
        urlHash = mUrlService.createUrl(dto).getHash();
    }

    @Test
    void resolveOriginalUrl() throws Exception {
        String outputUrl = mUrlService.resolveOriginalUrl(urlHash);

        assertNotNull(outputUrl);
        assertEquals(TEST_URL, outputUrl);
    }

    @Test
    void readUrls() throws Exception {
        UrlPageDTO page = mUrlService.readUrls(0, 10);

        assertNotNull(page);
        assertEquals(page.getPage(), 0);
        assertEquals(page.getTotalPages(), 1);
        assertEquals(page.getTotalUrls(), 1);
        assertEquals(page.getUrls().size(), 1);
        assertEquals(page.getUrls().get(0).getOriginalUrl(), TEST_URL);
    }

    @Test
    void createUrl() throws Exception {
        UrlEntity urlEntity = mUrlRepository.findById(Base62ConversionUtil.decode(urlHash))
            .orElse(null);

        assertNotNull(urlHash);
        assertEquals(TEST_URL, urlEntity.getOriginalUrl());
    }

    @Test
    void deleteUrl() throws Exception {
        mUrlService.deleteUrlByHash(urlHash);
        UrlEntity urlEntity = mUrlRepository.findById(Base62ConversionUtil.decode(urlHash))
            .orElse(null);

        assertNull(urlEntity);
    }
}
