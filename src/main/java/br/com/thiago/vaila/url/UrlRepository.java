package br.com.thiago.vaila.url;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.thiago.vaila.url.entity.UrlEntity;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

}
