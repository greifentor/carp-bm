package de.ollie.carp.bm.shell.command;

import de.ollie.carp.bm.client.BattleMapClient;
import de.ollie.carp.bm.core.model.BattleMap;
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
public class BattleMapCommands {

	private final ExceptionToStringMapper exceptionMapper;
	private final BattleMapClient client;

	@ShellMethod(value = "Adds a new battle map.", key = { "add-battle-map", "abm" })
	public String add(@ShellOption(help = "Name of the battle map.", value = "name") String name) {
		try {
			return client.createBattleMapWithName(name).toString();
		} catch (Exception e) {
			return exceptionMapper.map(e);
		}
	}

	@ShellMethod(value = "Lists all stored battle maps.", key = { "list-battle-maps", "lbm" })
	public String list() {
		return client.findAllBattleMaps().stream().map(BattleMap::toString).reduce((t0, t1) -> t0 + "\n" + t1).orElse("");
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
