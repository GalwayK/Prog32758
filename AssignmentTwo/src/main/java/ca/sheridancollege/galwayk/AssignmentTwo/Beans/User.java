package ca.sheridancollege.galwayk.AssignmentTwo.Beans;

import java.io.Serializable;
import java.time.*;
import lombok.*;

@Data
@NoArgsConstructor
public class User implements Serializable
{
	private int id;
	private String firstName;
	private String lastName;
	private String role;
	private LocalDateTime creationDateTime;
	private LocalDateTime updateDateTime;
	
	public User(String firstName, String lastName, String role)
	{
		setFirstName(firstName);
		setLastName(lastName);
		setRole(role);
	}

	public String toString()
	{
		return String.format("First Name: %s Last Name: %s Role: %s "
				+ "Creation Date: %s Update Date: %s Id: %d", 
			firstName, lastName, role, creationDateTime, updateDateTime, id);
	}
	
}
