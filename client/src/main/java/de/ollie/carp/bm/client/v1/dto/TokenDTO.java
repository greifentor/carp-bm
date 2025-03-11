package de.ollie.carp.bm.client.v1.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = DnDTokenDTO.class, name = "dnd"), @Type(value = TokenDTO.class, name = "simple") })
@ToString(exclude = { "image" })
public class TokenDTO {

	private UUID id;
	private byte[] image;
	private String name;
	private ShapeTypeDTO shapeType;
}
