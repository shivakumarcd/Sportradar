package com.match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.lang.Exception;


public class LiveScoreBoard {
	private List<Match> matchList = new ArrayList<Match>();
	

	//Function for each operation
	public void addNewMatch(String homeTeam, String awayTeam) throws Exception {
		validateMatchToAdd(homeTeam, awayTeam);
		
		Match match = new Match(homeTeam, awayTeam);
		matchList.add(match);
	}
	
	public void updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
		//TODO throw exception if index is -1
		Match match = new Match(homeTeam, awayTeam);
		int index = matchList.indexOf(match);
		matchList.get(index).setHomeTeamScore(homeTeamScore);
		matchList.get(index).setAwayTeamScore(awayTeamScore);
	}
	
	public void finishMatch(String homeTeam, String awayTeam) {
		Match match = new Match(homeTeam, awayTeam);
		matchList.remove(match); 
	}
	
	public String getSummary() {
		//matchList.sort(null);
		return this.sortMatches().toString();
	}
	
	
	/*************************************************************************************/
	//Other supporting refactored functions
	private List<Match> sortMatches() {
		Map<Integer, Match> indexToMatchMap = new HashMap<Integer, Match>(); 
		for (int i = 0; i < this.matchList.size(); i++) {
			indexToMatchMap.put(i, this.matchList.get(i));
		}
		List<Map.Entry<Integer, Match>> entryList = new ArrayList<>(indexToMatchMap.entrySet());
		entryList.sort((matchOneKeyVal, matchTwoKeyVal) -> {
			Match matchOne = matchOneKeyVal.getValue();
			Match matchTwo = matchTwoKeyVal.getValue();
			int matchOneTotalScore = matchOne.getHomeTeamScore() + matchOne.getAwayTeamScore();
			int matchTwoTotalScore = matchTwo.getHomeTeamScore() + matchTwo.getAwayTeamScore();
			if (matchOneTotalScore == matchTwoTotalScore) {
				matchOneTotalScore += matchOneKeyVal.getKey();
				matchTwoTotalScore += matchTwoKeyVal.getKey();
			}
			return matchTwoTotalScore - matchOneTotalScore;
		});
		return entryList.stream().map(entry -> entry.getValue()).collect(Collectors.toList());
	}
	
	private void validateMatchToAdd(String homeTeam, String awayTeam) throws Exception {
		for (Match match: matchList) {
			if (match.getHomeTeam().equals(homeTeam) || match.getAwayTeam().equals(homeTeam)
					|| match.getHomeTeam().equals(awayTeam) || match.getAwayTeam().equals(awayTeam)) {
				Exception ex = new Exception("Team already in match");
				throw ex;
			}
		}
	}
	
	public Match getLastAdded() {
		return matchList.get(matchList.size()-1);
	}
	
	public int getNumberOfLiveMathces() {
		return 	matchList.size();
	}

	
	public Match getMatachDetailsFor(String homeTeam, String awayTeam) {
		Match match = new Match(homeTeam, awayTeam);
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