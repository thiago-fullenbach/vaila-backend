package br.com.thiago.vaila.url;

import br.com.thiago.vaila.dto.url.UrlDTO;

/**
 * UrlService for communication between controller and repository.
 * Business logic must be written in the implementation of the service.
 */
public interface UrlService {
    /**
     * Method for redirecting short URL to the original URL based on the generated
     * short URL hash, if it exists.
     * 
     * @param hash Generated short URL hash
     */
    public void redirectByHash(String hash);

    /**
     * Method for creating a short URL, based on the original URL, if it's valid.
     * 
     * @param urlDTO URL Data Transfer Object
     * @return created short URL Data Transfer Object
     */
    public UrlDTO createUrl(UrlDTO urlDTO);

    /**
     * Method for deleting a short URL by id, if it exists.
     * 
     * @param id short URL id
     */
    public void deleteUrlById(Long id);

    /**
     * Method for deleting a short URL by hash, if it exists.
     * 
     * @param hash
     */
    public void deleteUrlByHash(String hash);
}
