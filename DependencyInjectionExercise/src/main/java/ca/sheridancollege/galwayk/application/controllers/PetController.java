package ca.sheridancollege.galwayk.application.controllers;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ca.sheridancollege.galwayk.application.beans.Animal;
import ca.sheridancollege.galwayk.application.repositories.Client;
import ca.sheridancollege.galwayk.application.repositories.PetParent;

@Controller
public class PetController 
{	
	//@Autowired
	private PetParent petParent;
	
	private Client client;
//	
//	@Autowired
//	public void setPetParent(PetParent petParent)
//	{
//		this.petParent = petParent;
//	}
	
	public PetController (PetParent petParent, Client client)
	{
		super();
		this.petParent = petParent;
		this.client = client;
	}
	
	@GetMapping("/index")
	public String getIndex()
	{
		return "index.html";
	}
	
	@GetMapping("/adopt")
	public void getAdopt(HttpServletResponse response, 
			@RequestParam String petName, 
			@RequestParam String adopterName, 
			@RequestParam String type,
			@RequestParam String breed)
	{
		
		Animal newAnimal = new Animal(petName, type, breed);
		client.setClientName(adopterName);
		client.getPetList().add(newAnimal);
		
		
		//petParent.getPetList().add(newAnimal);
		
		
		PrintWriter printPage = null;
		
		try 
		{
			printPage = response.getWriter();
			printPage.printf("<html><head><title>Pet Response</title></head>"
					+ "<body>Printing %s's Pets<br>", client.getClientName());
			
			for (int i = 0; i < client.getPetList().size(); i++)
			{
				printPage.printf("<p>%s</p>", 
					client.getPetList().get(i));
			}
			
//			for (int i = 0; i < petParent.getPetList().size(); i++)
//			{
//				printPage.printf("%s", 
//					petParent.getPetList().get(i));
//			}
			
			printPage.println("</body></html>");
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			printPage.close();
		}
	}
}
