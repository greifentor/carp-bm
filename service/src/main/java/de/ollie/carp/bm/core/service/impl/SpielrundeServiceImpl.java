package de.ollie.carp.bm.core.service.impl;

import de.ollie.carp.bm.core.model.Spielrunde;
import de.ollie.carp.bm.core.service.SpielrundeService;
import jakarta.inject.Named;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;

@Named
@ComponentScan("de.ollie.carp.bm")
@RequiredArgsConstructor
public class SpielrundeServiceImpl implements SpielrundeService {

	@Override
	public Optional<Spielrunde> findById(UUID tokenId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}
