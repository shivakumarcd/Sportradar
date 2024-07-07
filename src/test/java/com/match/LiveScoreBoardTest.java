package com.match;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class LiveScoreBoardTest {
	
	LiveScoreBoard liveScoreBoard = new LiveScoreBoard();
	
	
	@Test
    public void testAddNewMatch() {
        
        String homeTeam = "Mexico"; 
        String awayTeam = "Canada";
        
    	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
        assertEquals(liveScoreBoard.getLastAdded().getHomeTeam(), homeTeam);
        assertEquals(liveScoreBoard.getLastAdded().getAwayTeam(), awayTeam);
        assertEquals(liveScoreBoard.getLastAdded().getHomeTeamScore(), 0);
        assertEquals(liveScoreBoard.getLastAdded().getHomeTeamScore(), 0);
        
        
        homeTeam = "Spain"; 
        awayTeam = "Brazil";
    	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
        assertEquals(liveScoreBoard.getLastAdded().getHomeTeam(), homeTeam);
        assertEquals(liveScoreBoard.getLastAdded().getAwayTeam(), awayTeam);
        assertEquals(liveScoreBoard.getLastAdded().getHomeTeamScore(), 0);
        assertEquals(liveScoreBoard.getLastAdded().getHomeTeamScore(), 0);
    }
	@Test
    public void testAddNewMatchEdgeCases() {
		String homeTeam = "USA"; 
        String awayTeam = "Brazil";
    	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
        
        //Adding same match should result in exception
        Exception exception = assertThrows(Exception.class, () -> { 
        	liveScoreBoard.addNewMatch(homeTeam, awayTeam);;
        }); 
        assertEquals("Team already in match", exception.getMessage());
        
        //Testing keeping same home and away team
        exception = assertThrows(Exception.class, () -> { 
        	liveScoreBoard.addNewMatch(homeTeam, homeTeam);; 
        }); 
        assertEquals("Team already in match", exception.getMessage());
        
        //Testing match by interchanging homeTeam & awayTeam
        exception = assertThrows(Exception.class, () -> { 
        	liveScoreBoard.addNewMatch(awayTeam, homeTeam);; 
        }); 
        assertEquals("Team already in match", exception.getMessage());
        
        //Testing if team is already in another match 
        String newAwayTeam = "Spain";
        exception = assertThrows(Exception.class, () -> { 
        	liveScoreBoard.addNewMatch(homeTeam, newAwayTeam);; 
        }); 
        assertEquals("Team already in match", exception.getMessage());
        
        //Testing if either of home/away team is null
        exception = assertThrows(Exception.class, () -> { 
        	liveScoreBoard.addNewMatch(awayTeam, null);; 
        }); 
        assertEquals("Team cannot be null or empty", exception.getMessage());
        
        //Testing if either of home/away team is empty string
        exception = assertThrows(Exception.class, () -> { 
        	liveScoreBoard.addNewMatch(awayTeam, "");; 
        }); 
        assertEquals("Team cannot be null or empty", exception.getMessage());
    } 

	
	
	
	@Test
	public void testUpdateScore() {
		String homeTeam = "Mexico"; 
        String awayTeam = "Canada";
    	liveScoreBoard.addNewMatch(homeTeam, awayTeam);

    	homeTeam = "Spain"; 
        awayTeam = "Brazil";
    	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
        
        homeTeam = "Mexico"; 
        int homeTeamScore = 0;
        awayTeam = "Canada";
        int awayTeamScore = 5;
        liveScoreBoard.updateScore(homeTeam, awayTeam, homeTeamScore, awayTeamScore);
        Match matchDetails = liveScoreBoard.getMatachDetailsFor(homeTeam, awayTeam);
        assertEquals(matchDetails.getHomeTeamScore(), homeTeamScore);
        assertEquals(matchDetails.getAwayTeamScore(), awayTeamScore);
        
        
        homeTeam = "Spain"; 
        homeTeamScore = 10;
        awayTeam = "Brazil";
        awayTeamScore = 2;
        liveScoreBoard.updateScore(homeTeam, awayTeam, homeTeamScore, awayTeamScore);
        matchDetails = liveScoreBoard.getMatachDetailsFor(homeTeam, awayTeam);
        assertEquals(matchDetails.getHomeTeamScore(), homeTeamScore);
        assertEquals(matchDetails.getAwayTeamScore(), awayTeamScore);
	}
	@Test
	public void testUpdateScoreEdgeCases() {
		String homeTeam = "USA"; 
        String awayTeam = "Brazil";
        int homeTeamScore = 1;
        int awayTeamScore = 2;
    	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
    	liveScoreBoard.updateScore(homeTeam, awayTeam, homeTeamScore, awayTeamScore);
        
        //Handle -ve score case
        int negativeScore = -1;
        Exception exception = assertThrows(Exception.class, () -> { 
        	liveScoreBoard.updateScore(homeTeam, awayTeam, homeTeamScore, negativeScore); 
        }); 
        assertEquals("Cannot update score with negative value", exception.getMessage());
        
        
        //Testing update score on non-existing matches
        exception = assertThrows(Exception.class, () -> { 
        	liveScoreBoard.updateScore(awayTeam, homeTeam, awayTeamScore, homeTeamScore);
        }); 
        assertEquals("Cannot update score for non-existing match", exception.getMessage());

        String newAwayTeam = "Spain";
        exception = assertThrows(Exception.class, () -> { 
        	liveScoreBoard.updateScore(homeTeam, newAwayTeam, homeTeamScore, awayTeamScore); 
        }); 
        assertEquals("Cannot update score for non-existing match", exception.getMessage());
        
        //Testing if either of home/away team is empty string
        exception = assertThrows(Exception.class, () -> { 
        	liveScoreBoard.updateScore(awayTeam, null, awayTeamScore, homeTeamScore); 
        }); 
        assertEquals("Team cannot be null or empty", exception.getMessage());        
        exception = assertThrows(Exception.class, () -> { 
        	liveScoreBoard.updateScore(awayTeam, "", awayTeamScore, homeTeamScore);
        }); 
        assertEquals("Team cannot be null or empty", exception.getMessage());
        
	}
	
		
	
	@Test
	public void testFinishMatch() {
		String homeTeam = "Mexico"; 
        String awayTeam = "Canada";
    	liveScoreBoard.addNewMatch(homeTeam, awayTeam);

    	homeTeam = "Spain"; 
        awayTeam = "Brazil";
    	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
        
        homeTeam = "Mexico"; 
        awayTeam = "Canada";
        liveScoreBoard.finishMatch(homeTeam, awayTeam);
        Match matchData = new Match(homeTeam, awayTeam);
        int index = liveScoreBoard.getIndexOfMatch(matchData);
        assertEquals(index, -1);
        
        homeTeam = "Spain"; 
        awayTeam = "Brazil";
        matchData = new Match(homeTeam, awayTeam);
        index = liveScoreBoard.getIndexOfMatch(matchData);
        assertEquals(index, 0);
	}
	
	@Test
	public void testFinishMatchEdgeCases() {
		String homeTeam = "USA"; 
        String awayTeam = "Brazil";
    	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
        
        //Testing finish non-existing matches
        Exception exception = assertThrows(Exception.class, () -> { 
        	liveScoreBoard.finishMatch(awayTeam, homeTeam);
        }); 
        assertEquals("Cannot finish non-existing match", exception.getMessage());

        String newAwayTeam = "Spain";
        exception = assertThrows(Exception.class, () -> { 
        	liveScoreBoard.finishMatch(homeTeam, newAwayTeam); 
        }); 
        assertEquals("Cannot finish non-existing match", exception.getMessage());
        
        //Testing if either of home/away team is empty string
        exception = assertThrows(Exception.class, () -> { 
        	liveScoreBoard.finishMatch(awayTeam, null); 
        }); 
        assertEquals("Team cannot be null or empty", exception.getMessage());        
        exception = assertThrows(Exception.class, () -> { 
        	liveScoreBoard.finishMatch(awayTeam, "");
        }); 
        assertEquals("Team cannot be null or empty", exception.getMessage());
	}
	
	@Test
	public void testGetSummary() {
		prepareForSummaryTest();
		String result = liveScoreBoard.getSummary();
		String expResult = "[Uruguay  6  -  Italy  6, Spain  10  -  Brazil  2, Mexico  0  -  Canada  5, Argentina  3  -  Australia  1, Germany  2  -  France  2]";
		assertEquals(result, expResult);
		
		// test summary after match finish
		liveScoreBoard.finishMatch("Mexico", "Canada");
		result = liveScoreBoard.getSummary();
		expResult = "[Uruguay  6  -  Italy  6, Spain  10  -  Brazil  2, Argentina  3  -  Australia  1, Germany  2  -  France  2]";
		assertEquals(result, expResult);
		
	}

	@Test
	public void testGetSummaryEdgeCases() {
		String result = liveScoreBoard.getSummary();
		String expResult = "[]";
		assertEquals(result, expResult);
	}
	
	/*Other supporting re-factored functions*/
	private void prepareForSummaryTest() {
		String homeTeam = "Mexico"; 
        int homeTeamScore = 0;
        String awayTeam = "Canada";
        int awayTeamScore = 5;
        
    	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
    	liveScoreBoard.updateScore(homeTeam, awayTeam, homeTeamScore, awayTeamScore);

        
        homeTeam = "Spain";
        homeTeamScore = 10;
        awayTeam = "Brazil";
        awayTeamScore = 2;
    	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
    	liveScoreBoard.updateScore(homeTeam, awayTeam, homeTeamScore, awayTeamScore);
		
        homeTeam = "Germany";
        homeTeamScore = 2;
        awayTeam = "France";
        awayTeamScore = 2;
    	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
    	liveScoreBoard.updateScore(homeTeam, awayTeam, homeTeamScore, awayTeamScore);
        
        homeTeam = "Uruguay";
        homeTeamScore = 6;
        awayTeam = "Italy";
        awayTeamScore = 6;
    	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
    	liveScoreBoard.updateScore(homeTeam, awayTeam, homeTeamScore, awayTeamScore);
        
        homeTeam = "Argentina";
        homeTeamScore = 3;
        awayTeam = "Australia";
        awayTeamScore = 1;
    	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
    	liveScoreBoard.updateScore(homeTeam, awayTeam, homeTeamScore, awayTeamScore);
        
	}
}
