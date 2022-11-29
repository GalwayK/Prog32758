package ca.sheridancollege.galwayk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ca.sheridancollege.galwayk.beans.BookBean;

@Controller
public class ThymeleafController 
{
	
	private BookBean book;
	
	private ThymeleafController(BookBean book)
	{
		this.book = book;
	}
	
	@GetMapping()
	public String getIndex(Model model)
	{
		book.setAuthor("James Corey");
		book.setTitle("Leviathan Wakes");
		book.setGenre(1);
		book.setIsbn(123456789L);
		book.setPrice(20.99);
		
		model.addAttribute("html", "<h2> This a html heading! </h1>");
		model.addAttribute("book", book);
		model.addAttribute("firstName", "Kyle");
		model.addAttribute("lastName", "Galway");
		model.addAttribute("number", 1);
		
		return "index.html";
	}
	
}
