package com.example.demo.SecurityController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController
{
	
	@GetMapping("/")
	public String goIndex()
	{
		return "index.html";
	}
	
	@GetMapping("/exercise")
	public String getExercise()
	{
		return "exercise/exercise.html";
	}
	
	@GetMapping("/login")
	public String getLogin()
	{
		return "login.html";
	}
	
	
	@PostMapping("/logout")
	public String postLogout()
	{
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String getLogout()
	{
		return "redirect:/";
		
	}
	
	@GetMapping("/secure")
	public String getSecure()
	{
		return "secure/index.html";
	}
	
	@GetMapping("/denied")
	public String getDenied()
	{
		System.out.println("Going to error page.");
		return "denied/error.html";
	}

	
}
