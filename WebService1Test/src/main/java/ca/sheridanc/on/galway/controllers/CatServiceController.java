package ca.sheridanc.on.galway.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//Controller for rest requests. 
@RestController
//General request for the entire controller for "/cats"
@RequestMapping("/cats")
public class CatServiceController 
{
	private final String[] names = {"Muffin", "Fluffy", "Dumpling", "Spot",
	        "Whiskers", "Shadow", "Felix", "Tigger", "Smudge", "Simba", "Misty",
	        "Sasha", "Milo", "Luna", "Molly", "Tom", "Morris", "Fritz", "Coco",
	        "Cleo", "Puss", "Smokey", "Oscar", "Ginger", "Tibby", "Charlie", "Pedro",
	        "Daisy", "Jasper", "Sooty", "Alfie", "Millie", "Bella", "Patches",
	        "Pumpkin", "Maggie", "Oreo", "Sam", "Max", "Oliver", "Minka", "Maru",
	        "Moritz", "Eva", "Lucy", "Sisko", "Lulu"};

	    private final String[] breeds = {"Grey Tabby", "Void Kitty", "Orange Tabby",
	        "Russian Blue", "Tawny Tabby", "Snowshoe", "Siamese", "Persian",
	        "Manx", "British Shorthair", "Maine Coon", "American Shorthair",
	        "Ragdoll", "Bengal", "White Domestic Shorthair", "Calico", "Abyssinian",
	        "Scottish Fold", "Birman", "Burmese", "Japanese Bobtail", "Bombay",
	        "Egyptian Mau", "Balinese", "Munchkin", "Savannah", "Ocicat",
	        "Cornish Rex", "Chartreux"};

	    private final String[] locns = {"on your shoe", "in your shoe", "in front of you",
	        "in your bed", "on the folded laundry", "in your lap", "on the bath mat",
	        "under your desk", "on your keyboard", "on your snack",
	        "in your cup", "on your mouse", "on your desk", "under the table",
	        "by the front door", "in front of your bedroom door", "in your closet",
	        "on your favourite jeans", "in your sock drawer", "on your homework",
	        "in the litter box", "in their dish", "on the TV remote", "on your chair",
	        "in your slipper", "on the floor", "on your pillow", "in the bath tub",
	        "on your open textbook", "in your hand"};
	    
	    @GetMapping("/uniqueCat")
	    public String cat()
	    {
	    	return String.format("Name: %s Breed: %s", 
	    		names[(int)Math.random() * names.length], 
	    		breeds[(int)Math.random() * breeds.length]);
	    }
	    
	    @GetMapping("/catpuke")
	    public String getCatPuke()
	    {
	    	return String.format("%s the %s just puked %s!", 
	    		names[(int) (Math.random() * names.length)],
	    		breeds[(int) (Math.random() * breeds.length)], 
	    		locns[(int) (Math.random() * locns.length)]);
	    }
	    
}
