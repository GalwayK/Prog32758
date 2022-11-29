package ca.sheridancollege.galwayk.ExerciseFive.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.galwayk.ExerciseFive.database.DatabaseAccess;

@Controller
public class HomeController 
{
	@Autowired 
	@Lazy
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired 
	private JdbcUserDetailsManager jdbcUserDetailsManager;
	
	@Autowired 
	private DatabaseAccess dataAccess;
	
	@GetMapping("/")
	public String goIndex(Authentication authentication, Model model)
	{
		if (authentication != null)
		{
			String strUserName = authentication.getName();
			List<String> roles = new ArrayList<>();
			
			for (GrantedAuthority authority : authentication.getAuthorities())
			{
				roles.add(authority.getAuthority());
			}
			model.addAttribute("userName", strUserName);
			model.addAttribute("roles", roles);
		}
		return "index.html";
	}
	
	@GetMapping("/user")
	public String goUserIndex()
	{
		return "secured/user/index.html";
	}
	
	@GetMapping("/manager")
	public String goManagerIndex()
	{
		return "secured/manager/index.html";
	}
	
	@GetMapping("/secured")
	public String goSecuredGateway()
	{
		return "secured/gateway.html";
	}
	
	@GetMapping("/login")
	public String login()
	{
		return "login.html";
	}
	
	@GetMapping("/denied")
	public String goToDenied()
	{
		return "error/denied.html";
	}
	
	@GetMapping("/manager/addUser")
	public String newUser(Model model)
	{
		List<String> authorities = dataAccess.getAuthorities();
		
		model.addAttribute("authorities", authorities);
		
		return "/secured/manager/newUser.html";
	}
	
	@PostMapping("/manager/newUser")
	public String addUser(@RequestParam String userName, 
		@RequestParam String password, @RequestParam String[] authorities, 
		Model Model)
	{
		List<GrantedAuthority> authorityList = new ArrayList<>();
		
		for (String authority : authorities)
		{
			authorityList.add(new SimpleGrantedAuthority(authority));
		}
		String encodedPassword = passwordEncoder.encode(password);
		
		User user = new User(userName, encodedPassword, authorityList);
		
		jdbcUserDetailsManager.createUser(user);
		
		return "/secured/gateway.html";
	}
}
