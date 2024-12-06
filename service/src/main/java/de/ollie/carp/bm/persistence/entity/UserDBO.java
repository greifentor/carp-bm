package de.ollie.carp.bm.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@Entity(name = "User")
@Table(name = "USER")
public class UserDBO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private long id;

	@Column(name = "CURRENT_SITZUNG")
	private Long currentSitzung;

	@Column(name = "GLOBAL_ID")
	private String globalId;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "TOKEN", nullable = false)
	private String token;
}
