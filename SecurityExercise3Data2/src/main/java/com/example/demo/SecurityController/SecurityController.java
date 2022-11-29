package com.example.demo.SecurityController;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Database.DatabaseAccess;

@Controller
public class SecurityController
{
	private DatabaseAccess dataAccess;
	
	public SecurityController(DatabaseAccess dataAccess)
	{
		this.dataAccess = dataAccess;
	}
	
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
	public String postLogout(HttpSession session)
	{
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String getLogout()
	{
		return "redirect:/";
	}
	
	@GetMapping("/secure")
	public String getSecure(Model model, HttpSession session)
	{
		if (session.getAttribute("status") != null)
		{
			model.addAttribute(session.getAttribute("status"));
			session.setAttribute("status", null);
		}
		
		model.addAttribute("currentUser", 
			SecurityContextHolder.getContext().getAuthentication().getName());
		return "secure/index.html";
	}
	
	@GetMapping("/denied")
	public String getDenied()
	{
		System.out.println("Going to error page.");
		return "denied/error.html";
	}
	
	@GetMapping("/register")
	public String getRegister(Model model, HttpSession session)
	{
		if (session.getAttribute("status") != null)
		{
			model.addAttribute("status", session.getAttribute("status"));
			session.setAttribute("status", null);
		}
		return "register.html";
	}
	
	@PostMapping("/register")
	public String postRegister(@RequestParam String username, 
		@RequestParam String originalPassword, 
		@RequestParam String copyPassword, 
		HttpSession session)
	{
		if (!originalPassword.equals(copyPassword))
		{
			return "redirect:/register?PasswordNoMatch";
		}
		try 
		{
			int effectedRows = dataAccess.insertUser(originalPassword, username);
			
			if (effectedRows != 0)
			{
				dataAccess.insertUsersRoles(dataAccess.findUserAccount(username).getUserid(), 2);
			}
			session.setAttribute("status", username + " successfully added.");
			return "redirect:/secure";
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			session.setAttribute("status", username + " already exists.");
			return "redirect:/register";
		}
	}

	
}
