package ca.sheridancollege.galwayk.ExerciseCreateRead.Beans;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre 
{
	private String genre;
	
	public String toString()
	{
		return genre;
	}
}
