package de.ollie.carp.bm.persistence.mapper;

public interface DBOMapper<M, D> {
	D toDBO(M model);

	M toModel(D dbo);
}
