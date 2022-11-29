package ca.sheridancollege.galwayk.ExerciseCreateRead.Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.galwayk.ExerciseCreateRead.Beans.Book;
import ca.sheridancollege.galwayk.ExerciseCreateRead.Beans.Genre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DataAccess 
{
	
	protected NamedParameterJdbcTemplate jdbc;
	
	public DataAccess(NamedParameterJdbcTemplate jdbc)
	{
		this.jdbc = jdbc;
	}
	
	public int addBook(Book book)
	{
		
		String sqlQuery = "insert into books "
				+ "(isbn, title, author, price, genre) "
				+ "values "
				+ "(:isbn, :title, :author, :price, :genre);";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("isbn", book.getIsbn())
		.addValue("title", book.getTitle())
		.addValue("author", book.getAuthor())
		.addValue("price", book.getPrice())
		.addValue("genre", book.getGenre());
		
		return jdbc.update(sqlQuery, params);
		
	}
	
	public List<Book> getBooks()
	{
		ArrayList<Book> bookList = new ArrayList<Book>();
		String sqlQuery = "select * from books;";
		List<Map<String, Object>> rows = 
			jdbc.queryForList(sqlQuery, new HashMap());
		
		for (Map<String, Object> row : rows)
		{
			Book book = new Book();
			book.setIsbn((int)(row.get("isbn")));
			book.setAuthor((String)(row.get("author")));
			book.setTitle((String)(row.get("title")));
			book.setPrice((double)(row.get("price")));
			book.setGenre((String)(row.get("genre")));
			bookList.add(book);
		}
		return bookList;
	}
	
	public Book findBook(String bookTitle)
	{
		String sqlQuery = "select * from books where title = :bookTitle;";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("bookTitle", bookTitle);
		List<Map<String, Object>> rows = jdbc.queryForList(sqlQuery, params);
		
		Book book = null;
		
		for (Map<String, Object> row : rows)
		{
			book = new Book(
				(String)row.get("title"), 
				(int)row.get("isbn"),
				(String)row.get("author"),
				(double)row.get("price"), 
				(String)row.get("genre")
				);
		}
		return book;
	}
	
	public List<Genre> getGenres()
	{
		ArrayList<Genre> genreList = new ArrayList<Genre>();
		
		String sqlQuery = "select * from genres;";
		
		List<Map<String, Object>> rows = 
			jdbc.queryForList(sqlQuery, new HashMap());
		
		for (Map<String, Object> row : rows)
		{
			Genre genre = new Genre(
					(String)(row.get("genre")));
			genreList.add(genre);
		}
		return genreList;
	}
}
