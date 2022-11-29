package ca.sheridancollege.galwayk.application.repositories;
import ca.sheridancollege.galwayk.application.beans.Animal;
import java.util.List;

public interface PetParent 
{
	public void adoptPets(List<Animal> animals);
	
	public List<Animal> getPetList();
	
	public int numPets();
}
