package ca.sheridancollege.galwayk.ExerciseCreateRead;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import ca.sheridancollege.galwayk.ExerciseCreateRead.Beans.Player;
import ca.sheridancollege.galwayk.ExerciseCreateRead.Repository.DatabaseAccess;

@SpringBootTest
@AutoConfigureMockMvc
class TestController 
{
	@Autowired
	private DatabaseAccess dataAccess;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testRoot() throws Exception
	{
		mockMvc.perform(get("/"))
			.andExpect(status()
			.isOk()).andExpect(view().name("index.html"));
	}
	
	@Test
	public void testAddPlayer() throws Exception
	{
		LinkedMultiValueMap<String, String> requestParams = 
			new LinkedMultiValueMap<>();
		
		requestParams.add("firstName", "Kyle");
		requestParams.add("lastName", "Galway");
		requestParams.add("teamName", "Sheridan");
		
		int originalSize = dataAccess.getPlayers().size();
		
		mockMvc.perform(post("/submitPlayer")
		.params(requestParams))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/")).andDo(print());
		
		int newSize = dataAccess.getPlayers().size();
		
		assertEquals(newSize, originalSize + 1);
	}
	
	@Test
	public void testDeletePlayer() throws Exception 
	{
		List<Player> players = dataAccess.getPlayers();
		int originalSize = players.size();
		
		Player player = players.get(0);
		int id = player.getId();
		
		mockMvc.perform(post("/deletePlayer/{id}", id))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/")).andDo(print());
		int newSize = dataAccess.getPlayers().size();
		
		assertEquals(newSize, originalSize - 1);
		
	}
	
	@Test
	public void testUpdate() throws Exception
	{
		List<Player> players = dataAccess.getPlayers();
		
		Player player = players.get(0);
		int id = player.getId();
		player.setFirstName("Liam");
		
		mockMvc.perform(post("/updatePlayer", id)
				.flashAttr("player", player))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/")).andDo(print());
		
		assertEquals(player.getFirstName(), "Liam");
	}
	

}
