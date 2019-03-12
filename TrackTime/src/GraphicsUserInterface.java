import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class GraphicsUserInterface extends JFrame implements ActionListener {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -7816657412276748760L;
	
	private JButton startbtn, stopbtn;
	private JLabel lastSessionInfo;
	private JLabel subjectDescriptions;
	private ButtonGroup subjects;
	private Font font = new Font("Helvetica Neue",10,15);
	private int width = 100;
	private int height = 60;
	
	DateAndTime dateClass = new DateAndTime("/Users//Path/src/Dates.csv");
	
	public GraphicsUserInterface() {
		setTitle("Time Tracker");
		setLayout(null);
		setButtons();
		setAction();
		setSize(500,500);
		setLocationRelativeTo(null);
		add(startContainer());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	protected Container startContainer() {
		Container container = new Container();
		setLabel(dateClass.lastAction() + "ed");
		setButtons();
		setAction();
		container.setLayout(new FlowLayout());
		container.setSize(420, 200);
		container.setLocation(50, 380);
		container.add(lastSessionInfo);
		container.add(startbtn);
		container.add(stopbtn);
		container.setVisible(true);
		return container;
	}
	private void checkLastAction() {
		if (dateClass.lastAction().equals("start")) {
			stopbtn.setEnabled(true);
			startbtn.setEnabled(false);
		} else {
			stopbtn.setEnabled(false);
			startbtn.setEnabled(true);
		}
	}
	private void setButtons() {
		startbtn = new JButton("Start");
		stopbtn = new JButton("Stop");
		startbtn.setBounds(140, 350, width, height);
		stopbtn.setBounds(265, 350, width, height);
		checkLastAction();
	}
	
	private void setAction() {
		startbtn.addActionListener(this);
		startbtn.setActionCommand("startTime");
		stopbtn.addActionListener(this);
		stopbtn.setActionCommand("stopTime");
	}
	private void setLabel(String lastAction) {
		lastSessionInfo = new JLabel();
		lastSessionInfo.setText("Session " + lastAction + " at: " + dateClass.LastSession());
		lastSessionInfo.setFont(font);
		lastSessionInfo.setBounds(90, 300, 500, height);
		lastSessionInfo.setVisible(true);
	}
	private void clearLabel() {
		remove(lastSessionInfo);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("startTime")) {
			dateClass.start("start");
			clearLabel();
			setLabel("started");
		} else if (e.getActionCommand().equals("stopTime")) {
			dateClass.start("end");
			clearLabel();
			setLabel("ended");
		}
		checkLastAction();
		repaint();
		
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		GraphicsUserInterface gui = new GraphicsUserInterface();
	}

}
