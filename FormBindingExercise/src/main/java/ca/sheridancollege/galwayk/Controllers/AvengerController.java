package ca.sheridancollege.galwayk.Controllers;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.galwayk.Beans.Avenger;

@Controller
public class AvengerController 
{
	private CopyOnWriteArrayList<Avenger> avengerList = 
		new CopyOnWriteArrayList<Avenger>();
	
	
	
	@GetMapping("/")
	public String goHome(Model model)
	{
		model.addAttribute("avengers", avengerList);
		return "index.html";
	}
	
	@GetMapping("/addPage")
	public String goToAddPage(Model model)
	{
		System.out.println("Going to Add Page");
		model.addAttribute("avenger", new Avenger());
		
		return "addAvenger.html";
	}
	
	@PostMapping("/addAvenger")
	public String addAvenger(Model model, @ModelAttribute Avenger avenger)
	{
		avengerList.add(avenger);
		model.addAttribute("avengers", avengerList);
		//Prevents form resubmission on refresh
		return "redirect:/";
	}
	
}
