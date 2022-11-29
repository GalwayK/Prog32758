package ca.sheridancollege.galwayk.ExerciseBookFormBinding.BookControllers;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ca.sheridancollege.galwayk.ExerciseBookFormBinding.Genres;
import ca.sheridancollege.galwayk.ExerciseBookFormBinding.Beans.BookBean;

@Controller
public class BookController 
{
	CopyOnWriteArrayList<BookBean> bookList = 
		new CopyOnWriteArrayList<BookBean>();
	
	@PostMapping("/submitBook")
	public String submitBook(@ModelAttribute BookBean book)
	{
		bookList.add(book);
		return "redirect:/";
	}
	
	@GetMapping("/")
	public String goToIndex(Model model)
	{
		model.addAttribute("bookList", bookList);
		model.addAttribute("genres", Genres.values());
		model.addAttribute("bookTemplate", new BookBean());
		return "index.html";
	}
	
	@GetMapping("/deleteBooks")
	public String deleteBooks()
	{
		bookList.clear();
		return "redirect:/";
	}
}
