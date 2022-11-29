package ca.sheridancollege.galwayk.ExerciseDataTemplateBook;

public enum Genres 
{
	ROMANCE("Romance"), SCIENCE_FICTION("Science Fiction"), FANTASY("Fantasy");
	private String genre;
	
	public String getGenre()
	{
		return genre;
	}
	
	private Genres(String genre)
	{
		this.genre = genre;
	}
}
