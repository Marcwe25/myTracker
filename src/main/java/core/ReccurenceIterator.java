package core;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.time.temporal.ValueRange;
import java.util.TreeSet;

public class ReccurenceIterator {
	
	private RecurrenceCalculator recurrenceCalculator;
	private LocalDate nextRecurrence;
	
	public ReccurenceIterator(RecurrenceCalculator recurrenceCalculator) {
		this.recurrenceCalculator = recurrenceCalculator;
	}
	
	public LocalDate next(){
		while(nextRecurrence.isBefore(LocalDate.now())){
			nextRecurrence = recurrenceCalculator.nextRecurrence();			
		}
		return nextRecurrence;
	}

	
	private interface RecurrenceCalculator {
		LocalDate nextRecurrence();
	}
	
	private class RepetitiveCalculator implements RecurrenceCalculator{

		Duration duration;

		private  RepetitiveCalculator(Duration duration, LocalDate lastEntryDate) {
			nextRecurrence = lastEntryDate;
			this.duration = duration;
		}
		
		@Override
		public LocalDate nextRecurrence() {
			while(nextRecurrence.isBefore(LocalDate.now())){
				nextRecurrence = nextRecurrence.plus(duration);
			}
			return nextRecurrence;
		}
	}
	
	private class DaysOfWeekCalculator implements RecurrenceCalculator{

		DayOfWeek[] days;
		
		private DaysOfWeekCalculator(DayOfWeek[] days) {
			this.days=days;
		}
		
		@Override
		public LocalDate nextRecurrence() {
			TreeSet<LocalDate> dates = new TreeSet<>();
			for(DayOfWeek day : days){
				dates.add(nextRecurrence.with(TemporalAdjusters.nextOrSame(day)));
			}
			return dates.first();
		}
		
	}
	
	private class DaysOfMonthCalculator implements RecurrenceCalculator{
		
		TreeSet<LocalDate> dates;
		
		private DaysOfMonthCalculator(int[] days){
			buildTreeOfDays(days);
		}
		
		public void buildTreeOfDays(int[] days){
			this.dates = new TreeSet<>();
			for(Integer day : days){
				if(day>0 && nextRecurrence.lengthOfMonth()>=day){
				dates.add(LocalDate.of(nextRecurrence.getYear(), nextRecurrence.getMonth(), day));
				}
			}
		}
		
		@Override
		public LocalDate nextRecurrence() {
			nextRecurrence = LocalDate.now();
			
			LocalDate next = dates.ceiling(nextRecurrence);
			
			if(next == null){
				int lower = dates.first().getDayOfMonth();
				next = nextRecurrence;
				do{next = next.with(TemporalAdjusters.firstDayOfNextMonth());
				} while (lower>next.lengthOfMonth());
				next = next.withDayOfMonth(lower);
			}
			return next;
		}
		
	}
	
	public static class RecurrrenceIteratorFactory{

		public static ReccurenceIterator getReccurenceIterator(String str, LocalDate lastEntryDate){
			ReccurenceIterator reccurenceIterator = new ReccurenceIterator(null);;
			String[] arr = str.split("-");
			String[] pr = arr[1].split(",");
			switch (arr[0]) {
			case "1":
				reccurenceIterator.recurrenceCalculator = reccurenceIterator.new RepetitiveCalculator(Duration.of(Long.parseLong(pr[0]), ChronoUnit.valueOf(pr[1])), lastEntryDate);
				break;
			case "2":
				DayOfWeek[] daysOfWeek = new DayOfWeek[pr.length];
				for(int i = 0 ; i < pr.length ; i++){
					daysOfWeek[i] = DayOfWeek.valueOf(pr[i]);
				}
				reccurenceIterator.recurrenceCalculator = reccurenceIterator.new DaysOfWeekCalculator(daysOfWeek);
				break;
			case "3":
				int [] daysOfMonth = new int[pr.length];
				for (int i = 0; i < pr.length; i++) {
					daysOfMonth[i]=Integer.parseInt(pr[i]);
				}
				reccurenceIterator.recurrenceCalculator = reccurenceIterator.new DaysOfMonthCalculator(daysOfMonth);
				break;

			default:
				break;
			}
			if(reccurenceIterator.recurrenceCalculator == null) return null;
			return reccurenceIterator;
		}
	}
	
	public static void main(String[] args) {
		
		LocalDate date = LocalDate.now();
		while(27>date.lengthOfMonth()){
			date = date.with(TemporalAdjusters.firstDayOfNextMonth());
		}
		date = date.withDayOfMonth(27);
		System.out.println(date);

	}

}
