package ca.sheridancollege.galwayk.Beans;

import lombok.*;

@Data
public class Avenger 
{

	private String name = "";
	private int age =  0;
	private String powerSource = "";
	
	private final String[] powerSources = 
		{"Magic", "Tech", "Deity", "Training"};
	
}
