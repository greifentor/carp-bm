package de.ollie.carp.maps.client.v1.dto;

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
	private byte[] image;
}
