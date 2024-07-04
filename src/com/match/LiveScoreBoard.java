package com.match;

import java.util.ArrayList;
import java.util.List;
import java.lang.Exception;

//@Component
public class LiveScoreBoard {
	private List<Match> matchDataList = new ArrayList<Match>();

	
	public LiveScoreBoard() {
		//System.out.println("Creating LiveScoreBoard");
	}
	
	public void addNewMatch(String homeTeam, String awayTeam) throws Exception {
		Match matchData = new Match();
		matchData.setHomeTeam(homeTeam);
		matchData.setAwayTeam(awayTeam);
		matchData.setHomeTeamScore(0);;
		matchData.setAwayTeamScore(0);
		
		if (matchDataList.contains(matchData)) {
			//System.out.println("Exception point 1");
			Exception ex = new Exception("Match already live");
			throw ex;
		}
		
		matchDataList.add(matchData);
	}
	
	public Match getLastAdded() {
		return matchDataList.get(matchDataList.size()-1);
	}
	
	public int getNumberOfLiveMathces() {
		return 	matchDataList.size();
	}

}
