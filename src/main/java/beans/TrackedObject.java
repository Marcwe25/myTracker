package beans;

public class TrackedObject {

	Long id;
	public String name;
	public Integer value;
	String category;
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public TrackedObject(String name, Integer value) {
		super();
		this.id=-1l;
		this.name = name;
		this.value = value;
		this.category="default";
	}

	public TrackedObject(String name, Integer value, String category) {
		super();
		this.id=-1l;
		this.name = name;
		this.value = value;
		this.category = category;
	}
	
	public TrackedObject(long id, String name, Integer value, String category) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.category = category;
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

	@Override
	public String toString() {
		return "TrackedObject [id=" + id + ", name=" + name + ", value=" + value + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrackedObject other = (TrackedObject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

	public static void main(String[] args) {}


//	public boolean containSuffisantData(){
//		if(date == null) return false;
//		else if(trackObject == null) return false;
//		else return true;
//	}
}
