package de.ollie.carp.bm.shell.command;

import de.ollie.carp.bm.client.TokenClient;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.shell.ExceptionToStringMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * @author ollie (16.12.2024)
 */
@ShellComponent
@RequiredArgsConstructor
public class TokenCommands {

	private final ExceptionToStringMapper exceptionMapper;
	private final TokenClient tokenClient;

	@ShellMethod(value = "Adds a new token.", key = { "add-token", "at" })
	public String add(@ShellOption(help = "Name of the token.", value = "name") String name) {
		try {
			return tokenClient.createTokenWithName(name).toString();
		} catch (Exception e) {
			return exceptionMapper.map(e);
		}
	}

	@ShellMethod(value = "Lists all stored tokens.", key = { "list-tokens", "lt" })
	public String list() {
		return tokenClient.findAllTokens().stream().map(Token::toString).reduce((t0, t1) -> t0 + "\n" + t1).orElse("");
	}
}
