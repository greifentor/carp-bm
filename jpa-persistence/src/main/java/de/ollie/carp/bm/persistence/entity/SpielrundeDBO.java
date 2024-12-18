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
@Entity(name = "Spielrunde")
@Table(name = "SPIELRUNDE")
public class SpielrundeDBO {

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id;
}
