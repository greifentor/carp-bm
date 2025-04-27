package de.ollie.carp.maps.rest.api.persistence.repository;

import de.ollie.carp.maps.rest.api.persistence.entity.TokenDBO;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenDBORepository extends JpaRepository<TokenDBO, UUID> {}
