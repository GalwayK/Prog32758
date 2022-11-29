package ca.sheridancollege.galwayk.StudentDatabase.Beans;

import java.io.Serializable;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable
{
	public String firstName;
	public String lastName;
	public String program;
	public int id;
	public int schoolYear;
	public boolean coop;
	public boolean internship;
	
	public String toString()
	{
		return String.format("Student Name: %s %s Program: %s School Year: %d "
				+ "Co op: %b Internship: %b Id: %d", firstName, lastName, program, 
				schoolYear, coop, internship, id);
	}
}
