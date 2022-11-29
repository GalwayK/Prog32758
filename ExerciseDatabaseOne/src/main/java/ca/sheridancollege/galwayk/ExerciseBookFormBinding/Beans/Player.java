package ca.sheridancollege.galwayk.ExerciseBookFormBinding.Beans;
import java.io.Serializable;

import lombok.*;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class Player implements Serializable
{
	private int id; 
	private String firstName;
	private String lastName;
	private int number;
	private String team;
}
