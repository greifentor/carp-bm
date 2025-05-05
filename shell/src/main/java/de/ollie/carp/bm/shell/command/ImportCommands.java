package de.ollie.carp.bm.shell.command;

import de.ollie.carp.bm.shell.ExceptionToStringMapper;
import de.ollie.carp.maps.client.v1.MapsTokenImportClient;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * @author ollie (16.12.2024)
 */
@ShellComponent
@RequiredArgsConstructor
public class ImportCommands {

	private final ExceptionToStringMapper exceptionMapper;
	private final MapsTokenImportClient mapsTokenImportClient;

	@ShellMethod(value = "Imports tokens from a Carp Maps REST Api.", key = { "import", "ip" })
	public String importTokens() {
		return mapsTokenImportClient.findAll(0, 20) + "\n;op";
	}
}
