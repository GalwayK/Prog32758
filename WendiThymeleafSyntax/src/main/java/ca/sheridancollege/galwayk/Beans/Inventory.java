package ca.sheridancollege.galwayk.Beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Inventory 
{
	private String itemName;
	private int itemQuantity;
	private String itemId;
}
