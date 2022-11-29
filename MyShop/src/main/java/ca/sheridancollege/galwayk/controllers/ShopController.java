package ca.sheridancollege.galwayk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController 
{

	@GetMapping("/shop.html")
	public String goShopPage()
	{
		return "shop.html";
	}
	
	@GetMapping("/contact.html")
	public String getContactPage()
	{
		return "contact.html";
	}
	
	@GetMapping("/faq.html")
	public String getFaqPage()
	{
		return "faq.html";
	}
	
}
