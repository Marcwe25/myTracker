package beans;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.TreeSet;

public class TrackedObject {

	Long id;
	public String name;
	public Integer value;
	String category;
	int target;
	/**
	 * recurrence is express as the following:
	 * recurrence[0] first char is the type (3 types)
	 * type 1 = simple repetitive ( each 2 days, each 3 weeks, always from last entry)
	 * type 2 = days of the week enumeration
	 * type 3 = days of the month enumeration
	 */
	LocalDate nextDay;
	
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
	
	public LocalDate next(java.time.LocalDate current){
		
		return current;
	}

	public LocalDate next(java.time.LocalDate current, java.time.Duration duration){
		return current.plus(duration);
	}

	public LocalDate next(java.time.LocalDate current, java.time.DayOfWeek[] days){
		TreeSet<LocalDate> dates = new TreeSet<>();
		for(DayOfWeek day : days){
			dates.add(current.with(TemporalAdjusters.nextOrSame(day)));
		}
		return dates.first();
	}

	public LocalDate next(java.time.LocalDate current, int[] days){
		TreeSet<LocalDate> dates = new TreeSet<>();
		for(Integer day : days){
			if(day>0 && current.getMonth().length(current.isLeapYear())>=day){
			dates.add(LocalDate.of(current.getYear(), current.getMonth(), day));
			}
		}
		LocalDate next = dates.ceiling(current);
		if(next == null){
			int lower = dates.first().getDayOfMonth();
			next = current.with(TemporalAdjusters.firstDayOfNextMonth());
			next = next.withDayOfMonth(lower);
		}
		return next;
	}
	
	
	

	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2012, 12, 6);
		TrackedObject tr = new TrackedObject("", 4);
		int[] i = new int[3];
		i[0] = 1;
		i[1] = 7;
		i[2] = 5;
		System.out.println(tr.next(date, i));
	}


//	public boolean containSuffisantData(){
//		if(date == null) return false;
//		else if(trackObject == null) return false;
//		else return true;
//	}
}
