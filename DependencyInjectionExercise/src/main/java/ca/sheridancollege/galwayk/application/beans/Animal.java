package ca.sheridancollege.galwayk.application.beans;
import java.io.Serializable;

public class Animal implements Serializable
{

	private static final long serialVersionUID = -5118322037618093015L;
	private String name;
	private String type;
	private String breed;
	
	public Animal(String name, String type, String breed)
	{
		this.name = name;
		this.type = type;
		this.breed = breed;
	}
	
	public Animal()
	{
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	public String toString()
	{
		return String.format("Name: %s Type: %s Breed: %s", name, type, breed);
	}
}
