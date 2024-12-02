package de.ollie.carp.bm.resource;

import de.ollie.carp.bm.core.model.Localization;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourceFileAdapterConfiguration {

	static final String LOCALIZATION_WILDCARD = "%LOCALIZATION%";

	@Getter
	@Value("${localization.resource.enabled:true}")
	private boolean enabled;

	@Value(
		"${localization.resource.file.name.pattern:localization/carp_bm_resources" + LOCALIZATION_WILDCARD + ".properties}"
	)
	private String fileNamePattern;

	public String getResourceFileName(Localization localization) {
		String value = (localization != null) ? "_" + localization.name().toLowerCase() : "";
		return fileNamePattern.replace(LOCALIZATION_WILDCARD, value);
	}
}
