package ca.sheridancollege.galwayk.application.repositories;

import ca.sheridancollege.galwayk.application.beans.Animal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Component;

@Component
public class Client implements PetParent
{
	List<Animal> pets = new CopyOnWriteArrayList<Animal>();
	
	private String clientName = null;
	
	public void setClientName(String clientName)
	{
		if (this.clientName == null)
		{
			this.clientName = clientName;
		}
	}
	
	public String getClientName()
	{
		return clientName;
	}
	
	@Override
	public void adoptPets(List<Animal> animals) {
		pets.addAll(animals);
	}

	@Override
	public List<Animal> getPetList() {
		return pets;
	}

	@Override
	public int numPets() {
		return pets.size();
	}
	
}
