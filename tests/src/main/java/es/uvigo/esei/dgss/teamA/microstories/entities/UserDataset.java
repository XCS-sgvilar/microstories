package es.uvigo.esei.dgss.teamA.microstories.entities;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;


import org.junit.runner.RunWith;


public class UserDataset {
	  public static final String EXISTENT_Login = "Victor";
	  public static final String EXISTENT_Password = "VictorPasswd";
	  
	  public static User[] users(String... logins) {
	        final Set<String> loginSet = stream(logins).collect(toSet());

	        return stream(users()).filter(stories -> loginSet.contains(stories.getLogin())).toArray(User[]::new);
	        
	    }
	    


public static User[] users() {
        return new User[]{
        		new User("JK Rowling","JK RowlingPasswd"),
        		new User("Scott Aaronson" ,"Scott AaronsonPasswd"),
        		new User("Victor" ,"VictorPasswd"),
        		new User("Brandon Sanderson" ,"Brandon SandersonPasswd"),
        		new User("Santiago" ,"SantiagoPasswd"),
        		new User("Ines" ,"InesPasswd"),
        		new User("Bruno" ,"BrunoPasswd"),
        		new User("Yudkowsky" ,"I am a potato 927"),
        		new User("Greg Egan" ,"Greg EganPsswd")
        };

     
        }  
	 public static User userWithLogin(String login) {
	        return stream(users()).filter(user -> user.getLogin() == login)
	                .findFirst()
	                .orElseThrow(IllegalArgumentException::new);
	    }
}