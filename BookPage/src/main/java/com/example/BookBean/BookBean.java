package com.example.BookBean;

import java.io.Serializable;

public class BookBean implements Serializable
{

	private static final long serialVersionUID = -3277961582654604039L;
	
	private long isbn = 0;
    private String title = "TBD";
    private String author;
    private double price;
    private int genre = 1;
    
    public BookBean()
    {
        
    }
    
    public long getIsbn()
    {
        return isbn;
    }
    
    public void setIsbn(long isbn)
    {
        if (isbn < 0)
        {
            throw new IllegalArgumentException("Error, ISBN cannot be less than 0.");
        }
        this.isbn = isbn;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        if (title == null || title.trim().isEmpty())
        {
            throw new IllegalArgumentException("Error, title cannot be empty.");
        }
        this.title = title;
    }
    
    public String getAuthor()
    {
        return author;
    }
    
    public void setAuthor(String author)
    {
        this.author = author;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public void setPrice(double price)
    {
        if (price < 0)
        {
            throw new IllegalArgumentException("Error, price cannot be less than 0.");
        }
        this.price = price;
    }
    
    public int getGenre()
    {
        return genre;
    }
    
    public void setGenre(int genre)
    {
        this.genre = genre;
    }
    
    public String toString()
    {
        return String.format("%d %s by %s", isbn, title, author);
    }
    
    public static void main(String[] args) 
    {
        BookBean nemesisGames = new BookBean();
        nemesisGames.setAuthor("James Corey");
        nemesisGames.setIsbn(1234567891234L);
        nemesisGames.setTitle("Nemesis Games");
        System.out.println(nemesisGames);
    }
    
}
