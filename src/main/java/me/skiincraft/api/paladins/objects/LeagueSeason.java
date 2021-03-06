package me.skiincraft.api.paladins.objects;

import me.skiincraft.api.paladins.enums.Tier;

public class LeagueSeason {
	
	private final int wins;
	private final int losses;
	private final int points;
	private final Tier tier;
	
	public LeagueSeason(int wins, int losses, int points, Tier tier) {
		super();
		this.wins = wins;
		this.losses = losses;
		this.points = points;
		this.tier = tier;
	}
	
	public int getWins() {
		return wins;
	}
	public int getLosses() {
		return losses;
	}
	public int getPoints() {
		return points;
	}
	public Tier getTier() {
		return tier;
	}

	@Override
	public String toString() {
		return "LeagueSeason [wins=" + wins + ", losses=" + losses + ", points=" + points + ", tier=" + tier + "]";
	}

}
