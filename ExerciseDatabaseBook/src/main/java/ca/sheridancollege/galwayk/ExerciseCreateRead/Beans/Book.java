package ca.sheridancollege.galwayk.ExerciseCreateRead.Beans;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book 
{
	private String title;
	private int isbn;
	private String author;
	private double price;
	private String genre;
	
	public String toString()
	{
		return String.format("%s genre: %s by %s priced %.2f, Isbn: %d", 
			title, genre, author, price, isbn);
	}
}
