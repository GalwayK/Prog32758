package ca.sheridanc.galwayk.MessageController;

import javax.servlet.http.HttpSession;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.sheridanc.galwayk.Beans.Message;
import ca.sheridanc.galwayk.Database.DataAccess;

@Controller
public class MessageController 
{
	private DataAccess dataAccess;
	
	public MessageController(DataAccess dataAccess)
	{
		this.dataAccess = dataAccess;
	}
	
	@GetMapping("/")
	public String getIndex()
	{
		return "index.html";
	}
	
	@GetMapping("/login")
	public String getLogin(Model model, 
			HttpSession session)
	{
		if (session.getAttribute("status") != null)
		{
			model.addAttribute("status", session.getAttribute("status"));
			session.setAttribute("status", null);
		}
		return "login.html";
	}
	
	@GetMapping("/messageBoard")
	public String getMessageBoard(Model model, 
		HttpSession session)
	{
		//Only for first load
		if (session.getAttribute("messageList") == null)
		{
			session.setAttribute("messageList", dataAccess.getMessageList());
		}
		if (session.getAttribute("status") != null)
		{
			model.addAttribute("status", session.getAttribute("status"));
			session.setAttribute("status", null);
		}
		model.addAttribute("messageList", session.getAttribute("messageList"));
		//This code adds the user's names and roles to the model if we want them. 
		//We could also access them with sec:authentication
		//model.addAttribute("username", 
		//	SecurityContextHolder.getContext().getAuthentication().getName());
		//model.addAttribute("roles", 
		//	SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return "/messageBoard/messageBoard.html";
	}
	
	@GetMapping("/messagePost")
	public String getMessagePost(Model model)
	{
		if (model.getAttribute("message") == null)
		{
			model.addAttribute("message", new Message());
		}
		return "/messagePost/messagePost.html";
	}
	
	@GetMapping("/editMessage/{id}")
	public String getMessageEdit(@PathVariable int id, 
		RedirectAttributes redirect)
	{
		redirect.addFlashAttribute("message", dataAccess.getMessage(id));
		return "redirect:/messagePost";
	}
	
	@GetMapping("/logout")
	public String getLogout(HttpSession session)
	{
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/submitMessage")
	public String postSubmitMessage(@ModelAttribute Message message, 
		RedirectAttributes redirect, HttpSession session)
	{
		if (message.getMessageId() == 0)
		{
			dataAccess.insertMessage(message);
			redirect.addFlashAttribute("status", "Message inserted successfully.");
		}
		else 
		{
			dataAccess.updateMessage(message);
			redirect.addFlashAttribute("status", "Message updated successfully.");
		}
		session.setAttribute("messageList", dataAccess.getMessageList());
		return "redirect:/messageBoard";
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
	public String postRegister(@RequestParam String originalPassword, 
		@RequestParam String verifyPassword, @RequestParam String username, 
		@RequestParam(defaultValue = "false") boolean isWriter, RedirectAttributes redirect, 
		HttpSession session)
	{
		if (!originalPassword.equals(verifyPassword))
		{
			return "redirect:/register?noMatch";
		}
		int effectedRows = dataAccess.addUserToUserAccount(username, originalPassword);
		if (effectedRows != 0)
		{
			int accountId = dataAccess.findUserAccount(username).getAccountId();
			dataAccess.addUserToUserAccountRole(accountId, 2);
			
			if (isWriter)
			{
				dataAccess.addUserToUserAccountRole(accountId, 1);
			}
			session.setAttribute("status", username  + " successfully added.");
			return "redirect:/messageBoard";	
		}
		session.setAttribute("status", username + " already exists.");
		return "redirect:/register?usernameExists";
	}
	
	@GetMapping("/denied")
	public String getDenied()
	{
		return "denied.html";
	}
}
