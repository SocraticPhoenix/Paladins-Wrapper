package me.skiincraft.api.paladins.enums;

import me.skiincraft.api.paladins.common.EndPoint;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.entity.match.LiveMatch;
import me.skiincraft.api.paladins.entity.player.Player;
import me.skiincraft.api.paladins.exceptions.MatchException;

import java.util.Arrays;

/**
 * <h1>PlayerStatus</h1>
 * <p>
 *     <p>It's little information about the player, at this point.</p>
 * </p>
 */
public class PlayerStatus {

	/**
	 * <h1>Status</h1>
	 * <p>
	 *     <p>These are ingame statuses, such as offline, online, in game, and selection menu</p>
	 * </p>
	 */
	public enum Status {
		/**
		 * <p>If the player is offline</p>
		 */
		Offline(0),

		/**
		 * <p>If the player is in the game lobby</p>
		 */
		In_Lobby(1),

		/**
		 * <p>If the player is in the match selection menu</p>
		 */
		God_Selection(2),

		/**
		 * <p>If the player is in a match</p>
		 */
		In_Game(3),

		/**
		 * <p>If the player is only online</p>
		 */
		Online(4),
		/**
		 * <p>Unknown, in case there are none of the other enums</p>
		 */
		Unknown(5);

		private final int id;

		Status(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
		public static Status getStatusById(int id) {
			return Arrays.stream(values())
					.filter(status -> status.getId() == id)
					.findFirst()
					.orElse(Unknown);
		}
	}

	private final long matchId;
	private final Status status;
	private final EndPoint endPoint;
	private final String player;
	
	public PlayerStatus(String player, long matchId, Status status, EndPoint endPoint) {
		this.player = player;
		this.matchId = matchId;
		this.status = status;
		this.endPoint = endPoint;
	}

	/**
	 * <p>If the player is in a match, he will return the match Id.
	 *  otherwise, it will return 0.</p>
	 */
	public long getMatchId() {
		return matchId;
	}

	/**
	 * <p>Checks whether the player is in a match.</p>
	 * <p>
	 *    <p>Is the same thing as: getMatchId() != 0</p>
	 * </p>
	 */
	public boolean isInMatch() {
		return (getMatchId() != 0);
	}

	/**
	 * <p>Are the status in game, this player</p>
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * <p>Make an api request to return a Player</p>
	 * <p>After the order is completed, the API will receive a Json, which will be converted into a class and returned</p>
	 *
	 * @throws me.skiincraft.api.paladins.exceptions.RequestException If anything is wrong with the session.
	 * @throws me.skiincraft.api.paladins.exceptions.PlayerException If the player has a private profile or does not exist.
	 *
	 * @return Player
	 */
	public Request<Player> getPlayer(){
		return endPoint.getPlayer(player);
	}

	/**
	 * <p>Get information about this player's current match</p>
	 * @throws MatchException If the player is not in a match.
	 */
	public Request<LiveMatch> getLiveMatch(){
		if (!isInMatch() || getStatus() == Status.Offline) {
			throw new MatchException("This player is not in a match");
		}
		return endPoint.getMatchPlayerDetails(getMatchId());
	}

	@Override
	public String toString() {
		return "PlayerStatus{" +
				"matchId=" + matchId +
				", status=" + status +
				", player='" + player + '\'' +
				'}';
	}
}
