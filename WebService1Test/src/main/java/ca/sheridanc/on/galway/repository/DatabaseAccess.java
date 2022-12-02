package ca.sheridanc.on.galway.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ca.sheridanc.on.galway.beans.Container;

@Repository
public class DatabaseAccess 
{
	private NamedParameterJdbcTemplate jdbc;
	
	public DatabaseAccess(NamedParameterJdbcTemplate jdbc)
	{
		this.jdbc = jdbc;
	}
	
	public Container readContainerByName(String containerName)
	{
		String sqlQuery = "Select * from Containers where "
				+ "containerName = :containerName;";
		
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("containerName", containerName);
		
		BeanPropertyRowMapper<Container> rowMapper = 
			new BeanPropertyRowMapper<Container>(Container.class);
		
		return jdbc.queryForObject(sqlQuery, paramMap, rowMapper);
	}
	
	public List<Container> readContainers()
	{
		String sqlQuery = "Select * from Containers;";
		
		BeanPropertyRowMapper<Container> rowMapper = 
			new BeanPropertyRowMapper<Container>(Container.class);
		
		return jdbc.query(sqlQuery, rowMapper);
	}
	
	public Container readContainerById(int id)
	{
		String sqlQuery = "Select * from Containers where "
				+ "id = :id;";
		
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("id", id);
		
		BeanPropertyRowMapper<Container> rowMapper = 
			new BeanPropertyRowMapper<Container>(Container.class);
		
		return jdbc.queryForObject(sqlQuery, paramMap, rowMapper);
	}
	
	public Integer createContainer(Container container)
	{
		
		String sqlQuery = "INSERT INTO CONTAINERS (containerName, volume) "
				+ "VALUES (:name, :volume);";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("name", container.getContainerName())
			.addValue("volume", container.getVolume());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(sqlQuery, paramMap, keyHolder);
		return (Integer)keyHolder.getKey();
	}
	
	public Integer deleteContainers()
	{
		String sqlQuery = "DELETE FROM CONTAINERS;";
		
		return jdbc.update(sqlQuery, new HashMap());
	}
	
	public Long readContainerCount()
	{
		String sql = "Select count(*) FROM Containers;";
		return jdbc.queryForObject(sql, new HashMap(), Long.TYPE);
	}
	
	public Integer createContainers(List<Container> containerList)
	{
		int effectedRows = 0;
		for (Container container : containerList)
		{
			effectedRows = effectedRows + this.createContainer(container);
		}
		return effectedRows;
	}

	public Integer updateContainer(Container container) 
	{
		String sqlQuery = "Update Containers set containerName = :name, volume = :volume "
				+ "where id = :id;";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		
		paramMap.addValue("name", container.getContainerName())
			.addValue("volume", container.getVolume())
			.addValue("id", container.getId());
		
		return jdbc.update(sqlQuery, paramMap);
	}

	public void deleteContainer(int id) 
	{
		String sqlQuery = "DELETE FROM CONTAINERS WHERE ID = :id;";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("id", id);
		jdbc.update(sqlQuery, paramMap);
	}
	
}
