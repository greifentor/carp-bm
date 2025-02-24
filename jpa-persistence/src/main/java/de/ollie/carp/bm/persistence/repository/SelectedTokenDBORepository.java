package de.ollie.carp.bm.persistence.repository;

import de.ollie.carp.bm.persistence.entity.BattleMapDBO;
import de.ollie.carp.bm.persistence.entity.SelectedTokenDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedTokenDBORepository extends JpaRepository<SelectedTokenDBO, BattleMapDBO> {}
