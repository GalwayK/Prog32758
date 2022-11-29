package ca.sheridancollege.galwayk.ExerciseCreateRead.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.sheridancollege.galwayk.ExerciseCreateRead.Beans.Book;
import ca.sheridancollege.galwayk.ExerciseCreateRead.Database.DataAccess;

@Controller
public class BookController 
{
	
	private DataAccess dataAccess;
	
	private BookController(DataAccess dataAccess)
	{
		this.dataAccess = dataAccess;
	}
	
	@GetMapping("/")
	public String goIndex(Model model)
	{
		model.addAttribute("genreList", dataAccess.getGenres());
		model.addAttribute("bookList", dataAccess.getBooks());
		model.addAttribute("bookTemplate", new Book());
		return "index.html";
	}
	
	@PostMapping("submitBook")
	public String submitBook(@ModelAttribute Book book)
	{
		dataAccess.addBook(book);
		return "redirect:/";
	}
	
	@PostMapping("searchBook")
	public String searchBook(@RequestParam String bookTitle,
			RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addFlashAttribute("foundBook", dataAccess.findBook(bookTitle));
		return "redirect:/";
	}
}
