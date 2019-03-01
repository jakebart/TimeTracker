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
	public void start(String buttonAction) {
		Date date = new Date();
		writeTime(buttonAction + ", " + date.toString());
		System.out.println(readTime());

	}
	public String LastSession() {
		String time = "";
		try {
			Scanner timeFile = new Scanner(new File(this.fileName));
			while(timeFile.hasNextLine()) {
				time = timeFile.nextLine();
				
			}
			timeFile.close();
		} catch (FileNotFoundException e) {
			System.err.print(this.fileName + " could not be read!");
			e.printStackTrace();
		}
		if (time.isEmpty()) {
			return "0";
		}
		return time.substring(4).replaceAll("t, ", "");
	}
	public String lastAction() {
		
		if (readTime().lastIndexOf("end") > readTime().lastIndexOf("start")) {
			return "end";
		} else if (readTime().isEmpty()) {
			return "was empti";
		}
		else {
			return "start";
		}
	}
	
}
