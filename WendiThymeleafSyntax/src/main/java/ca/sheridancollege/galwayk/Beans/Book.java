package ca.sheridancollege.galwayk.Beans;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.*;

@Component
@NoArgsConstructor
@EqualsAndHashCode
public class Book implements Serializable
{

	private static final long serialVersionUID = -3277961582654604039L;
	@Getter
	private long isbn = 0;
	@Getter @NonNull
    private String title = "TBD";
	@Getter @Setter @NonNull
    private String author;
	@Getter
    private double price;
	@Getter @Setter
    private int genre = 1;
	
	public Book(long isbn, String title, String author, double price, int genre)
	{
		setIsbn(isbn);
		setTitle(title);
		setAuthor(author);
		setPrice(price);
		setGenre(genre);
	}
    
    public void setIsbn(long isbn)
    {
        if (isbn < 0)
        {
            throw new IllegalArgumentException("Error, ISBN cannot be less than 0.");
        }
        this.isbn = isbn;
    }
    
    
    public void setTitle(String title)
    {
        if (title == null || title.trim().isEmpty())
        {
            throw new IllegalArgumentException("Error, title cannot be empty.");
        }
        this.title = title;
    }
    
    public void setPrice(double price)
    {
        if (price < 0)
        {
            throw new IllegalArgumentException("Error, price cannot be less than 0.");
        }
        this.price = price;
    }
    
    public String toString()
    {
        return String.format("%d %s by %s", isbn, title, author);
    }
}
