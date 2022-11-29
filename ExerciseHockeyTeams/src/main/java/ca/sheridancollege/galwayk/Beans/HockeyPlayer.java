package ca.sheridancollege.galwayk.Beans;

import java.io.Serializable;

import ca.sheridancollege.galwayk.Teams;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HockeyPlayer implements Serializable
{
	private String firstName;
	private String lastName;
	private int jerseyNumber;
	private int teamNumber;
	
	public String toString()
	{
		return String.format("Name: %s %s Jersey Number: %d Team Name: %s", 
			firstName, lastName, jerseyNumber, 
			Teams.values()[teamNumber].getTeamName());
	}

}
