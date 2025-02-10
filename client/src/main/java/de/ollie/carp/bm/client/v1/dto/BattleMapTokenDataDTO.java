package de.ollie.carp.bm.client.v1.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
	{
		@Type(value = DnDBattleMapTokenDataDTO.class, name = "dnd"),
		@Type(value = BattleMapTokenDataDTO.class, name = "simple"),
	}
)
public class BattleMapTokenDataDTO {

	private CoordinatesDTO coordinates;
}
