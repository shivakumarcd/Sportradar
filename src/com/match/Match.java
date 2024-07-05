package com.match;

//TODO remove comment
public class Match /*implements Comparable<Match> */ {
	private String homeTeam;
	private String awayTeam;
	private int homeTeamScore;
	private int awayTeamScore;
	//TODO remove
	//private int addingOrder;

	
	public Match(String homeTeam, String awayTeam) {
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
		//TODO check if any team is already part of ongoing match in validate and remove this condition
		if (this.awayTeam.equals(match.getHomeTeam()) && this.homeTeam.equals(match.getAwayTeam())) {
			return true;
		}
		return false;
	}
	
	//TODO remove
	/*@Override
	public int compareTo(Match obj) {
		int thisTotalScore = this.homeTeamScore + this.awayTeamScore;
		int objTotalScore = obj.getHomeTeamScore() + obj.getAwayTeamScore(); 
		if (thisTotalScore < objTotalScore) {
			return 1;
		} else if (thisTotalScore < objTotalScore) {
			return -1;
		} else if (this.addingOrder > obj.getAddingOrder()) {
			return -1;
		} else {
			return 1;
		}
	}*/
	
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

	//TODO remove
//	public int getAddingOrder() {
//		return addingOrder;
//	}
	
	public void setHomeTeamScore(int homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}

	public void setAwayTeamScore(int awayTeamScore) {
		this.awayTeamScore = awayTeamScore;
	}



//	public void setAddingOrder(int addingOrder) {
//		this.addingOrder = addingOrder;
//	}
}