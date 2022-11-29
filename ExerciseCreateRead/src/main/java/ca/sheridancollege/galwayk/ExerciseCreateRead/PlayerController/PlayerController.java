package ca.sheridancollege.galwayk.ExerciseCreateRead.PlayerController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.galwayk.ExerciseCreateRead.Beans.Player;
import ca.sheridancollege.galwayk.ExerciseCreateRead.Repository.DatabaseAccess;

@Controller
public class PlayerController 
{

	private DatabaseAccess dataAccess;
	
	private PlayerController(DatabaseAccess dataAccess)
	{
		this.dataAccess = dataAccess;
	}
	
	@PostMapping("/submitPlayer")
	public String submitPlayer(@ModelAttribute Player player)
	{
		dataAccess.addPlayer(player);
		return "redirect:/";
	}
	
	@GetMapping("/")
	public String goIndex(Model model)
	{
		model.addAttribute("teamList", dataAccess.getTeams());
		model.addAttribute("playerList", dataAccess.getPlayers());
		model.addAttribute("playerTemplate", new Player());
		return "index.html";
	}
}
