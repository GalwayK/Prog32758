package ca.sheridancollege.galwayk.StudentDatabase.Beans;

public enum Programs
{

	SDNE("Software Development and Network Engineering"), 
	COMPUTER_PROGRAMMING("Computer Programming"), 
	INFORMATION_SYSTEMS_ENGINEERING("Information Systems Engineering"), 
	COMPUTER_TECHNICIAN("Computer Technician");
	
	private String courseName;
	
	public String getCourseName()
	{
		return this.courseName;
	}
	
	private Programs(String courseName)
	{
		this.courseName = courseName;
	}
}
