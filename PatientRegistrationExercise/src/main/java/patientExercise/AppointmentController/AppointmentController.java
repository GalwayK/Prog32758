package patientExercise.AppointmentController;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppointmentController 
{
	
	@PostMapping("/submitAppointment")
	public void makeAppointment(@RequestParam(required = true) String patientId, 
			@RequestParam(required = true) LocalDate appointmentDate, 
			@RequestParam(required = true) LocalTime earlyTime, 
			@RequestParam(required = true) LocalTime lateTime,
			@RequestParam(defaultValue = "") List<String> accommodation, 
			HttpServletResponse response)
	{
		PrintWriter pagePrinter = null;
		
		try 
		{
			pagePrinter = response.getWriter();
			pagePrinter.println("<html><head><title>Successful Registration</title><body>");
			pagePrinter.printf("Patient ID: %s <br> Appointment Date: %s Between %s and %s", patientId, appointmentDate, earlyTime, lateTime);
			
			if (!accommodation.isEmpty())
			{
				pagePrinter.printf("<h1> Accommodations Required: </h1><ul>");
				for (int i = 0; i < accommodation.size(); i++)
				{
					pagePrinter.printf("<li>%s</li>", accommodation.get(i));
				}
				pagePrinter.printf("</ul>");
				
			}
			
			pagePrinter.println("</body></html>");
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		finally 
		{
			pagePrinter.close();
		}
		
		
		
	}
	

}
