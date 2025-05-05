package de.ollie.carp.maps.rest.api.persistence.repository;

import de.ollie.carp.maps.rest.api.persistence.entity.ImageTypeDBO;
import de.ollie.carp.maps.rest.api.persistence.entity.TokenDBO;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenDBORepository extends JpaRepository<TokenDBO, UUID> {
	Page<TokenDBO> findAllByImageTypeIs(ImageTypeDBO imageType, PageRequest pageRequest);
}
