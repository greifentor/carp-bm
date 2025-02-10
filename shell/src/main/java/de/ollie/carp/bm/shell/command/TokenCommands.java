package de.ollie.carp.bm.shell.command;

import de.ollie.carp.bm.client.v1.TokenClient;
import de.ollie.carp.bm.client.v1.dto.DnDTokenSizeDTO;
import de.ollie.carp.bm.client.v1.dto.TokenDTO;
import de.ollie.carp.bm.shell.ExceptionToStringMapper;
import java.io.FileInputStream;
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

	@ShellMethod(value = "Adds a new D&D token.", key = { "add-dnd-token", "adt" })
	public String add(
		@ShellOption(help = "Name of the token.", value = "name") String name,
		@ShellOption(help = "Name of the file which contains the image", value = "imageFileName") String imageFileName,
		@ShellOption(help = "The AC for the token", value = "rk") int rk,
		@ShellOption(help = "The HP maximum for the token", value = "tpMaximum") int tpMaximum,
		@ShellOption(help = "Size of the token", value = "tokenSize", defaultValue = "MITTEL") String tokenSizeStr
	) {
		try (FileInputStream fis = new FileInputStream(imageFileName)) {
			byte[] imageContent = fis.readAllBytes();
			return tokenClient
				.createDnDToken(name, imageContent, rk, tpMaximum, DnDTokenSizeDTO.valueOf(tokenSizeStr))
				.toString();
		} catch (Exception e) {
			return exceptionMapper.map(e);
		}
	}

	@ShellMethod(value = "Adds a new token.", key = { "add-token", "at" })
	public String add(
		@ShellOption(help = "Name of the token.", value = "name") String name,
		@ShellOption(help = "Name of the file which contains the image", value = "imageFileName") String imageFileName
	) {
		try (FileInputStream fis = new FileInputStream(imageFileName)) {
			byte[] imageContent = fis.readAllBytes();
			return tokenClient.createToken(name, imageContent).toString();
		} catch (Exception e) {
			return exceptionMapper.map(e);
		}
	}

	@ShellMethod(value = "Lists all stored tokens.", key = { "list-tokens", "lt" })
	public String list() {
		return tokenClient.findAllTokens().stream().map(TokenDTO::toString).reduce((t0, t1) -> t0 + "\n" + t1).orElse("");
	}

	@ShellMethod(value = "Deletes the token by UUID or name.", key = { "delete-token", "dt" })
	public String delete(@ShellOption(help = "UUID or name of the token.", value = "uuidOrName") String uuidOrName) {
		try {
			return "token deleted with id: " + tokenClient.delete(uuidOrName).toString();
		} catch (Exception e) {
			return exceptionMapper.map(e);
		}
	}
}
