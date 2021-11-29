package es.uvigo.esei.dgss.teamA.microstories.entities;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	private String login;
	private String password;
	
	@Before
	public void setUp() throws Exception {
		this.login = "TestLogin";
		this.password = "TestPassword";
	}
	@Test
	public void testUser() {
			final User user = new User(login,password);
			
			assertThat(user.getLogin(), is(equalTo(login)));
			assertThat(user.getPassword(), is(equalTo(password)));

	}
	@Test(expected = NullPointerException.class)
	public void testUserLoginNull() {
		new User(null,password);
	}	
	@Test(expected = NullPointerException.class)
	public void testUserPasswordNull() {
		new User(login,null);
	}
}
