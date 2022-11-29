package ca.sheridancollege.galwayk.ExerciseCreateRead.PlayerController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String submitPlayer(@RequestParam String firstName, 
			@RequestParam String lastName, @RequestParam String teamName)
	{
		Player newPlayer = new Player();
		newPlayer.setFirstName(firstName);
		newPlayer.setLastName(lastName);
		newPlayer.setTeam(teamName);
		
		dataAccess.addPlayer(newPlayer);
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
	
	@PostMapping("/updatePlayer")
	public String updatePlayer(@ModelAttribute Player player)
	{
		dataAccess.updatePlayer(player);
		return "redirect:/";
	}
	
	@PostMapping("/deletePlayer/{id}")
	public String deletePlayer(@PathVariable int id)
	{
		dataAccess.deletePlayer(id);
		return "redirect:/";
	}
}
