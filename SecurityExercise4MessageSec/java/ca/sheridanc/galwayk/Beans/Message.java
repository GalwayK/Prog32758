package ca.sheridanc.galwayk.Beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int messageId;
	private String message;
	
	public String toString()
	{
		return String.format("MessageID: $d Message: %s", messageId, message);
	}

}
