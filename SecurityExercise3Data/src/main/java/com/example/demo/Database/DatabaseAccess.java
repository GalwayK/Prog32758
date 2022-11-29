package com.example.demo.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.Beans.User;

@Repository
public class DatabaseAccess 
{
	private NamedParameterJdbcTemplate jdbc;
	
	public DatabaseAccess(NamedParameterJdbcTemplate jdbc)
	{
		this.jdbc = jdbc;
	}
	
	public User findUserAccount(String email)
	{
		String sqlQuery = "Select * from Users where email = :email;";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("email", email);
		
		//Note, user attributes must the same as the table!!
		BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		
		return jdbc.queryForObject(sqlQuery, params, rowMapper);
	}
	
	public List<String> getRolesById(long userId)
	{
		String sqlQuery = "Select user_role.userid, roles.rolename "
				+ "from user_role, roles where user_role.roleid = roles.roleid "
				+ "AND user_role.userid = :userId ;";
		
		ArrayList<String> roles = new ArrayList<String>();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userId", userId);
		List<Map<String, Object>> rows = jdbc.queryForList(sqlQuery, params);
		
		for (Map<String, Object> row : rows)
		{
			roles.add((String)row.get("roleName"));
		}
		return roles;
	}
}
