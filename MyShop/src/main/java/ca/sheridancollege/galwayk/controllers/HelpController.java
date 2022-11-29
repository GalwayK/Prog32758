package ca.sheridancollege.galwayk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelpController 
{

	
	@GetMapping("/help*")
	public String getHelp()
	{
		return "Help/index.html";
	}

}
