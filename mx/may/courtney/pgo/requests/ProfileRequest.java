package mx.may.courtney.pgo.requests;

import com.google.protobuf.InvalidProtocolBufferException;

import mx.may.courtney.pgo.Pgo.ClientPlayer;
import mx.may.courtney.pgo.Pgo.ClientPlayerDetails;
import mx.may.courtney.pgo.Pgo.Payload;


import mx.may.courtney.pgo.Request;
import mx.may.courtney.pgo.api.PlayerProfile;
import mx.may.courtney.pgo.api.Team;

public class ProfileRequest extends Request 
{
	private PlayerProfile profile;
	public int getRpcId()
	{
		profile = new PlayerProfile();
		return 2;
	}

	public PlayerProfile getProfile()
	{
		return profile;
	}

	public void handleResponse(Payload payload)
	{
		try {
			ClientPlayerDetails details = ClientPlayerDetails.parseFrom(payload.getData());

			profile.setBadge(details.getBadge());
			profile.setCreationTime(details.getCreationTime());
			profile.setItemStorage(details.getItemStorage());
			profile.setPokemonStorage(details.getPokeStorage());
			profile.setTeam(valueOf(details.getTeam()));
			profile.setUsername(details.getUsername()); 

		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
	
	private Team valueOf(int value)
	{
		if (value == 0) return Team.TEAM_INSTINCT;
		if (value == 1) return Team.TEAM_MYSTIC;
		if (value == 2) return Team.TEAM_VALOR;
		
		return Team.TEAM_NONE;
	}
	
	

}
