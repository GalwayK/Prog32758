package ca.sheridancollege.galwayk.ThymeleafController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ca.sheridancollege.galwayk.beans.BookBean;
import ca.sheridancollege.galwayk.beans.Inventory;

@Controller
public class ThymeleafController 
{
	private Inventory excInventory;
	
	public ThymeleafController(Inventory excInventory) 
	{
		this.excInventory = excInventory;
	}
	
	@GetMapping("/")
	public String loadIndex()
	{
		return "exercise.html";
	}

	@GetMapping("/submit")
	public String submitForm(Model model)
	{
		excInventory.setItemId(1234456L);
		excInventory.setItemName("Apple");
		excInventory.setItemQuantity(1);
		model.addAttribute("inv", excInventory);
		
		return "output.html";
	}
	
	
	
	
	
	
	
//	@GetMapping("/")
//	public String getHome(Model model)
//	{ 
//		
//		BookBean b = new BookBean(123456789, "Cheese Stories", 
//		        "Wendi Jollymore", 29.95, 1);
//		
//		model.addAttribute("book", b);
//
//		model.addAttribute("cat", "Sydney");
//
//		return "index.html";
//	}
	
}
