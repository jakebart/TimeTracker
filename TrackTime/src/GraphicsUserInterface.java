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
	private Font font = new Font("Helvetica Neue",10,15);
	private int width = 100;
	private int height = 40;
	
	DateAndTime dateClass = new DateAndTime("/Users/j4k3/eclipse-workspace/TrackTime/src/Dates.csv");
	
	public GraphicsUserInterface() {
		setTitle("UI");
		setLayout(null);
		setButtons();
		setAction();
		setLabels(dateClass.lastAction() + "ed");
		setSize(500,500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setButtons() {
		startbtn = new JButton("Start");
		stopbtn = new JButton("Stop");
		startbtn.setBounds(125, 250, width, height);
		stopbtn.setBounds(250, 250, width, height);
		add(startbtn);
		add(stopbtn);
	}
	
	private void setAction() {
		startbtn.addActionListener(this);
		startbtn.setActionCommand("startTime");
		stopbtn.addActionListener(this);
		stopbtn.setActionCommand("stopTime");
	}
	private void setLabels(String lastAction) {
		lastSessionInfo = new JLabel();
		lastSessionInfo.setText("Session " + lastAction + " at: " + dateClass.LastSession());
		lastSessionInfo.setFont(font);
		lastSessionInfo.setBounds(70, 150, 400, height);
		add(lastSessionInfo);
	}
	private void clearLabel() {
		remove(lastSessionInfo);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("startTime")) {
			dateClass.start("start");
			clearLabel();
			setLabels("started");
		} else if (e.getActionCommand().equals("stopTime")) {
			dateClass.start("end");
			clearLabel();
			setLabels("ended");
		}
		repaint();
		
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		GraphicsUserInterface gui = new GraphicsUserInterface();
	}

}
