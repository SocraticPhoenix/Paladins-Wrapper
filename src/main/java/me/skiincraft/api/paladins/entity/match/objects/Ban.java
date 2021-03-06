package me.skiincraft.api.paladins.entity.match.objects;

import me.skiincraft.api.paladins.common.EndPoint;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.champions.Champion;
import me.skiincraft.api.paladins.enums.Language;

public class Ban {

	private final long championId;
	private final String championName;
	private Request<Champion> champion;
	
	private final EndPoint endPoint;
	
	public Ban(long banId, String banName, EndPoint endPoint) {
		this.championId =banId;
		this.championName = banName;
		this.endPoint = endPoint;
	}
	/**
	 * <p>Is the Champion Id</p>
	 */
	public long getChampionId() {
		return championId;
	}

	/**
	 * <p>Make an API request to return the banned champion of this class</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.ChampionException In case the champion Id is wrong.
	 *
	 * @param language the language you want to receive the champions;
	 *
	 * @return Champion
	 */
	public Request<Champion> getChampion(Language language) {
		if (champion == null) {
			champion = endPoint.getChampion(getChampionId(), language);
		}
		return champion;
	}

	/**
	 * <p>Is the Champion name</p>
	 */
	public String getChampionName() {
		return championName;
	}

	@Override
	public String toString() {
		return "Ban{" +
				"championId=" + championId +
				", championName='" + championName + '\'' +
				'}';
	}
}
