package de.ollie.carp.bm.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@Entity(name = "BattleMap")
@Table(name = "BATTLE_MAP")
public class BattleMapDBO {

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id;

	@Column(name = "FIELD_SIZE_IN_PIXELS", nullable = false)
	private int fieldSizeInPixels;

	@Column(name = "IMAGE", columnDefinition = "BLOB")
	private byte[] image;

	@Column(name = "NAME", nullable = false, unique = true)
	private String name;
}
