package de.ollie.carp.maps.rest.api.persistence.mapper;

public interface DBOMapper<M, D> {
	M toModel(D dbo);
}
