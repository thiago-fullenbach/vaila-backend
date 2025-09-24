package br.com.thiago.vaila.url;

import br.com.thiago.vaila.dto.url.UrlDTO;
import br.com.thiago.vaila.dto.url.UrlPageDTO;
import jakarta.persistence.EntityNotFoundException;

/**
 * UrlService for communication between controller and repository.
 * Business logic must be written in the implementation of the service.
 */
public interface UrlService {
    /**
     * Method for resolving the hash into the original URL.
     * Returns the original URL, if it exists, throws a EntityNotFoundException otherwise.
     * 
     * @param hash Generated short URL hash
     * @return original URL, found by the specified hash
     * @throws EntityNotFoundException if the URL with the specified hash is not found
     */
    public String resolveOriginalUrl(String hash) throws EntityNotFoundException;

    /**
     * Method for listing saved URLs in pages.
     * 
     * @param page page number
     * @param size elements by page
     * @return a page of URL Data Transfer Objects
     */
    public UrlPageDTO readUrls(int page, int size);

    /**
     * Method for creating a short URL, based on the original URL, if it's valid.
     * 
     * @param urlDTO URL Data Transfer Object
     * @return created short URL Data Transfer Object
     */
    public UrlDTO createUrl(UrlDTO urlDTO);

    /**
     * Method for deleting a short URL by hash, if it exists.
     * 
     * @param hash short URL hash
     */
    public void deleteUrlByHash(String hash);
}
