package de.ollie.carp.bm.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@Entity(name = "DndBattleMapToken")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "ID")
@Table(name = "DND_BATTLE_MAP_TOKEN")
@ToString(callSuper = true)
public class DnDBattleMapTokenDBO extends BattleMapTokenDBO {

	@Column(name = "RK_CURRENT", nullable = false)
	private int rkCurrent;

	@Column(name = "TP_CURRENT", nullable = false)
	private int tpCurrent;

	@JoinColumn(name = "DND_TOKEN", nullable = false, referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private DnDTokenDBO dndToken;
}
