package beans;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

public class TrackEntry {

	Long id;
	public String name;
	public Integer value;
	public HashMap<Date,Long> dateAndQuantity;
	
	public TrackEntry(String name, Integer value) {
		super();
		this.name = name;
		this.value = value;
		this.dateAndQuantity = new HashMap<Date,Long>();
	}
	
	public TrackEntry(long id, String name, Integer value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.dateAndQuantity = new HashMap<Date,Long>();
	}

	public TrackEntry(long id, String name, Integer value, HashMap<Date, Long> dateAndQuantity) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.dateAndQuantity = dateAndQuantity;
	}

//	public boolean containSuffisantData(){
//		if(date == null) return false;
//		else if(trackObject == null) return false;
//		else return true;
//	}
}
