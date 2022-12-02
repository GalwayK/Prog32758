package ca.sheridanc.on.galway.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class BeanConfiguration 
{
	@Bean
	public NamedParameterJdbcTemplate jdbc(DataSource dataSource)
	{
		return new NamedParameterJdbcTemplate(dataSource);
	}
}
