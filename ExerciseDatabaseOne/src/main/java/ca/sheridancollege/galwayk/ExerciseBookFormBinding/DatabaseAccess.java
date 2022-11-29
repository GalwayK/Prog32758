package ca.sheridancollege.galwayk.ExerciseBookFormBinding;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.galwayk.ExerciseBookFormBinding.Beans.Player;

@Repository
public class DatabaseAccess 
{
	@Autowired 
	protected NamedParameterJdbcTemplate jdbc;
	
	public void insertPlayer(Player player) throws Exception
	{
		
	}
	
	public void addPlayer() throws Exception
	{
		String sql = "INSERT INTO players (firstName, lastName, number, team)"
				+ " values ('Carey', 'Price', 32, 'MTL');";
		jdbc.update(sql, new HashMap());
	}
	
	public List<Player> getPlayerList()
	{
		return null;
	}
}
