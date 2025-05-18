package de.ollie.carp.bm.lib.server.persistence.entity;

public interface DBOMapper<M, D> {
	M toModel(D dbo);
}
