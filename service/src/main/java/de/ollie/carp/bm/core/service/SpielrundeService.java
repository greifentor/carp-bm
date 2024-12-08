package de.ollie.carp.bm.core.service;

import de.ollie.carp.bm.core.model.Spielrunde;
import java.util.Optional;
import java.util.UUID;

public interface SpielrundeService {
	Optional<Spielrunde> findById(UUID sitzungId);
}
