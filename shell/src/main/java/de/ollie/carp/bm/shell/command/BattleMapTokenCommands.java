package de.ollie.carp.bm.shell.command;

import de.ollie.carp.bm.client.TokenClient;
import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.shell.ExceptionToStringMapper;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * @author ollie (21.12.2024)
 */
@ShellComponent
@RequiredArgsConstructor
public class BattleMapTokenCommands {

	private final ExceptionToStringMapper exceptionMapper;
	private final TokenClient client;

	@ShellMethod(value = "Adds a token to a battle map.", key = { "add-token-to-battle-map", "atbm" })
	public String add(
		@ShellOption(
			help = "Id or name of the token to place on the battle map.",
			value = "tokenIdOrName"
		) String tokenIdOrName,
		@ShellOption(help = "Id or name of the battle map.", value = "battleMapIdOrName") String battleMapIdOrName,
		@ShellOption(help = "X field coordinate where the token is to place.", value = "x") BigDecimal x,
		@ShellOption(help = "Y field coordinate where the token is to place.", value = "y") BigDecimal y
	) {
		try {
			return client.setTokenToBattleMapOfSpielrunde(
				tokenIdOrName,
				battleMapIdOrName,
				new Coordinates().setFieldX(x).setFieldY(y)
			);
		} catch (Exception e) {
			return exceptionMapper.map(e);
		}
	}

	@ShellMethod(value = "Lists all stored battle maps for the .", key = { "list-battle-maps", "lbm" })
	public String list(
		@ShellOption(
			help = "Id or name of the battle map whose token should be listed.",
			value = "battleMapIdOrName"
		) String battleMapIdOrName
	) {
		try {
			return client
				.findAllByBattleMap(battleMapIdOrName)
				.stream()
				.map(BattleMapToken::toString)
				.reduce((s0, s1) -> s0 + "\n" + s1)
				.orElse("no battle map tokens found for: " + battleMapIdOrName);
		} catch (Exception e) {
			return exceptionMapper.map(e);
		}
	}

	@ShellMethod(value = "Deletes the battle map by UUID or name.", key = { "delete-battle-map", "dbm" })
	public String delete(@ShellOption(help = "UUID or name of the battle map.", value = "uuidOrName") String uuidOrName) {
		try {
			return "battle map deleted with id: " + client.delete(uuidOrName).toString();
		} catch (Exception e) {
			return exceptionMapper.map(e);
		}
	}
}
