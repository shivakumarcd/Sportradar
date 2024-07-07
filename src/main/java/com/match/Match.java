package com.match;

public class Match  {
	private String homeTeam;
	private String awayTeam;
	private int homeTeamScore;
	private int awayTeamScore;
	
	public Match(String homeTeam, String awayTeam) {
		if (null == homeTeam || null == awayTeam || "".equals(awayTeam) || "".equals(homeTeam)) {
			throw new InvalidDataException("Team cannot be null or empty");
		}
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Match)) {
			return false;
		}
		Match match = (Match)obj;
		if (this.homeTeam.equals(match.getHomeTeam()) && this.awayTeam.equals(match.getAwayTeam())) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return homeTeam + "  " + homeTeamScore + "  -  " + awayTeam + "  " + awayTeamScore;
	}
	
	//getters and setters
	public String getHomeTeam() {
		return this.homeTeam;
	}

	public String getAwayTeam() {
		return this.awayTeam;
	}

	public int getHomeTeamScore() {
		return this.homeTeamScore;
	}

	public int getAwayTeamScore() {
		return this.awayTeamScore;
	}
	
	public void setHomeTeamScore(int homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}

	public void setAwayTeamScore(int awayTeamScore) {
		this.awayTeamScore = awayTeamScore;
	}


}