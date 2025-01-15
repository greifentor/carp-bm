package de.ollie.carp.bm.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
	{ @Type(value = DnDTokenDataDTO.class, name = "dnd"), @Type(value = TokenDataDTO.class, name = "simple") }
)
public class TokenDataDTO {

	private UUID id;
	private TokenDTO token;
}
