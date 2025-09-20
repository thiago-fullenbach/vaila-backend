package br.com.thiago.vaila.url;

import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.vaila.dto.url.UrlDTO;
import jakarta.validation.Valid;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class UrlController {
    @Autowired
    private UrlService mUrlService;

    @GetMapping("/{hash}")
    public ResponseEntity<Void> redirectByHash(@PathVariable String hash) {
        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT.value())
            .location(URI.create(mUrlService.resolveOriginalUrl(hash)))
            .build();
    }

    @PostMapping("/url")
    public ResponseEntity<UrlDTO> createUrl(@Valid @RequestBody UrlDTO urlDTO) {
        return ResponseEntity.ok(mUrlService.createUrl(urlDTO));
    }
    
    @DeleteMapping("/url/{id}")
    public ResponseEntity<Void> deleteUrlById(@PathVariable Long id) {
        mUrlService.deleteUrlById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/url/{hash}")
    public ResponseEntity<Void> deleteUrlById(@PathVariable String hash) {
        mUrlService.deleteUrlByHash(hash);
        return ResponseEntity.noContent().build();
    }
}
