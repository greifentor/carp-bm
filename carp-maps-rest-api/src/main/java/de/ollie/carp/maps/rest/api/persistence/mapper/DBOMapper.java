package de.ollie.carp.maps.rest.api.persistence.mapper;

public interface DBOMapper<M, D> {
	D toDbo(M model);

	M toModel(D dbo);
}
