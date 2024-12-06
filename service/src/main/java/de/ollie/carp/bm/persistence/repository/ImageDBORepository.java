package de.ollie.carp.bm.persistence.repository;

import de.ollie.carp.bm.persistence.entity.ImageDBO;
import de.ollie.carp.bm.persistence.entity.ImageTypeDBO;
import de.ollie.carp.bm.persistence.entity.SitzungTypDBO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDBORepository extends JpaRepository<ImageDBO, Long> {
	Optional<ImageDBO> findByGlobalId(String globalId);

	List<ImageDBO> findAllByImageType(ImageTypeDBO imageType);

	List<ImageDBO> findAllBySitzungTyp(SitzungTypDBO sitzungTyp);
}
