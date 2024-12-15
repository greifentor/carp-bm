package de.ollie.carp.bm.persistence.mapper;

import java.util.List;

public interface DBOMapper<M, D> {
	D toDBO(M model);

	M toModel(D dbo);

	List<M> toModels(List<D> dbos);
}
