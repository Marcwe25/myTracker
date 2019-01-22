package beans;

import java.time.LocalDateTime;
import java.util.HashMap;

public class TrackEntry {

	long id;
	LocalDateTime date;
	HashMap<String,String> trackObject;
	
	private TrackEntry(){}

	public TrackEntry(LocalDateTime date, HashMap<String, String> trackObject) {
		super();
		this.date = date;
		this.trackObject = trackObject;
	} 

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public HashMap<String, String> getTrackObject() {
		return trackObject;
	}

	public void setTrackObject(HashMap<String, String> trackObject) {
		this.trackObject = trackObject;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public boolean containSuffisantData(){
		if(date == null) return false;
		else if(trackObject == null) return false;
		else return true;
	}
}
