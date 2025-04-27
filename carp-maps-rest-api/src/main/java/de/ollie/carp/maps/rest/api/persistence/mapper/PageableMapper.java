package de.ollie.carp.maps.rest.api.persistence.mapper;

import static de.ollie.carp.bm.util.Check.ensure;

import de.ollie.carp.maps.rest.api.service.model.SeitenParameter;
import de.ollie.carp.maps.rest.api.service.model.SeitenParameter.Richtung;
import de.ollie.carp.maps.rest.api.service.model.SeitenParameter.Sortierung;
import jakarta.inject.Named;
import java.util.Arrays;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

@Named
public class PageableMapper {

	public PageRequest toDbo(SeitenParameter seitenParameter) {
		ensure(seitenParameter != null, "seiten parameter cannot be null!");
		return PageRequest.of(
			seitenParameter.getSeitennummer(),
			seitenParameter.getSeitengroesse(),
			mapSortierungen(seitenParameter.getSortierung())
		);
	}

	private Sort mapSortierungen(Sortierung[] sortierung) {
		return Sort.by(Arrays.asList(sortierung).stream().map(this::mapSortierung).toList());
	}

	private Order mapSortierung(Sortierung sortierung) {
		return sortierung.getRichtung() == Richtung.ABSTEIGEND
			? Order.desc(sortierung.getPropertyName())
			: Order.asc(sortierung.getPropertyName());
	}
}
