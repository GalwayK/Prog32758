package ca.sheridancollege.galwayk.MidtermReview.Beans;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoGame implements Serializable
{
	private int id;
	private String title;
	private String developer;
	private LocalDate releaseDate;
	private String genre;
}
