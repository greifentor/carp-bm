package de.ollie.carp.bm.persistence.factory;

import de.ollie.carp.bm.persistence.entity.TokenDBO;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenDBOFactory {

	private final UUIDFactory uuidFactory;

	public TokenDBO createWithName(String name) {
		return new TokenDBO().setId(uuidFactory.create()).setName(name);
	}
}
