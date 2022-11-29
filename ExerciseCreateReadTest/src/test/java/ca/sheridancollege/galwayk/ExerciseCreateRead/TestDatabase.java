package ca.sheridancollege.galwayk.ExerciseCreateRead;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.sheridancollege.galwayk.ExerciseCreateRead.Beans.Player;
import ca.sheridancollege.galwayk.ExerciseCreateRead.Repository.DatabaseAccess;

@SpringBootTest
class TestDatabase 
{
	@Autowired
	private DatabaseAccess dataAccess;
	
	@Test
	public void testDatabaseAdd() 
	{
		Player player = new Player();
		player.setFirstName("Kyle");
		player.setLastName("Galway");
		player.setTeam("Sheridan");
		
		int originalSize = dataAccess.getPlayers().size();
		
		dataAccess.addPlayer(player);
		
		int newSize = dataAccess.getPlayers().size();
		
		assertEquals(newSize, originalSize + 1);
	}
	
	@Test
	public void testDatabaseDelete()
	{
		List<Player> players = dataAccess.getPlayers();
		int id = players.get(0).getId();
		System.out.println("Players: " + players);
		System.out.println("Player ID: "+ id);
		int originalSize = dataAccess.getPlayers().size();
		
		dataAccess.deletePlayer(id);
		
		int newSize = dataAccess.getPlayers().size();
		
		assertEquals(newSize, originalSize - 1);
	}
	

}
