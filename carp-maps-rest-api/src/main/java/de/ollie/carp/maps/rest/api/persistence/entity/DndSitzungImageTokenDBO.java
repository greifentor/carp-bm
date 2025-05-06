package de.ollie.carp.maps.rest.api.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * A DBO for dnd_sitzung_image_tokens.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@Generated
@Entity(name = "DndSitzungImageToken")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "ID")
@Table(name = "DND_SITZUNG_IMAGE_TOKEN")
@ToString(callSuper = true)
public class DndSitzungImageTokenDBO extends SitzungImageDBO {

	@Column(name = "CURRENT_TP", nullable = false)
	private int currentTp;

	@Column(name = "INITIATIVE", nullable = false)
	private int initiative;
}
