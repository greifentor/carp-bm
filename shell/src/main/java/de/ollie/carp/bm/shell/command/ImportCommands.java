package de.ollie.carp.bm.shell.command;

import de.ollie.carp.bm.client.v1.TokenClient;
import de.ollie.carp.bm.shell.ExceptionToStringMapper;
import de.ollie.carp.bm.shell.command.mapper.TokenDTOMapper;
import de.ollie.carp.maps.client.v1.MapsTokenImportClient;
import de.ollie.carp.maps.client.v1.dto.SeiteDTO;
import de.ollie.carp.maps.client.v1.dto.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ImportCommands {

	private final ExceptionToStringMapper exceptionMapper;
	private final MapsTokenImportClient mapsTokenImportClient;
	private final TokenClient tokenClient;
	private final TokenDTOMapper tokenDTOMapper;

	@ShellMethod(value = "Imports tokens from a Carp Maps REST Api.", key = { "import", "ip" })
	public String importTokens() {
		Counter imported = new Counter();
		int pageNumber = 0;
		SeiteDTO<TokenDTO> page = mapsTokenImportClient.findAll(pageNumber, 20);
		page.getContent().forEach(dto -> updateOrCreate(dto, imported));
		while (page.getContent().size() == page.getAnzahlDatensaetzeProSeite()) {
			page = mapsTokenImportClient.findAll(++pageNumber, 20);
			page.getContent().forEach(dto -> updateOrCreate(dto, imported));
		}
		return imported.getValue() + " record(s) imported";
	}

	private void updateOrCreate(TokenDTO token, Counter imported) {
		if (token.getDndData() != null) {
			tokenClient.updateOrCreate(tokenDTOMapper.toDto(token));
			imported.inc();
		}
	}
}
