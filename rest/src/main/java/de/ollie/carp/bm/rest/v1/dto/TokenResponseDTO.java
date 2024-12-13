package de.ollie.carp.bm.rest.v1.dto;

import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class TokenResponseDTO {

	private UUID id;
	private String name;
}
