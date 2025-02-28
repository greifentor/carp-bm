package de.ollie.carp.bm.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@Entity(name = "BattleMapToken")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BATTLE_MAP_TOKEN")
public class BattleMapTokenDBO {

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id;

	@JoinColumn(name = "BATTLE_MAP", nullable = false, referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private BattleMapDBO battleMap;

	@JoinColumn(name = "TOKEN", nullable = false, referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private TokenDBO token;

	@Column(name = "FIELD_X", nullable = false)
	private BigDecimal fieldX;

	@Column(name = "FIELD_Y", nullable = false)
	private BigDecimal fieldY;
}
