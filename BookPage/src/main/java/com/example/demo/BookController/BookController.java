package com.example.demo.BookController;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.BookBean.BookBean;


@Controller
public class BookController 
{
	
	@GetMapping("/formSubmit")
	public void getBookPage(HttpServletResponse response, @RequestParam(required = true) String inputTitle, 
			@RequestParam(required = true) String inputAuthor, 
			@RequestParam(defaultValue = "1") int inputGenre,
			@RequestParam(required = true) Long inputISBN, 
			@RequestParam(required = true, name = "inputPrice") 
			double inputPrice, @RequestParam(required = true) int inputCopies)
	{
		
		System.out.printf("Your order: %d copies of %s by %s priced %.2f. Genre"
				+ ": %d", inputCopies, inputTitle, inputAuthor, inputPrice, 
				inputGenre);
		
		BookBean bookOrder = new BookBean();
		bookOrder.setAuthor(inputAuthor);
		bookOrder.setGenre(inputGenre);
		bookOrder.setIsbn(inputISBN);
		bookOrder.setPrice(inputPrice);
		bookOrder.setTitle(inputTitle);
		double orderPrice = bookOrder.getPrice() * inputCopies;
		
		
		String strBookOrder = String.format("Your order: %s priced %.2f", 
				bookOrder.toString(), orderPrice);
		
		PrintWriter pagePrinter = null;
		
		try 
		{
			pagePrinter = response.getWriter();
			
			pagePrinter.println(strBookOrder);
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
