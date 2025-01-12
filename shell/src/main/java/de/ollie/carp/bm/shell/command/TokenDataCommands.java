package de.ollie.carp.bm.shell.command;

import de.ollie.carp.bm.client.TokenClient;
import de.ollie.carp.bm.client.TokenDataClient;
import de.ollie.carp.bm.core.model.TokenData;
import de.ollie.carp.bm.shell.ExceptionToStringMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * @author ollie (12.01.2025)
 */
@ShellComponent
@RequiredArgsConstructor
public class TokenDataCommands {

	private final ExceptionToStringMapper exceptionMapper;
	private final TokenClient tokenClient;
	private final TokenDataClient tokenDataClient;

	@ShellMethod(value = "Adds a new token data.", key = { "add-token-data", "atd" })
	public String add(
		@ShellOption(
			help = "Name or id of the token which the token data are related to.",
			value = "nameOrId"
		) String idOrName,
		@ShellOption(help = "The maximum hit points for the token data", value = "maximumTp") int maximumTp,
		@ShellOption(help = "The armour class for the token data", value = "rk") int rk
	) {
		try {
			return tokenDataClient.createDnDTokenData(tokenClient.getByIdOrName(idOrName), maximumTp, rk).toString();
		} catch (Exception e) {
			return exceptionMapper.map(e);
		}
	}

	@ShellMethod(value = "Lists all stored token data.", key = { "list-token-data", "ltd" })
	public String list() {
		return tokenDataClient
			.findAllTokenData()
			.stream()
			.map(TokenData::toString)
			.reduce((t0, t1) -> t0 + "\n" + t1)
			.orElse("");
	}

	@ShellMethod(value = "Deletes the token data by id.", key = { "delete-token-data", "dtd" })
	public String delete(@ShellOption(help = "Id of the token data.", value = "id") UUID id) {
		try {
			return "token data deleted with id: " + tokenDataClient.delete(id).toString();
		} catch (Exception e) {
			return exceptionMapper.map(e);
		}
	}
}
