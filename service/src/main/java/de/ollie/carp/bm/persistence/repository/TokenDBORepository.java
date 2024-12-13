package de.ollie.carp.bm.persistence.repository;

import de.ollie.carp.bm.persistence.entity.TokenDBO;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenDBORepository extends JpaRepository<TokenDBO, UUID> {
	Optional<TokenDBO> findByName(String name);
}
