package beans;

public class TrackedObject {

	Long id;
	public String name;
	public Integer value;
	
	public TrackedObject(String name, Integer value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public TrackedObject(long id, String name, Integer value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}


//	public boolean containSuffisantData(){
//		if(date == null) return false;
//		else if(trackObject == null) return false;
//		else return true;
//	}
}
