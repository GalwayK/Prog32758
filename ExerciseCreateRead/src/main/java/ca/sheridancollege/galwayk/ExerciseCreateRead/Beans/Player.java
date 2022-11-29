package ca.sheridancollege.galwayk.ExerciseCreateRead.Beans;

import org.springframework.data.annotation.Id;

import lombok.*;

@NoArgsConstructor
@Data
public class Player 
{
	public Player(String firstName, String lastName, String team, int number)
	{
		setFirstName(firstName);
		setLastName(lastName);
		setTeam(team);
		setNumber(number);
	}
	
	public String toString()
	{
		return String.format("Player Name: %s %s Team: %s Number %d", 
			firstName, lastName, team, number);
	}
	
	private String firstName;
	private String lastName; 
	private String team;
	private int number;
	
	@Id
	private int id;
	
	

}
