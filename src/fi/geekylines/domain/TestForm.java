package fi.geekylines.domain;

import javax.annotation.Nonnull;

public class TestForm {

	
	private String username;
	
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		String result = "The username is" + username + " and the password is " + password;
		return result;
	}
	
}
