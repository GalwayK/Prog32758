package ca.sheridancollege.galwayk.StudentDatabase.StudentController;

import ca.sheridancollege.galwayk.StudentDatabase.Beans.Programs;
import ca.sheridancollege.galwayk.StudentDatabase.Beans.Student;
import ca.sheridancollege.galwayk.StudentDatabase.Database.DataAccess;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StudentController
{
	private DataAccess dataAccess;
	public StudentController(DataAccess dataAccess)
	{
		this.dataAccess = dataAccess;
	}
	
	@GetMapping("/")
	public String goIndex(Model model)
	{
		return "index.html";
	}
	
	@GetMapping("/viewStudents")
	public String goList(Model model)
	{
		model.addAttribute("studentList", dataAccess.getStudentList());
		return "students.html";
	}
	
	@GetMapping("/addStudent")
	public String goAdd(Model model)
	{
		if (model.getAttribute("student") == null)
		{
			model.addAttribute("student", new Student());
		}
		model.addAttribute("programList", Programs.values());
		return "add.html";
	}
	
	@PostMapping("/updateStudent")
	public String updateStudent(@ModelAttribute Student student, 
			RedirectAttributes redirect)
	{
		System.out.println(student);
		redirect.addFlashAttribute("message", 
				dataAccess.addStudent(student));
		return "redirect:/";
	}
	
	@GetMapping("/details/{id}")
	public String goDetails(@PathVariable int id, RedirectAttributes redirect)
	{
		redirect.addFlashAttribute("student", dataAccess.getStudent(id));
		return "redirect:/view";
	}
	
	@GetMapping("/view")
	public String goView(Model model)
	{
		return "view.html";
	}

	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable int id, 
			RedirectAttributes redirect)
	{
		redirect.addFlashAttribute("message", dataAccess.deleteStudent(id));
		return "redirect:/";
	}
	
	@GetMapping("/editStudent/{id}")
	public String goEdit(@PathVariable int id, 
		RedirectAttributes redirect)
	{
		redirect.addFlashAttribute("student", dataAccess.getStudent(id));
		return "redirect:/addStudent";
	}
	
	@GetMapping("/deleteStudents")
	public String deleteStudents(RedirectAttributes redirect)
	{
		
		redirect.addFlashAttribute("message", dataAccess.deleteAll());
		return "redirect:/";
	}
	
}
