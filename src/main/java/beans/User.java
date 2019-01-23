package beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
	
	long id;
	String username;
	String password;
	Set<TrackEntry> entries;
	
	private User() {
		super();
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.entries = new HashSet<>();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public Set<TrackEntry> getEntries() {
		return entries;
	}

	public void setEntries(Set<TrackEntry> entries) {
		this.entries = entries;
	}
	
	

}
