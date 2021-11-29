package es.uvigo.esei.dgss.teamA.microstories.entities;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity(name = "User")
public class User {
	@Id
    @Column(length = 100, nullable = false)
	private String login;
    @Column(length = 100, nullable = false)
	private String password;
	@OneToMany(mappedBy="author")
	private List<Story> storiesWritten = new ArrayList<Story>();
	public User() {
		
	}
	public User(String login,String password) {
		setLogin(login);
		setPassword(password);
	}
	 public String getLogin() {
		return login;
	}
	 public String getPassword() {
		return password;
	}
	 private void setLogin(String login) {
    	 requireNonNull(login, "login can't be null");
  		this.login=login;
  	}
	 private void setPassword(String password) {
    	 requireNonNull(password, "login can't be null");
  		this.password=password;
  	}
	 public List<Story> getStoriesWritten() {
		return storiesWritten;
	}
	 public void setStoriesWritten(List<Story> storiesWritten) {
		this.storiesWritten = storiesWritten;
	}

	 
		
}
