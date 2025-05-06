package de.ollie.carp.maps.rest.api.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@Entity(name = "DndImageToken")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "ID")
@Table(name = "DND_IMAGE_TOKEN")
@ToString(callSuper = true)
public class DndImageTokenDBO extends ImageTokenDBO {

	@Column(name = "CURRENT_TP", nullable = false)
	private int currentTp;

	@Column(name = "GROUP_INITIATIVE", nullable = false)
	private boolean groupInitiative;

	@Column(name = "INITIATIVE_BONUS", nullable = false)
	private int initiativeBonus;

	@Column(name = "MAX_TP", nullable = false)
	private int maxTp;

	@Column(name = "SOURCE")
	private String source;

	@Column(name = "SPIELWERTE")
	private String spielwerte;

	@Column(name = "SUB_HEADER")
	private String subHeader;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "DND_IMAGE_TOKEN_ID")
	private List<DndImageTokenInfoDBO> dndImageTokenInfos;
}
