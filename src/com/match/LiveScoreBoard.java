package com.match;

import java.util.ArrayList;
import java.util.List;
import java.lang.Exception;


public class LiveScoreBoard {
	private List<Match> matchList = new ArrayList<Match>();
	

	//Function for each operation
	public void addNewMatch(String homeTeam, String awayTeam) throws Exception {
		validateMatchToAdd(homeTeam, awayTeam);
		
		//TODO constructor to pass home and away team
		Match match = new Match();
		match.setHomeTeam(homeTeam);
		match.setAwayTeam(awayTeam);
		//Maintaining adding order to be used for sorting
		int addOrder = matchList.size();
		//TODO remove addingOrder from match and use list index for sorting instead
		match.setAddingOrder(addOrder);
		matchList.add(match);
	}
	
	public void updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
		//TODO constructor to pass home and away team
		//TODO throw exception if index is -1
		Match match = new Match();
		match.setHomeTeam(homeTeam);
		match.setAwayTeam(awayTeam);
		int index = matchList.indexOf(match);
		matchList.get(index).setHomeTeamScore(homeTeamScore);
		matchList.get(index).setAwayTeamScore(awayTeamScore);
	}
	
	public void finishMatch(String homeTeam, String awayTeam) {
		//TODO constructor to pass home and away team, search for all new Match()
		Match match = new Match();
		match.setHomeTeam(homeTeam);
		match.setAwayTeam(awayTeam);
		matchList.remove(match);
		//TODO update order of all Matches hence forth, no need of this if adding order is removed from the match 
	}
	
	public String getSummary() {
		//TODO update to use comparator
		matchList.sort(null);
		return matchList.toString();
	}
	
	/*************************************************************************************/
	//Other supporting functions
	//TODO return validation errors or Validation exception
	private void validateMatchToAdd(String homeTeam, String awayTeam) throws Exception {
		if (homeTeam.equals(awayTeam)) {
			Exception ex = new Exception("Match not allowed between same team");
			throw ex;
		}
		
		Match match = new Match();
		match.setHomeTeam(homeTeam);
		match.setAwayTeam(awayTeam);
		
		if (matchList.contains(match)) {
			Exception ex = new Exception("Match already live");
			throw ex;
		}
	}
	
	public Match getLastAdded() {
		return matchList.get(matchList.size()-1);
	}
	
	public int getNumberOfLiveMathces() {
		return 	matchList.size();
	}

	
	public Match getMatachDetailsFor(String homeTeam, String awayTeam) {
		Match match = new Match();
		match.setHomeTeam(homeTeam);
		match.setAwayTeam(awayTeam);
		int index = matchList.indexOf(match);
		return matchList.get(index);
	}

	
	public int getIndexOfMatch(Match match) {
		if (null != match) {
			return matchList.indexOf(match);
		}
		return -1;
	}


	

}
