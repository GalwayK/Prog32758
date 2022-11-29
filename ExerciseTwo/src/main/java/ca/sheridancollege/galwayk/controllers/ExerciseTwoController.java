package ca.sheridancollege.galwayk.controllers;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.galwayk.beans.School;

@Controller
public class ExerciseTwoController 
{
	CopyOnWriteArrayList<School> schoolList = new CopyOnWriteArrayList<School>();
	
	@GetMapping("/")
	public String getIndexPage()
	{
		return "index.html";
	}
	
	@GetMapping("/addSchool")
	public String getAddSchoolPage()
	{
		return "addSchool.html";
	}
	
	@PostMapping("/submitSchool")
	public String getSubmitSchool(@RequestParam(required = true) String schoolName, 
			@RequestParam(required = true) String schoolAddress, 
			@RequestParam(required = true) int studentCount)
	{
		schoolList.add(new School(schoolName, schoolAddress, studentCount));
		
		return getIndexPage();
	}
	
	@GetMapping("removeSchools")
	public String getRemoveSchools()
	{
		schoolList.removeAll(schoolList);
		
		return getIndexPage();
	}
	
	@GetMapping("/viewSchools")
	public String getDisplaySchools(Model model)
	{
		model.addAttribute("schools", schoolList);
		return "viewSchools.html";
	}

}
