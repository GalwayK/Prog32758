package ca.sheridancollege.galwayk.ExerciseBookFormBinding.PlayerController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ca.sheridancollege.galwayk.ExerciseBookFormBinding.DatabaseAccess;

@Controller
public class PlayerController 
{
	
	@Autowired
	private DatabaseAccess dataAccess;

	
	@GetMapping("/dbtest")
	public String test() throws Exception
	{
		dataAccess.addPlayer();
		return "index.html";
	}
}
