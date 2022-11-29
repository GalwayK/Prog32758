package ca.sheridancollege.galwayk.AssignmentTwo.Database;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ca.sheridancollege.galwayk.AssignmentTwo.Beans.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DataAccess 
{
	private NamedParameterJdbcTemplate jdbc;
	
	public DataAccess(NamedParameterJdbcTemplate jdbc)
	{
		this.jdbc = jdbc;
	}
	
	public List<User> getUserList()
	{
		String sqlQuery = "SELECT * FROM Users;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		List<Map<String, Object>> rows = jdbc.queryForList(sqlQuery, 
			new HashMap());
		ArrayList<User> userList = new ArrayList<User>();
		
		for (Map<String, Object> row : rows)
		{
			User user = new User();
			user.setId((int)row.get("id"));
			user.setFirstName((String)row.get("firstName"));
			user.setLastName((String)row.get("lastName"));
			user.setRole((String)row.get("role"));
			user.setCreationDateTime(((Timestamp) 
				row.get("creationDateTime")).toLocalDateTime());
			user.setUpdateDateTime(((Timestamp)
				row.get("updateDateTime")).toLocalDateTime());
			userList.add(user);
		}
		
		return userList;
	}
	
	public String addUser(User user)
	{
		String sqlQuery = "insert into users (firstName, lastName, "
			+ "role, creationDateTime, updateDateTime) values (:firstName, "
			+ ":lastName, :role, :creationDateTime, :updateDateTime)";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", user.getId());
		params.addValue("firstName", user.getFirstName());
		params.addValue("lastName", user.getLastName());
		params.addValue("role", user.getRole());
		params.addValue("creationDateTime", user.getCreationDateTime());
		params.addValue("updateDateTime", user.getCreationDateTime());
		
		jdbc.update(sqlQuery, params);
		return String.format("Successfully added user %s %s.", 
				user.getFirstName(), user.getLastName());
	}
	
	public String deleteUser(int id)
	{
		String sqlQuery = "Delete from users where id = :id;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		jdbc.update(sqlQuery, params);
		return String.format("Successfully deleted user.");
	}
	
	public String updateUser(User user)
	{
		String sqlQuery = "UPDATE Users set firstName = :firstName, "
				+ "lastName = :lastName, role = :role, creationDateTime = "
				+ ":creationDateTime, updateDateTime = :updateDateTime "
				+ "where id = :id;";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("firstName", user.getFirstName())
		.addValue("lastName", user.getLastName())
		.addValue("role", user.getRole())
		.addValue("creationDateTime", user.getCreationDateTime())
		.addValue("updateDateTime", user.getUpdateDateTime())
		.addValue("id", user.getId());
		
		jdbc.update(sqlQuery, params);
		return String.format("Successfully updated user %s %s.", 
				user.getFirstName(), user.getLastName());
	}
	
	public User getUser(int id)
	{
		String sqlQuery = "Select * from Users where id = :id;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		List<Map<String, Object>> rows = jdbc.queryForList(sqlQuery, params);
		User user = new User();
		Map<String, Object> row = rows.get(0);
		
		user.setId((int)row.get("id"));
		user.setFirstName((String)row.get("firstName"));
		user.setLastName((String)row.get("lastName"));
		user.setRole((String)row.get("role"));
		user.setCreationDateTime(((Timestamp) 
				row.get("creationDateTime")).toLocalDateTime());
		user.setUpdateDateTime(((Timestamp)
				row.get("updateDateTime")).toLocalDateTime());
		return user;
	}

}
