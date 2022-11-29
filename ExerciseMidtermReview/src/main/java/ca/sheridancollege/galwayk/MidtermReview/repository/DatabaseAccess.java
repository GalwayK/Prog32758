package ca.sheridancollege.galwayk.MidtermReview.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.galwayk.MidtermReview.Beans.Genre;
import ca.sheridancollege.galwayk.MidtermReview.Beans.VideoGame;

@Repository
public class DatabaseAccess 
{
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public DatabaseAccess(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
	{
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	public VideoGame getVideoGame(int id)
	{
		String sqlQuery = "Select * from VideoGames where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		BeanPropertyRowMapper<VideoGame> rowMapper = 
			new BeanPropertyRowMapper(VideoGame.class);
		return namedParameterJdbcTemplate.queryForObject(sqlQuery, params, rowMapper);
	}
	
	public String deleteVideoGame(int id)
	{
		
		String sqlQuery = "delete from videogames where id = :id;";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("id", id);
		int result = namedParameterJdbcTemplate.update(sqlQuery, params);
		
		if (result == 1)
		{
			return "Game successfully deleted.";
		}
		else 
		{
			return "Game was not deleted.";
		}
	}
	
	public String deleteAllGames()
	{
		String sqlQuery = "Delete from videogames;";	
		MapSqlParameterSource params = new MapSqlParameterSource();
		int result = namedParameterJdbcTemplate.update(sqlQuery, params);
		
		return "All entries deleted.";
	}
	
	public List<VideoGame> getGameList()
	{
		MapSqlParameterSource params = new MapSqlParameterSource();
		BeanPropertyRowMapper<VideoGame> rowMapper = new BeanPropertyRowMapper<>(VideoGame.class);
		String sqlQuery = "select * from videogames;";
		
		List<VideoGame> gameList = namedParameterJdbcTemplate.query(sqlQuery, params, rowMapper);
		
		return gameList;
	}
	
	public List<Genre> getGenreList()
	{
		String sqlQuery = "Select genre from genres;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		BeanPropertyRowMapper<Genre> rowMapper = new BeanPropertyRowMapper(Genre.class);
		List<Genre> genres = namedParameterJdbcTemplate.query(sqlQuery, rowMapper);
		System.out.println(genres);
		return genres;
	}
	
	public String updateGame(VideoGame game)
	{
		String sqlQuery;
		String sqlAction;
		if (game.getId() == 0)
		{
			sqlAction = "added";
			sqlQuery = "insert into videogames "
					+ "(title, developer, releaseDate, genre) "
					+ "values "
					+ "(:title, :developer, :releaseDate, :genre);";
		}
		else 
		{
			sqlAction = "updated";
			sqlQuery = "update videogames set title = :title, "
	 			+ "developer = :developer, releaseDate = :releaseDate, "
	 			+ "genre = :genre where id = :id;";
		}
	 	
	 	MapSqlParameterSource params = new MapSqlParameterSource();
	 	params.addValue("title", game.getTitle())
	 	.addValue("developer", game.getDeveloper())
	 	.addValue("releaseDate", game.getReleaseDate())
	 	.addValue("genre", game.getGenre())
	 	.addValue("id", game.getId());
	 	
	 	int result = namedParameterJdbcTemplate.update(sqlQuery, params);
	 	
	 	if (result == 1)
	 	{
	 		return String.format("Game %s has been successfully %s.", 
	 			game.getTitle(), sqlAction);
	 	}
	 	else 
	 	{
	 		return "Game %s has not been successfully %s.";
	 	}
	
	}
	
	public String addGame(VideoGame game)
	{
		return "";
	}
	
	
}
	
