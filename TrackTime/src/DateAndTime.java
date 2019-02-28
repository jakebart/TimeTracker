import java.io.*;
import java.util.*;

public class DateAndTime {

	private String fileName = "";
	public DateAndTime(String file) {
		this.fileName = file;
	}
	public void writeTime(String Date) {
		try {
			PrintWriter record = new PrintWriter(new FileWriter(this.fileName, true));
			record.write(Date + "\n");
			record.close();
		} catch (Exception e) {
			System.err.print("File: " + this.fileName + " could not be opened!");
			e.printStackTrace();
		}
	}
	public String readTime() {
		String time = "";
		try {
			Scanner timeFile = new Scanner(new File(this.fileName));
			while(timeFile.hasNextLine()) {
				time += timeFile.nextLine() + "\n";
			}
			timeFile.close();
		} catch (FileNotFoundException e) {
			System.err.print(this.fileName + " could not be read!");
			e.printStackTrace();
		}
		return time;
	}
	public void resetData() {
		try {
			PrintWriter record = new PrintWriter(this.fileName);
			record.write("");
			record.close();
		} catch (Exception e) {
			System.err.print("File: " + this.fileName + " could not be opened!");
			e.printStackTrace();
		}
	}
	public void start() {
		Scanner keyboard = new Scanner(System.in);
		while(true) {
			String user = "";
			user += keyboard.next();
			if (user.toLowerCase().contains("quit")) {
				break;
			} else if (user.equals("reset")) {
				resetData();
				break;
			} else {
				Date date = new Date();
				writeTime(user + ": " + date.toString());
				System.out.println(readTime());
			}
		}
		keyboard.close();
	}
	public static void main(String[] args) {
		System.out.println("Welcome to tracking times, type start to start and stop to record end");
		DateAndTime testFile = new DateAndTime(args[0]);
		testFile.start();
		System.out.println(testFile.readTime());
	}
}
