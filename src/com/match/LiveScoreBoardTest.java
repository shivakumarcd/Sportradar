package com.match;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LiveScoreBoardTest {
	
	LiveScoreBoard liveScoreBoard = new LiveScoreBoard();
	
	@Test
    public void testAddNewMatch() {
        
        String homeTeam = "Mexico"; 
        String  awayTeam = "Canada";
        
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
    public void testAddSameMatchTwice() {  
		String homeTeam = "USA"; 
        String awayTeam = "Brazil";
        try {
        	liveScoreBoard.addNewMatch(homeTeam, awayTeam);
        } catch(Exception ex) {
        		
        }
        
        //System.out.println("No of live matches after adding 1 in 1st test case = " + liveScoreBoard.getNumberOfLiveMathces());
		
        Exception exception = assertThrows(Exception.class, () -> { 
        	liveScoreBoard.addNewMatch(homeTeam, awayTeam);; 
        }); 
  
        assertEquals("Match already live", exception.getMessage()); 
    } 

	@Test
	public void testUpdateScore() {
		fail("fail");
	}
	
	@Test
	public void testUpdateScoreOfNonLiveMatch() {
		fail("fail");
	}
	
	@Test
	public void testFinishMatch() {
		fail("fail");
	}
	
	@Test
	public void testGetSummary() {
		fail("fail");
	}
}
