package ca.sheridancollege.galwayk.MidtermReview.GameController;

import org.slf4j.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.sheridancollege.galwayk.MidtermReview.Beans.VideoGame;
import ca.sheridancollege.galwayk.MidtermReview.repository.DatabaseAccess;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class GameController 
{
	
	public static Logger log = LoggerFactory.getLogger(GameController.class);
	
	Logger logger = LoggerFactory.getLogger(GameController.class);
	
	private DatabaseAccess databaseAccess;
	public GameController(DatabaseAccess databaseAccess)
	{
		logger.info("Hello");;
		this.databaseAccess = databaseAccess;
	}
	
	@GetMapping("/")
	public String goIndex(Model model)
	{
		logger.info("Loading Index logger");
		model.addAttribute("gameList", databaseAccess.getGameList());
		model.addAttribute("genreList", databaseAccess.getGenreList());
		if (model.getAttribute("game") == null)
		{
			model.addAttribute("game", new VideoGame());
		}
		logger.warn("This is a warning message.");
		logger.info("This is an info message.");
		logger.debug("This is a debug message.");
		logger.trace("This is a trace message.");
		logger.error("This is not supposed to happen.");
		return "index.html";
		
	}
	
	@PostMapping("/submitGame")
	public String updateGame(RedirectAttributes redirect, 
			@ModelAttribute VideoGame game)
	{
		redirect.addFlashAttribute("message", databaseAccess.updateGame(game));
		return "redirect:/";
	}
	
	@GetMapping("/editGame/{id}")
	public String editGame(@PathVariable int id, RedirectAttributes redirect)
	{
		redirect.addFlashAttribute("game", databaseAccess.getVideoGame(id));
		return "redirect:/";
	}
	
	@GetMapping("/deleteGame/{id}")
	public String deleteGame(@PathVariable int id, RedirectAttributes redirect)
	{
		redirect.addFlashAttribute("message", 
			databaseAccess.deleteVideoGame(id));
		return "redirect:/";
	}
	
	@GetMapping("/deleteAll")
	public String deleteAllGames(RedirectAttributes redirect)
	{
		redirect.addFlashAttribute("message", databaseAccess.deleteAllGames());
		return "redirect:/";
	}
}
