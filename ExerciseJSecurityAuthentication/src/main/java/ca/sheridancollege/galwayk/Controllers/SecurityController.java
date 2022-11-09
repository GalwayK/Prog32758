package ca.sheridancollege.galwayk.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController 
{
	@GetMapping("/")
	public String goIndex()
	{
		return "index.html";
	}
	
	@GetMapping("/user")
	public String goToUserSecured()
	{
		return "secured/user/index.html";
	}
	
	@GetMapping("/manager")
	public String goToManagerSecured()
	{
		return "secured/manager/index.html";
	}
	
	@GetMapping("/secured")
	public String goToSecured()
	{
		return "secured/gateway.html";
	}
}
