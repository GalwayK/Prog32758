package ca.sheridancollege.galwayk.ExerciseFive;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TestController
{
	private MockMvc mock;
	
	@Autowired
	private void setMock(MockMvc mock)
	{
		this.mock= mock;
	}
	
	@Test
	void testUserNotLoggedIn() throws Exception
	{
		mock.perform(get("/user"))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("http://localhost/login"));
	}
	
	@Test
	@WithMockUser(username = "testuser", roles = {"USER"})
	public void testUserLoggedInAndAuthorized() throws Exception
	{
		mock.perform(get("/user"))
			.andExpect(status().isOk())
			.andExpect(view().name("secured/user/index.html"));
	}
	
	@Test
	@WithMockUser(username = "testUser", roles = {"USER"})
	public void testUserLoggedInNotAuthorized() throws Exception 
	{
		mock.perform(get("/manager"))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/denied"));
	}

}
