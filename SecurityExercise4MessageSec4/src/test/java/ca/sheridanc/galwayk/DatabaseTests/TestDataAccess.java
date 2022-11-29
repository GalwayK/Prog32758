package ca.sheridanc.galwayk.DatabaseTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import ca.sheridanc.galwayk.Beans.Message;
import ca.sheridanc.galwayk.Database.DataAccess;

@SpringBootTest
@AutoConfigureTestDatabase
class TestDataAccess 
{
	@Autowired
	private DataAccess dataAccess;
	
	@Test
	void testInsertPass() 
	{
		int firstSize = dataAccess.getMessageList().size();
		dataAccess.insertMessage(new Message(0, "Test"));
		int secondSize = dataAccess.getMessageList().size();
		Assertions.assertTrue(firstSize == secondSize - 1);
	}
	
	@Test
	void testInsertFail()
	{
		int firstSize = dataAccess.getMessageList().size();
		
			assertThrows(NullPointerException.class, () -> dataAccess.insertMessage(null));
		int secondSize = dataAccess.getMessageList().size();
		Assertions.assertTrue(firstSize == secondSize);
	}

}
