package ca.sheridancollege.galwayk.TeamController;

import ca.sheridancollege.galwayk.Teams;
import ca.sheridancollege.galwayk.Beans.HockeyPlayer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeamController 
{
	
	List<HockeyPlayer> playerList = new CopyOnWriteArrayList<HockeyPlayer>();
	
	@GetMapping("/")
	public String goToIndex(Model model)
	{
		model.addAttribute("playerList", playerList);
		model.addAttribute("teams", Teams.values());
		model.addAttribute("playerTemplate", new HockeyPlayer());
		return "index.html";
	}
	
	@PostMapping("/addPlayer")
	public String addPlayerToList(@ModelAttribute HockeyPlayer player)
	{
		playerList.add(player);
		return "redirect:/";
	}
	
}
