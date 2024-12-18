package de.ollie.carp.bm.persistence.repository;

import de.ollie.carp.bm.persistence.entity.SpielrundeTokenDBO;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpielrundeTokenDBORepository extends JpaRepository<SpielrundeTokenDBO, UUID> {}
