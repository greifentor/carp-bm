package de.ollie.carp.bm.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@Entity(name = "SelectedToken")
@Table(name = "SELECTED_TOKEN")
public class SelectedTokenDBO {

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id;

	@JoinColumn(name = "BATTLE_MAP", nullable = false, referencedColumnName = "ID", unique = true)
	@OneToOne(orphanRemoval = true)
	private BattleMapDBO battleMap;

	@JoinColumn(name = "TOKEN", nullable = false, referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private TokenDBO token;
}
