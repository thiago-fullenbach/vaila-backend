package br.com.thiago.vaila.url;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.vaila.dto.url.UrlDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/url")
public class UrlController {
    @Autowired
    private UrlService mUrlService;

    @GetMapping("/{hash}")
    public ResponseEntity<Void> redirectByHash(@PathVariable String hash) {
        mUrlService.redirectByHash(hash);
        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT.value()).build();
    }

    @PostMapping
    public ResponseEntity<UrlDTO> createUrl(@RequestBody UrlDTO urlDTO) {
        return ResponseEntity.ok(mUrlService.createUrl(urlDTO));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUrlById(@PathVariable Long id) {
        mUrlService.deleteUrlById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{hash}")
    public ResponseEntity<Void> deleteUrlById(@PathVariable String hash) {
        mUrlService.deleteUrlByHash(hash);
        return ResponseEntity.noContent().build();
    }
}
