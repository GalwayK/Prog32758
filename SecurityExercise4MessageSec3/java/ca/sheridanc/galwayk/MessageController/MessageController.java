package ca.sheridanc.galwayk.MessageController;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/messageBoard")
	public String getMessageBoard(Model model, 
		HttpSession session)
	{
		//Only for first load
		if (session.getAttribute("messageList") == null)
		{
			session.setAttribute("messageList", dataAccess.getMessageList());
		}
		model.addAttribute("messageList", session.getAttribute("messageList"));
		
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
}
