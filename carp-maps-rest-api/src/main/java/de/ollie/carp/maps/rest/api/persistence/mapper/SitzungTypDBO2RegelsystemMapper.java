package de.ollie.carp.maps.rest.api.persistence.mapper;

import de.ollie.carp.maps.rest.api.core.model.Regelsystem;
import de.ollie.carp.maps.rest.api.persistence.entity.SitzungTypDBO;
import jakarta.inject.Named;
import java.util.Map;

@Named
public class SitzungTypDBO2RegelsystemMapper {

	Map<SitzungTypDBO, Regelsystem> valueMap = Map.of(
		SitzungTypDBO.DND,
		Regelsystem.DND,
		SitzungTypDBO.SPACE_1889,
		Regelsystem.SPACE_1889,
		SitzungTypDBO.STAR_WARS,
		Regelsystem.STAR_WARS
	);

	public Regelsystem map(SitzungTypDBO sitzungTyp) {
		if (sitzungTyp == null) {
			return null;
		}
		return valueMap.get(sitzungTyp);
	}
}
