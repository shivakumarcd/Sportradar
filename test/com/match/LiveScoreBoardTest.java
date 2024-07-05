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
        
        try {
        	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
        } catch(Exception ex) {
        		
        }
        assertEquals(liveScoreBoard.getLastAdded().getHomeTeam(), homeTeam);
        assertEquals(liveScoreBoard.getLastAdded().getAwayTeam(), awayTeam);
        assertEquals(liveScoreBoard.getLastAdded().getHomeTeamScore(), 0);
        assertEquals(liveScoreBoard.getLastAdded().getHomeTeamScore(), 0);
        
        
        homeTeam = "Spain"; 
        awayTeam = "Brazil";
        try {
        	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
        } catch(Exception ex) {
        		
        }
        assertEquals(liveScoreBoard.getLastAdded().getHomeTeam(), homeTeam);
        assertEquals(liveScoreBoard.getLastAdded().getAwayTeam(), awayTeam);
        assertEquals(liveScoreBoard.getLastAdded().getHomeTeamScore(), 0);
        assertEquals(liveScoreBoard.getLastAdded().getHomeTeamScore(), 0);
        //System.out.println("No of live matches after adding 2 in 1st test case = " + liveScoreBoard.getNumberOfLiveMathces());
    }
	@Test
    public void testAddNewMatchEdgeCases() { 
		System.out.println("*****************testAddNewMatchEdgeCases: Start**********************");
		String homeTeam = "USA"; 
        String awayTeam = "Brazil";
        try {
        	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
        } catch (Exception ex) {
        		
        }
        
        //System.out.println("No of live matches after adding 1 in 1st test case = " + liveScoreBoard.getNumberOfLiveMathces());
		
        //Edge case 1/3: Same match cannot be added twice to scoreboard
        Exception exception = assertThrows(Exception.class, () -> { 
        	liveScoreBoard.addNewMatch(homeTeam, awayTeam);; 
        }); 
        assertEquals("Match already live", exception.getMessage());
        System.out.println("testAddNewMatchEdgeCases Case 1 passed");
        
    
    	//Edge case 2/3: Test adding ("Brazil", "USA") when ("USA", "Brazil") match already on scoreboard 
        exception = assertThrows(Exception.class, () -> { 
        	liveScoreBoard.addNewMatch(awayTeam, homeTeam);; 
        }); 
        assertEquals("Match already live", exception.getMessage());
        System.out.println("testAddNewMatchEdgeCases Case 2 passed");
        
        //Edge case 3/3:team cannot be in match with itself
        exception = assertThrows(Exception.class, () -> { 
        	liveScoreBoard.addNewMatch(homeTeam, homeTeam);; 
        }); 
        assertEquals("Match not allowed between same team", exception.getMessage());
        System.out.println("testAddNewMatchEdgeCases Case 3 passed");
        //TODO test that match cannot be created when a team is already in a running match
        System.out.println("*****************testAddNewMatchEdgeCases: Start**********************");
    } 

	
	
	
	@Test
	public void testUpdateScore() {
		String homeTeam = "Mexico"; 
        String awayTeam = "Canada";
        try {
        	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
        } catch(Exception ex) {
        		
        }
        homeTeam = "Spain"; 
        awayTeam = "Brazil";
        try {
        	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
        } catch(Exception ex) {
        		
        }
        
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
		//fail("fail");
	}
	/*
	@Test
	public void testUpdateScoreEdgeCases() {
		// TODO
		//testUpdateScoreOfNonLiveMatch
		fail("fail");
	}
	*/
	
		
	
	@Test
	public void testFinishMatch() {
		String homeTeam = "Mexico"; 
        String awayTeam = "Canada";
        try {
        	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
        } catch(Exception ex) {
        		
        }
        homeTeam = "Spain"; 
        awayTeam = "Brazil";
        try {
        	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
        } catch(Exception ex) {
        		
        }
        
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
	/*@Test
	public void testFinishMatchEdgeCases() {
		// TODO
		fail("fail");
	}*/
	
	
	
	
	@Test
	public void testGetSummary() {
		prepareForSummaryTest();
		String result = liveScoreBoard.getSummary();
		String expResult = "[Uruguay  6  -  Italy  6, Spain  10  -  Brazil  2, Mexico  0  -  Canada  5, Argentina  3  -  Australia  1, Germany  2  -  France  2]";
		assertEquals(result, expResult);
		
		//TODO test after deleting
		
	}
	private void prepareForSummaryTest() {
		String homeTeam = "Mexico"; 
        int homeTeamScore = 0;
        String awayTeam = "Canada";
        int awayTeamScore = 5;
        
        try {
        	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
        	liveScoreBoard.updateScore(homeTeam, awayTeam, homeTeamScore, awayTeamScore);
        } catch(Exception ex) {
        		
        }
        
        homeTeam = "Spain";
        homeTeamScore = 10;
        awayTeam = "Brazil";
        awayTeamScore = 2;
        try {
        	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
        	liveScoreBoard.updateScore(homeTeam, awayTeam, homeTeamScore, awayTeamScore);
        } catch(Exception ex) {
        		
        }
		
        homeTeam = "Germany";
        homeTeamScore = 2;
        awayTeam = "France";
        awayTeamScore = 2;
        try {
        	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
        	liveScoreBoard.updateScore(homeTeam, awayTeam, homeTeamScore, awayTeamScore);
        } catch(Exception ex) {
        		
        }
        
        homeTeam = "Uruguay";
        homeTeamScore = 6;
        awayTeam = "Italy";
        awayTeamScore = 6;
        try {
        	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
        	liveScoreBoard.updateScore(homeTeam, awayTeam, homeTeamScore, awayTeamScore);
        } catch(Exception ex) {
        		
        }
        
        homeTeam = "Argentina";
        homeTeamScore = 3;
        awayTeam = "Australia";
        awayTeamScore = 1;
        try {
        	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
        	liveScoreBoard.updateScore(homeTeam, awayTeam, homeTeamScore, awayTeamScore);
        } catch(Exception ex) {
        		
        }
        
	}
	/*
	@Test
	public void testGetSummaryEdgeCases() {
		// TODO
		fail("fail");
	}
	*/
	
	
	
	public void dummy() {
		
	}
}
