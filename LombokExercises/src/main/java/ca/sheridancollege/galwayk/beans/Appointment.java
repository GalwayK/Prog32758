package ca.sheridancollege.galwayk.beans;
import java.io.Serializable;
import java.time.LocalTime;

import lombok.*;

@Data
@NoArgsConstructor
public class Appointment implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public Appointment(int studentId, LocalTime appTime, String topic)
	{
		setStudentId(studentId);
		setAppTime(appTime);
		setTopic(topic);
	}
	
	@Getter
	private int studentId = 99000000;
	
	@Getter @Setter
	private LocalTime appTime = LocalTime.of(12, 00);
	
	@Getter @Setter @ToString.Exclude
	private String topic = "";
	
	public void setStudentId(int studentId)
	{
		if (studentId > 0)
		{
			this.studentId = studentId;
		}
		else 
		{
			throw new IllegalArgumentException("Error, student ID must be "
					+ "greater than 0.");
		}
	}

}
