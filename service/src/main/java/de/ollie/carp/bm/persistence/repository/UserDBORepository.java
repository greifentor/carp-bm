package de.ollie.carp.bm.persistence.repository;

import de.ollie.carp.bm.persistence.entity.UserDBO;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDBORepository extends JpaRepository<UserDBO, Long> {
	Optional<UserDBO> findByGlobalId(String globalId);
}
