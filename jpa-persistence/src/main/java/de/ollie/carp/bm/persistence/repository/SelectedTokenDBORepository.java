package de.ollie.carp.bm.persistence.repository;

import de.ollie.carp.bm.persistence.entity.SelectedTokenDBO;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedTokenDBORepository extends JpaRepository<SelectedTokenDBO, UUID> {
	@Query(value = "select st from SelectedToken st where st.battleMap.id = :id")
	Optional<SelectedTokenDBO> findByBattleMapId(UUID id);
}
