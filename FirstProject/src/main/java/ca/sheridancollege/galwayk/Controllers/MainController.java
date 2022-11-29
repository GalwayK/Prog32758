package ca.sheridancollege.galwayk.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController 
{
	@GetMapping("/templatePage")
	public String getTemplatePage()
	{
		return "templatePage.html";
	}
	
	@GetMapping({"/", "index", "", "index.html"})
	public String getIndexPage()
	{
		return "index.html";
	}

	@GetMapping({"about", "about.html"})
	public String getAboutPage()
	{
		return "about.html";
	}

}
