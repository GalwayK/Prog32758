package ca.sheridanc.galwayk.ControllerTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import ca.sheridanc.galwayk.Beans.Message;
import ca.sheridanc.galwayk.Database.DataAccess;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
class TestMessageController 
{
	@Autowired private MockMvc mock;
	@Autowired DataAccess dataAccess;
	
	@Test
	@WithMockUser(username = "TestUser", roles = {"READER"})
	void testLoadingIndexPagePass() throws Exception
	{
		mock.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("index.html"));
	}
	
	@Test
	@WithMockUser(username = "TestUser", roles = {"READER"})
	void testLoadingMessageBoardPass() throws Exception 
	{
		mock.perform(get("/messageBoard"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("messageList"))
		.andExpect(view().name("/messageBoard/messageBoard.html"));
	}
	
	@Test 
	void testNotAuthorized() throws Exception 
	{
		mock.perform(get("/messageBoard"))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("http://localhost/login"));
	}
	
	@Test
	@WithMockUser(username = "FakeUser", roles = {})
	void testLoadingMessageBoardFail() throws Exception
	{
		mock.perform(get("/messageBoard"))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/denied"));
	}
	
	@Test
	@WithMockUser(username = "FakeUser", roles = {"WRITER", "READER"})
	void testInsertMessage() throws Exception
	{
		Message message = new Message(0, "Woah.");
		int firstSize = dataAccess.getMessageList().size();
		mock.perform(post("/submitMessage").flashAttr("message", message))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/messageBoard"));
		int secondSize = dataAccess.getMessageList().size();
		
		Assertions.assertTrue(firstSize == secondSize - 1);
	}
	
	@Test
	@WithMockUser(username = "name", roles = {"WRITER", "READER"})
	void testDisplayStatusMessage() throws Exception
	{
		mock.perform(get("/login").sessionAttr("status", "Message"))
		.andExpect(model().attributeExists("status"))
		.andExpect(view().name("login.html"));
	}
	
	@Test 
	@WithMockUser(username = "name", roles = {"WRITER", "READER"})
	void testEditFlagSet() throws Exception 
	{
		dataAccess.insertMessage(new Message(0, "Test Message"));
		mock.perform(get("/editMessage/{id}", 1))
			.andExpect(redirectedUrl("/messagePost"));
	}
}
