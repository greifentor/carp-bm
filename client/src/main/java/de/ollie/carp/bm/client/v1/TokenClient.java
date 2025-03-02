package de.ollie.carp.bm.client.v1;

import de.ollie.carp.bm.client.v1.dto.BattleMapTokenDTO;
import de.ollie.carp.bm.client.v1.dto.BattleMapTokenDataDTO;
import de.ollie.carp.bm.client.v1.dto.CoordinatesDTO;
import de.ollie.carp.bm.client.v1.dto.DnDTokenSizeDTO;
import de.ollie.carp.bm.client.v1.dto.TokenDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenClient {
	TokenDTO createToken(String name, byte[] image);

	TokenDTO createDnDToken(String name, byte[] image, int rk, int tpMaximum, DnDTokenSizeDTO dndTokenSize);

	UUID delete(String tokenIdOrName);

	List<TokenDTO> findAllTokens();

	TokenDTO getByIdOrName(String idOrName);

	List<BattleMapTokenDTO> findAllByBattleMap(String battleMapIdOrName);

	Optional<BattleMapTokenDTO> findSelectedTokenByBattleMap(String battleMapIdOrName);

	String moveBattleMapToken(String battleMapTokenId, CoordinatesDTO coordinates);

	String setTokenToBattleMapOfSpielrunde(
		String tokenIdOrName,
		String battleMapIdOrName,
		BattleMapTokenDataDTO battleMapTokenData
	);
}
