package beans;

import java.util.HashSet;
import java.util.Set;

public class User {
	
	long id;
	String username;
	String password;
	Set<TrackedObject> entries;
	

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.entries = new HashSet<>();
	}

	public User(String username) {
		super();
		this.username = username;
		this.password = null;
		this.entries = new HashSet<>();
	}
	
	public User() {
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

	public Set<TrackedObject> getEntries() {
		return entries;
	}

	public void setEntries(Set<TrackedObject> entries) {
		this.entries = entries;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", entries=" + entries + "]";
	}
	
	

}
