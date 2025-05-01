package de.ollie.carp.maps.rest.api.persistence.mapper;

import static de.ollie.carp.bm.util.Check.ensure;

import de.ollie.carp.maps.rest.api.core.model.Seite;
import jakarta.inject.Named;
import org.springframework.data.domain.Page;

@Named
public class PageDBOMapper<M, D> {

	public Seite<M> toModel(Page<D> dbo, DBOMapper<M, D> mapper) {
		ensure(dbo != null, "dbo cannot be null!");
		ensure(mapper != null, "mapper cannot be null!");
		return new Seite<>(
			dbo.getTotalElements(),
			dbo.getPageable().getPageSize(),
			dbo.getContent().stream().map(mapper::toModel).toList(),
			dbo.getNumber()
		);
	}
}
