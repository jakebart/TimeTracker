import java.text.*;
import java.time.*;
import java.util.*;


public class TimeStats extends DateAndTime {
	
	public TimeStats() {	
		this("/Users/j4k3/eclipse-workspace/TrackTime/src/Dates.csv");
	}
	private TimeStats(String file) {
		super(file);
	}

	protected ArrayList<String> startTimes = new ArrayList<String>();
	protected ArrayList<String> endTimes = new ArrayList<String>();
	
	
	public void ParseTime() {
		String allTimeString;
		allTimeString = readTime();
		
		for (String time:allTimeString.split("\n")) {
			String fullTime = time;
			if (fullTime.contains("start")) {
				fullTime = fullTime.replaceAll("start, ", "");
				fullTime = fullTime.substring(10, 19);
				startTimes.add(fullTime);
			} else if (fullTime.contains("end")) {
				fullTime = fullTime.replaceAll("end, ", "");
				fullTime = fullTime.substring(11, 19);
				endTimes.add(fullTime);
			}
		}		
	}	
	
	public String difference(String timeStart, String timeEnd) {
		return this.formatTime(DifferenceHelper(timeStart, timeEnd));
	}
	
	public String averageTime(String timeStart, int totalSessions) {
		long total = 0;
		for (int i = 0; i < totalSessions; i++) {
			String startIndexString = startTimes.get(startTimes.indexOf(timeStart)+i);
			String endIndexString = endTimes.get(startTimes.indexOf(timeStart)+i);
			total += DifferenceHelper(startIndexString,endIndexString);
		}
		return this.formatTime(total/totalSessions);
	}
	
	public String allTimeAverage() {
		return averageTime(startTimes.get(0), (endTimes.size()-1));
	}
	private long DifferenceHelper(String timeStart, String timeEnd) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		try {
			Date start = format.parse(timeStart);
			Date end = format.parse(timeEnd);
			return (end.getTime()-start.getTime())/1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	private String formatTime(long seconds) {
		int hour, minute, second;
		hour = (int) (seconds/3600);
		minute = (int) (seconds%3600)/60;
		second = (int) seconds%60;
		
		return String.format("%02d:%02d:%02d", hour, minute, second);	
	}

	public static void main(String[] args) {
		TimeStats statsTest = new TimeStats();
		statsTest.ParseTime();
		System.out.println(statsTest.difference(statsTest.startTimes.get(1), statsTest.startTimes.get(2)));
		System.out.println(statsTest.averageTime(statsTest.startTimes.get(1), 2));
		System.out.println(statsTest.allTimeAverage());
	}

}
