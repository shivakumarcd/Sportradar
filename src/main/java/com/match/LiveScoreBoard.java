package com.match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class LiveScoreBoard {
	private List<Match> matchList = new ArrayList<Match>();
	

	//Function for each operation
	public void addNewMatch(String homeTeam, String awayTeam) {
		validateMatchToAdd(homeTeam, awayTeam);
		
		Match match = new Match(homeTeam, awayTeam);
		matchList.add(match);
	}
	
	public void updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
		if (homeTeamScore < 0 || awayTeamScore < 0) {
			throw new InvalidDataException("Cannot update score with negative value");
		}
		
		Match match = new Match(homeTeam, awayTeam);
		int index = matchList.indexOf(match);
		if (index < 0) {
			throw new InvalidDataException("Cannot update score for non-existing match");
		}
		matchList.get(index).setHomeTeamScore(homeTeamScore);
		matchList.get(index).setAwayTeamScore(awayTeamScore);
	}
	
	public void finishMatch(String homeTeam, String awayTeam) {
		Match match = new Match(homeTeam, awayTeam);
		int index = matchList.indexOf(match);
		if (index < 0) {
			throw new InvalidDataException("Cannot finish non-existing match");
		}
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
	
	private void validateMatchToAdd(String homeTeam, String awayTeam) {
		if (null == homeTeam || null == awayTeam || "".equals(awayTeam) || "".equals(homeTeam)) {
			throw new InvalidDataException("Team cannot be null or empty");
		}
		
		for (Match match: matchList) {
			if (match.getHomeTeam().equals(homeTeam) || match.getAwayTeam().equals(homeTeam)
					|| match.getHomeTeam().equals(awayTeam) || match.getAwayTeam().equals(awayTeam)) {
				throw new InvalidDataException("Team already in match");
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
