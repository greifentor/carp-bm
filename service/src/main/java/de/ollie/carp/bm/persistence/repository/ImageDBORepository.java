package de.ollie.carp.bm.persistence.repository;

import de.ollie.carp.bm.persistence.entity.ImageDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDBORepository extends JpaRepository<ImageDBO, Long> {}
