package de.ollie.carp.maps.rest.api.persistence.repository;

import de.ollie.carp.maps.rest.api.persistence.entity.ImageDBO;
import de.ollie.carp.maps.rest.api.persistence.entity.ImageTypeDBO;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDBORepository extends JpaRepository<ImageDBO, UUID> {
	Page<ImageDBO> findAllByImageTypeIs(ImageTypeDBO imageType, PageRequest pageRequest);
}
