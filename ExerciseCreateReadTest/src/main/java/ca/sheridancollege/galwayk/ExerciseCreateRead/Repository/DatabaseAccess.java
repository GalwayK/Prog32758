package ca.sheridancollege.galwayk.ExerciseCreateRead.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import ca.sheridancollege.galwayk.ExerciseCreateRead.Beans.Team;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ca.sheridancollege.galwayk.ExerciseCreateRead.Beans.Player;

@Repository
public class DatabaseAccess 
{
	@Autowired 
	protected NamedParameterJdbcTemplate jdbc;
	
	MapSqlParameterSource params = new MapSqlParameterSource();
	
	public int addPlayer(Player player)
	{
		params.addValue("firstName", player.getFirstName())
		.addValue("lastName", player.getLastName())
		.addValue("number", player.getNumber())
		.addValue("team", player.getTeam());
		
		String sqlQuery = "INSERT INTO PLAYERS "
				+ "(firstName, lastName, number, team) "
				+ "VALUES (:firstName, :lastName, :number, :team);";
		
		return jdbc.update(sqlQuery, params);
	}
	
	public int deletePlayer(int id)
	{
		String sqlQuery = "Delete from Players where id = :id;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		return jdbc.update(sqlQuery, params);
	}
	
	public List<Player> getPlayers()
	{
		String sql = "Select * From Players";
		ArrayList<Player> players = new ArrayList<Player>();
		
		List<Map<String, Object>> rows = jdbc.queryForList(sql, new HashMap());
		
		for (Map<String, Object> row : rows)
		{
			Player player = new Player();
			player.setId((int)row.get("id"));
			player.setFirstName((String)row.get("firstName"));
			player.setLastName((String)row.get("lastName"));
			player.setNumber((int)row.get("number"));
			player.setTeam((String)row.get("team"));
			players.add(player);
		}
		
		return players;
	}
	
	public int updatePlayer(Player player)
	{
		String sqlQuery = "Update Players set firstName = :firstName, "
				+ "lastName = :lastName, team = :team, number = :number "
				+ "where id = :id;";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("firstName", player.getFirstName())
			.addValue("lastName", player.getLastName())
			.addValue("number", player.getNumber())
			.addValue("team", player.getTeam())
			.addValue("id", player.getId());
			
		return jdbc.update(sqlQuery, params);
	}
	
	public List<Team> getTeams()
	{
		String sql = "SELECT * FROM TEAMS order by city;";
		ArrayList<Team> teams = new ArrayList<Team>();
		
		List<Map<String, Object>> rows = jdbc.queryForList(sql, new HashMap());
		
		for (Map<String, Object> row : rows)
		{
			Team team = new Team();
			team.setId((String)(row.get("id")));
			team.setCity((String)(row.get("city")));
			team.setName((String)row.get("name"));
			teams.add(team);
		}
		
		return teams;
		
	}
}
