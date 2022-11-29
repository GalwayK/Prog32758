package ca.sheridancollege.galwayk.DataController;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.galwayk.Beans.Book;
import ca.sheridancollege.galwayk.Beans.Container;
import ca.sheridancollege.galwayk.Beans.Inventory;



@Controller
public class DataController 
{
	Book testBook = new Book(123, "Leviathan Wakes", "James Corey", 10.99, 2);
	ArrayList<Book> bookList = new ArrayList<Book>();
	
	@GetMapping("/")
	public String enterBook(Model model)
	{
		return "index.html";
	}
	
	@PostMapping("/outputPage")
	public String getOutput(@RequestParam(defaultValue = "") String itemId, 
			@RequestParam(defaultValue=("")) String itemName, 
			@RequestParam(defaultValue = "0") int itemQuantity, 
			Model model)
	{
		bookList.add(testBook);
		bookList.add(new Book(123, "Caliban's War", "James Corey", 10.99, 2));
		bookList.add(new Book(123, "Abaddon's Gate", "James Corey", 10.99, 2));
		bookList.add(new Book(123, "Cibola Burn", "James Corey", 10.99, 2));
		bookList.add(new Book(123, "Nemeis Games", "James Corey", 10.99, 2));
		
		model.addAttribute("books", bookList);
		
		return "output.html";
	}
	
	
	@GetMapping("/containerInput")
	public String makeContainer(Model model, 
			@RequestParam int id,
			@RequestParam String name, 
			@RequestParam double volume)
	{
		
		Container container = new Container(id, name, volume);
		model.addAttribute("container", container);
		return "outputContainer.html";
	}
}
