package de.ollie.carp.bm.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity(name = "DndToken")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "ID")
@Table(name = "DND_TOKEN")
@ToString(callSuper = true)
public class DnDTokenDBO extends TokenDBO {

	@Column(name = "INITIATIVE_BONUS", nullable = false)
	private int initiativeBonus;

	@Column(name = "RK", nullable = false)
	private int rk;

	@Column(name = "TP_MAXIMUM", nullable = false)
	private int tpMaximum;

	@Column(name = "TOKEN_SIZE", nullable = false)
	private DnDTokenSizeDBO tokenSize;
}
