package de.ollie.carp.maps.rest.api.rest.v1;

import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class TokenDTO {

	private UUID id;
	private String name;
	private byte[] icon;
}
