package ca.sheridancollege.galwayk.ExerciseDataTemplateBook.BookController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ca.sheridancollege.galwayk.ExerciseDataTemplateBook.Repository.DatabaseAccess;

@Controller
public class BookController 
{
	
	private DatabaseAccess dataAccess;
	
	private BookController(DatabaseAccess dataAccess)
	{
		this.dataAccess = dataAccess;
	}
	
	@GetMapping("/dbtest")
	public String testBook()
	{
		dataAccess.addBook();
		return "index.html";
	}
	

}
