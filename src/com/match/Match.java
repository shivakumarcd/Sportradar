package com.match;

public class Match {
	private String homeTeam;
	private String awayTeam;
	private int homeTeamScore;
	private int awayTeamScore;

	public Match() {
	}
	
	@Override
	public boolean equals(Object obj) {
		Match match = (Match)obj;
		if (this.homeTeam.equals(match.getHomeTeam()) && this.awayTeam.equals(match.getAwayTeam())) {
			return true;
		}
		return false;
	}
	
	public String getHomeTeam() {
		return this.homeTeam;
	}

	public String getAwayTeam() {
		return this.awayTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
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