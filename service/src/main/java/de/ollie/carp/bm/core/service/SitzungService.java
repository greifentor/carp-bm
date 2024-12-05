package de.ollie.carp.bm.core.service;

import de.ollie.carp.bm.core.model.Sitzung;
import java.util.Optional;
import java.util.UUID;

public interface SitzungService {
	Optional<Sitzung> findById(UUID sitzungId);
}
