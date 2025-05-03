package de.ollie.carp.maps.rest.api.rest.v1.mapper;

import de.ollie.carp.maps.rest.api.core.model.Seite;
import de.ollie.carp.maps.rest.api.rest.v1.dto.SeiteDTO;

public interface DTOMapper<M, D> {
	D toDto(M model);

	default SeiteDTO<D> toDto(Seite<M> model) {
		return new SeiteDTO<>(
			model.getAnzahlDatensaetzeGesamt(),
			model.getAnzahlDatensaetzeProSeite(),
			model.getContent().stream().map(m -> toDto(m)).toList(),
			model.getSeitennummer()
		);
	}
}
