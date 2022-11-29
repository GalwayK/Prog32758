package ca.sheridancollege.galwayk.Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestController 
{

	@GetMapping("/formSubmit")
	public void getFormData(HttpServletResponse responseObject, @RequestParam(name = "inputName") String name, 
			@RequestParam(name = "inputQuantity") int quantity, 
			@RequestParam(name = "inputPrice") double price, 
			@RequestParam(name = "inputNewsletter", required = false, defaultValue = "false") boolean newsletter, 
			@RequestParam (name = "inputDate") /*@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)*/ LocalDate date,
			@RequestParam(name = "inputTime") /*@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)*/ LocalTime time)
	{
		
		PrintWriter pagePrinter = null;
		
		try
		{
			pagePrinter = responseObject.getWriter();
			
			String outputString = String.format("<html><head></head><body>"
					+ "You ordered %d orders of %s worth %.2f to be delivered on %s at %s. Add to newsletter: %b"
					+ "</body></html>", quantity, name, price, date.toString(), time.toString(), newsletter);
			pagePrinter.println(outputString);
		}
		catch (IOException iOException)
		{
			System.out.println(iOException.getStackTrace());
		}
		finally
		{
			pagePrinter.close();
		}
	}
	
}
