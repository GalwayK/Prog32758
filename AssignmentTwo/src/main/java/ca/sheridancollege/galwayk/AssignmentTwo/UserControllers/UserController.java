package ca.sheridancollege.galwayk.AssignmentTwo.UserControllers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.sheridancollege.galwayk.AssignmentTwo.Beans.User;
import ca.sheridancollege.galwayk.AssignmentTwo.Database.DataAccess;

@Controller
public class UserController 
{
	DataAccess dataAccess;
	
	public UserController(DataAccess dataAccess)
	{
		this.dataAccess = dataAccess;
	}
	
	ArrayList<User> userList = new ArrayList<User>();
	
	@GetMapping("/")
	public String goListView(Model model)
	{
		if (model.getAttribute("user") == null)
		{
			model.addAttribute("user", new User());
		}
		
		model.addAttribute("userList", dataAccess.getUserList());
		return "index.html";
	}
	
	@PostMapping("/modifyUser/{id}")
	public String modifyUser(@ModelAttribute User user, 
		RedirectAttributes redirectAttributes)
	{
		user.setUpdateDateTime(LocalDateTime.now());
		if (user.getId() == 0)
		{
			user.setCreationDateTime(LocalDateTime.now());
			redirectAttributes.addFlashAttribute("message", 
				dataAccess.addUser(user));
		}
		else 
		{
			redirectAttributes.addFlashAttribute("message", 
				dataAccess.updateUser(user));
		}
		return "redirect:/";
	}
	
	@GetMapping("/editUser/{id}")
	public String goEditView(@PathVariable int id, 
		RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addFlashAttribute("user", dataAccess.getUser(id));
		return "redirect:/";
	}
	
	@GetMapping("/deleteUser/{id}")
	public String updateUser(@PathVariable int id, 
		RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addFlashAttribute("message", 
			dataAccess.deleteUser(id));
		return "redirect:/";
	}
	
	@GetMapping("/error")
	public String goError()
	{
		return "error.html";
	}
}
