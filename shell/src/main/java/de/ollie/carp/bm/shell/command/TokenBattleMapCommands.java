package de.ollie.carp.bm.shell.command;

import de.ollie.carp.bm.client.TokenClient;
import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.shell.ExceptionToStringMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * @author ollie (21.12.2024)
 */
@ShellComponent
@RequiredArgsConstructor
public class TokenBattleMapCommands {

	private final ExceptionToStringMapper exceptionMapper;
	private final TokenClient client;

	@ShellMethod(value = "Adds a token to a battle map.", key = { "add-token-to-battle-map", "atbm" })
	public String add(
		@ShellOption(
			help = "Id or name of the token to place on the battle map.",
			value = "tokenIdOrName"
		) String tokenIdOrName,
		@ShellOption(help = "Id or name of the battle map.", value = "battleMapIdOrName") String battleMapIdOrName,
		@ShellOption(help = "X coordinate where the token is to place.", value = "x") int x,
		@ShellOption(help = "Y coordinate where the token is to place.", value = "y") int y
	) {
		try {
			return client.setTokenToBattleMapOfSpielrunde(
				tokenIdOrName,
				battleMapIdOrName,
				new Coordinates().setX(x).setY(y)
			);
		} catch (Exception e) {
			return exceptionMapper.map(e);
		}
	}

	@ShellMethod(value = "Lists all stored battle maps for the .", key = { "list-battle-maps", "lbm" })
	public String list() {
		// return
		// client.findAllBattleMaps().stream().map(BattleMap::toString).reduce((t0, t1)
		// -> t0 + "\n" + t1).orElse("");
		return "not implemented!";
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