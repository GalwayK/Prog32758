package ca.sheridancollege.galwayk.ExerciseDataTemplateBook.Repository;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseAccess
{
	@Autowired 
	protected NamedParameterJdbcTemplate jdbc;
	
	public void addBook()
	{
		String sqlQuery = "INSERT INTO Books (title, author, price, genre) "
				+ "VALUES ('Leviathan Wakes', "
				+ "'James Corey', 20.99, 2);";
		
		jdbc.update(sqlQuery, new HashMap());
	}
    
}
