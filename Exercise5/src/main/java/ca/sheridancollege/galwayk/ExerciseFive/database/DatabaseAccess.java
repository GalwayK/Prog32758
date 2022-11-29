package ca.sheridancollege.galwayk.ExerciseFive.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseAccess 
{
	@Autowired 
	private NamedParameterJdbcTemplate jdbc;
	
	public List<String> getAuthorities()
	{
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		String sqlQuery = "Select distinct authority from authorities;";
		
		return jdbc.queryForList(sqlQuery, params, String.class);
	}
}
