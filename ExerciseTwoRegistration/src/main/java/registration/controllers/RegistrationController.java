package registration.controllers;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController
{

	
	@GetMapping("register")
	public void getRegisterPage(@RequestParam String firstName,
			@RequestParam String lastName, 
			@RequestParam String age,
			@RequestParam(defaultValue = "no") String rememberMe,
			HttpServletResponse response)
	{
		PrintWriter pagePrinter = null;
		
		String pageOutput = String.format("<html><head><title>Registration "
				+ "Successful</title></head><body><h1 style = "
				+ "\"text-align: center\">"
				+ "Name: %s %s <br> Age: %s <br> Remember Me: %s</body></html>", 
				firstName, lastName, age, rememberMe);
		try 
		{
			pagePrinter = response.getWriter();
			pagePrinter.println(pageOutput);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally 
		{
			pagePrinter.close();
		}
	}
}
