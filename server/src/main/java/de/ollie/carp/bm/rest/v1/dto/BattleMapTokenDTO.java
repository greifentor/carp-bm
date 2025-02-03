package de.ollie.carp.bm.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
	{ @Type(value = DnDBattleMapTokenDTO.class, name = "dnd"), @Type(value = BattleMapTokenDTO.class, name = "simple") }
)
public class BattleMapTokenDTO {

	private UUID id;
	private BattleMapDTO battleMap;
	private TokenDTO token;
	private BigDecimal fieldX;
	private BigDecimal fieldY;
}
