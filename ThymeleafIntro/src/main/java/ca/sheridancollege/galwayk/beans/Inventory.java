package ca.sheridancollege.galwayk.beans;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.*;

@Data
@Component
@NoArgsConstructor
public class Inventory implements Serializable
{
	
	public Inventory(long itemId, String itemName, int itemQuantity)
	{
		setItemQuantity(itemQuantity);
		setItemId(itemId);
		this.itemName = itemName;
	}

	private static final long serialVersionUID = -6038406107884656852L;
	private long itemId;
	
	@NonNull
	private String itemName;
	
	private int itemQuantity;
	
	public void setItemQuantity(int itemQuantity)
	{
		if (itemQuantity >= 0)
		{
			this.itemQuantity = itemQuantity;
		}
		else 
		{
			throw new IllegalArgumentException("Error, itemQuantity must be "
					+ "equal to or greater than 0");
		}
	}
	
	public void setItemId(long itemId)
	{
		if (itemId > 0)
		{
			this.itemId = itemId;
		}
		else 
		{
			throw new IllegalArgumentException("Error, itemId must be greater "
					+ "than 0");
		}
	}
	
	public static void main(String[] args)
	{
		Inventory testInv = new Inventory(12345678912L, "Test Item", 1234);
		System.out.println(testInv);
	}
}



