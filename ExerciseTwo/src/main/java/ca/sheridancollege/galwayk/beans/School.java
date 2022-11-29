package ca.sheridancollege.galwayk.beans;

import java.io.Serializable;
import lombok.*;

@EqualsAndHashCode
public class School implements Serializable
{
	@Getter
	private String strAddress;
	@Getter
	private String strName;
	@Getter
	private int intStudentCount;
	
	public School(String strAddress, String strName, int intStudentCount)
	{
		setStrName(strName);
		setIntStudentCount(intStudentCount);
		setStrAddress(strAddress);
	}
	
	public String toString()
	{
		return String.format("%s in %s has %d students.", 
				strAddress, strName, intStudentCount);
	}

	public void setStrAddress(String strAddress)
	{
		if (strAddress == null || strAddress.isEmpty())
		{
			throw new IllegalArgumentException(
					"Error, school must have an address.");
		}
		this.strAddress = strAddress;
	}
	
	public void setStrName(String strName)
	{
		if (strName == null || strName.isEmpty())
		{
			throw new IllegalArgumentException(
					"Error, school must have a name.");
		}
		this.strName = strName;
	}
	
	public void setIntStudentCount(int intStudentCount)
	{
		if (intStudentCount < 0)
		{
			throw new IllegalArgumentException(
					"Error, student count cannot be lower than 0.");
		}
		this.intStudentCount = intStudentCount;
	}

	
}
