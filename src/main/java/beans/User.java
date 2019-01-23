package beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class User {
	
	long id;
	String username;
	String password;
	HashSet<String> trackedEntries;
	List<TrackEntry> entries;
	
	private User() {
		super();
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.trackedEntries = new HashSet<>();
		this.entries = new ArrayList<>();
	}
	
	public HashSet<String> getTrackedEntries() {
		return trackedEntries;
	}

	public void setTrackedEntries(HashSet<String> trackedEntries) {
		this.trackedEntries = trackedEntries;
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

	public List<TrackEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<TrackEntry> entries) {
		this.entries = entries;
	}
	
	

}
