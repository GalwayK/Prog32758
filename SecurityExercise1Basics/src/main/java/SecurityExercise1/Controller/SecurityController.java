package SecurityExercise1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController 
{
	@GetMapping("/logout")
	public String getLogout()
	{
		return "index.html";
	}
	
	@PostMapping("/logout")
	public String postLogout()
	{
		return "index.html";
	}
	
	@GetMapping("/")
	public String goIndex()
	{
		return "index.html";
	}

}
