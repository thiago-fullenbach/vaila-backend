package br.com.thiago.vaila.url;

import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.vaila.dto.url.UrlDTO;
import br.com.thiago.vaila.dto.url.UrlPageDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@Validated
public class UrlController {
    @Autowired
    private UrlService mUrlService;

    @GetMapping("/{hash}")
    public ResponseEntity<Void> redirectByHash(@PathVariable @NotBlank String hash) {
        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT.value())
            .location(URI.create(mUrlService.resolveOriginalUrl(hash)))
            .build();
    }

    @GetMapping("/url")
    public ResponseEntity<UrlPageDTO> readUrls(
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(mUrlService.readUrls(page, size));
    }
    

    @PostMapping("/url")
    public ResponseEntity<UrlDTO> createUrl(@RequestBody @Valid UrlDTO urlDTO) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(mUrlService.createUrl(urlDTO));
    }
    
    @DeleteMapping("/url/{hash}")
    public ResponseEntity<Void> deleteUrlByHash(@PathVariable @NotBlank String hash) {
        mUrlService.deleteUrlByHash(hash);
        return ResponseEntity.noContent().build();
    }
}
