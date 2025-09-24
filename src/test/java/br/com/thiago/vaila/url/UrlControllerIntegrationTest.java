package br.com.thiago.vaila.url;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import br.com.thiago.vaila.dto.url.UrlDTO;
import br.com.thiago.vaila.util.Base62ConversionUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.LinkedHashMap;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UrlControllerIntegrationTest {
    private static final String TEST_URL = "https://www.google.com/";

    @Autowired
    private MockMvc mMockMvc;

    @Autowired
    private UrlService mUrlService;

    @Test
    void redirectUrl() throws Exception {
        UrlDTO inputUrl = new UrlDTO();
        inputUrl.setOriginalUrl(TEST_URL);
        Long id = Base62ConversionUtil.decode(mUrlService.createUrl(inputUrl).getHash());

        mMockMvc.perform(get("/" + id))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl(TEST_URL));
    }

    @Test
    void readUrls() throws Exception {
        mMockMvc.perform(get("/url"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", isA(LinkedHashMap.class)));
    }

    @Test
    void createUrl() throws Exception {
        String json = """
            {"originalUrl": "%s"}
        """.formatted(TEST_URL);

        mMockMvc.perform(post("/url")
            .contentType("application/json")
            .content(json))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.originalUrl").value(TEST_URL));
    }

    @Test
    void deleteUrl() throws Exception {
        UrlDTO inputUrl = new UrlDTO();
        inputUrl.setOriginalUrl(TEST_URL);
        String hash = mUrlService.createUrl(inputUrl).getHash();

        mMockMvc.perform(delete("/url/" + hash))
            .andExpect(status().isNoContent());
    }
}
