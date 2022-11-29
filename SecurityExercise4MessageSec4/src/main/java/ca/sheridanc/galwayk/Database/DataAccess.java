package ca.sheridanc.galwayk.Database;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridanc.galwayk.Beans.Message;
import ca.sheridanc.galwayk.Beans.UserAccount;

@Repository
public class DataAccess 
{
	private NamedParameterJdbcTemplate jdbc;
	private BCryptPasswordEncoder passwordEncoder;
	
	public DataAccess(NamedParameterJdbcTemplate jdbc, 
		BCryptPasswordEncoder passwordEncoder)
	{
		this.jdbc = jdbc;
		this.passwordEncoder = passwordEncoder;
	}

	public List<Message> getMessageList() 
	{
		String strQuery = "Select * From message;";
		
		BeanPropertyRowMapper<Message> rowMapper = new BeanPropertyRowMapper<Message>(Message.class);
		
		return jdbc.query(strQuery, rowMapper);
	}

	public int updateMessage(Message message) 
	{
		String strQuery = "Update Message set message = :message "
				+ "where messageId = :messageId;";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("message", message.getMessage())
		.addValue("messageId", message.getMessageId());
		
		return jdbc.update(strQuery, params);
	}

	public int insertMessage(Message message) 
	{
		String strQuery = "Insert into Message (message) Values "
				+ "(:message);";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("message", message.getMessage());
		
		return jdbc.update(strQuery, params);
	}

	public Message getMessage(int id) 
	{
		String strQuery = "Select * from Message where messageId = :id;";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		BeanPropertyRowMapper<Message> rowMapper = 
			new BeanPropertyRowMapper<Message>(Message.class);
		
		return jdbc.queryForObject(strQuery, params, rowMapper);
	}
	
	public UserAccount findUserAccount(String username) 
	{
		String strQuery = "Select * From UserAccount where username = :username;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", username);
		BeanPropertyRowMapper<UserAccount> rowMapper = 
			new BeanPropertyRowMapper<UserAccount>(UserAccount.class);
		
		return jdbc.queryForObject(strQuery, params, rowMapper);
	}
	
	public List<String> getRolesById(int accountId)
	{
		String strQuery = "Select rolename from role where roleid in "
				+ "(Select roleid from useraccount_role where accountId = :accountId);";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("accountId", accountId);
		return jdbc.queryForList(strQuery, params, String.class);
	}

	public int addUserToUserAccount(String username, String password) 
	{
		String strQuery = "INSERT INTO UserAccount (username, password, enabled) "
				+ "VALUES (:username, :password, true)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", username);
		params.addValue("password", passwordEncoder.encode(password));
		
		try 
		{
			return jdbc.update(strQuery, params);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return 0;
	}

	public int addUserToUserAccountRole(int accountId, int roleId)
	{
		String strQuery = "INSERT INTO UserAccount_Role (accountId, roleId) "
				+ "VALUES (:accountId, :roleId);";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("accountId", accountId)
		.addValue("roleId", roleId);
		
		return jdbc.update(strQuery, params);
	}
	
}
