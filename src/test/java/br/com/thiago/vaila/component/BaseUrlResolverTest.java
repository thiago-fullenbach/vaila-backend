package br.com.thiago.vaila.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.assertj.core.api.Assertions.*;

import jakarta.servlet.http.HttpServletRequest;

public class BaseUrlResolverTest {
    @AfterEach
    void cleanup() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    void deveRetornarBaseUrlCorretamente() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getScheme()).thenReturn("http");
        Mockito.when(request.getServerName()).thenReturn("localhost");
        Mockito.when(request.getServerPort()).thenReturn(8080);

        ServletRequestAttributes attrs = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attrs);

        BaseUrlResolver resolver = new BaseUrlResolver();
        String baseUrl = resolver.getBaseUrl();

        assertThat(baseUrl).isEqualTo("http://localhost:8080");
    }

    @Test
    void deveLancarExcecaoSeNaoHouverRequest() {
        BaseUrlResolver resolver = new BaseUrlResolver();

        assertThatThrownBy(resolver::getBaseUrl)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("No active HTTP requests");
    }
}
