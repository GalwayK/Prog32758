package ca.sheridancollege.galwayk.StudentDatabase.Database;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DataConfig 
{
	@Bean
	public NamedParameterJdbcTemplate 
		namedParameterJdbcTemplate(DataSource dataSource)
	{
		return new NamedParameterJdbcTemplate(dataSource);
	}
		
}
