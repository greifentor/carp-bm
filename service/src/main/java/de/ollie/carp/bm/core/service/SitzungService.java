package de.ollie.carp.bm.core.service;

import de.ollie.carp.bm.core.model.Spielrunde;
import java.util.Optional;
import java.util.UUID;

public interface SitzungService {
	Optional<Spielrunde> findById(UUID sitzungId);
}
