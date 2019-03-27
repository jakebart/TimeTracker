import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;


public class GraphicsUserInterface extends JFrame implements ActionListener, ItemListener {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -7816657412276748760L;
	
	private int windowWidth = 500;
	private int windowHieght = 500;
	private JButton startbtn, stopbtn, showStats;
	private JLabel lastSessionInfo;
	private JLabel subjectDescriptions;
	private ButtonGroup subjects;
	private String optionSelected;
	private Font font = new Font("Helvetica Neue",10,15);
	private int btnwidth = 140;
	private int btnheight = 40;
	private int radiobtnSize = 20;
	private JRadioButton[] radiobtnArray = new JRadioButton[3];
	
	DateAndTime dateClass = new DateAndTime("/Users/j4k3/eclipse-workspace/TrackTime/src/Dates.csv");
	
	public GraphicsUserInterface() {
		setTitle("Time Tracker");
		setLayout(null);
		setSize(windowWidth,windowHieght);
		setLocationRelativeTo(null);
		add(startPanel());
		add(showStats);
		add(optionPanel());
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	protected Panel startPanel() {
		Panel actionPanel = new Panel();
		GroupLayout layout = new GroupLayout(actionPanel);
		
		setLabel(dateClass.lastAction() + "ed");
		setButtons();
		setAction();
		
		actionPanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(lastSessionInfo)
						.addGroup(layout.createSequentialGroup()
							.addComponent(startbtn, 50, btnwidth, btnwidth)
							.addComponent(stopbtn, 50, btnwidth, btnwidth)
						)
				)
		);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(lastSessionInfo)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(startbtn, 20, btnheight, btnheight)
						.addComponent(stopbtn, 20, btnheight, btnheight)
				)
				
		);
		
		actionPanel.setSize(windowWidth, 75);
		actionPanel.setLocation(70, 350);
		actionPanel.setVisible(true);
		return actionPanel;
	}
	protected Panel optionPanel() {
		Panel actionPanel = new Panel();
		GroupLayout layout = new GroupLayout(actionPanel);
		
		subjectDescriptions = new JLabel();
		subjectDescriptions.setText("Catagories");
		
		setOptions();
		
		actionPanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(subjectDescriptions)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(radiobtnArray[0])
							.addComponent(radiobtnArray[1])
							.addComponent(radiobtnArray[2])
						)
				)
		);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(subjectDescriptions)
				
				
						.addComponent(radiobtnArray[0])
						
				
						.addComponent(radiobtnArray[1])
				
						.addComponent(radiobtnArray[2])
				
				
		);
		
		actionPanel.setSize(windowWidth, 200);
		actionPanel.setLocation(10, 80);
		actionPanel.setVisible(true);
		return actionPanel;
	}
	private void setOptions() {
		//TODO set enabled last option
		JRadioButton option1, option2, option3;
		subjects = new ButtonGroup();
		option1 = new JRadioButton();
		option2 = new JRadioButton();
		option3 = new JRadioButton();
		option1.setText("Physics");
		option2.setText("Econ");
		option3.setText("Math");
		subjects.add(option1);
		subjects.add(option2);
		subjects.add(option3);
		
		radiobtnArray[0] = option1;
		radiobtnArray[1] = option2;
		radiobtnArray[2] = option3;
	}
	private void checkLastAction() {
		boolean previousAction = dateClass.lastAction().equals("start");
		stopbtn.setEnabled(previousAction);
		startbtn.setEnabled(!previousAction);
	}
	
	private void setButtons() {
		startbtn = new JButton("Start");
		stopbtn = new JButton("Stop");
		startbtn.setPreferredSize(new Dimension(btnwidth, btnheight));
		stopbtn.setPreferredSize(new Dimension(btnwidth, btnheight));
		checkLastAction();
		showStats = new JButton("Stats");
		showStats.setBounds(420, 10, 70, 20);
	}
	private void setAction() {
		startbtn.addActionListener(this);
		startbtn.setActionCommand("startTime");
		stopbtn.addActionListener(this);
		stopbtn.setActionCommand("stopTime");
		showStats.setEnabled(true);
		showStats.addActionListener(this);
		showStats.setActionCommand("showStats");
	}
	
	private void setLabel(String lastAction) {
		lastSessionInfo = new JLabel();
		lastSessionInfo.setText("Session " + lastAction + " at: " + dateClass.LastSession());
		lastSessionInfo.setFont(font);
		lastSessionInfo.setVisible(true);
		repaint();
	}
	private void clearLabel() {
		remove(lastSessionInfo);
	}
	
	//TODO bug with updating time label
	@Override
	public void actionPerformed(ActionEvent e) {
		checkOption();
		clearLabel();
		switch(e.getActionCommand()) {
			case "startTime":
				dateClass.start("start", optionSelected);
				setLabel("started");
				break;
			case "stopTime":
				dateClass.start("end", optionSelected);
				setLabel("ended");
				break;
			case "showStats":
				new StatsWindow();
				break;
			default:
				break;	
		}
		checkLastAction();
		repaint();
	}
	protected void checkOption() {
		for (int i = 0; i < radiobtnArray.length; i++) {
			if (radiobtnArray[i].isSelected()) {

				optionSelected = radiobtnArray[i].getText();
			}
		}
	}
	@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			for (int i = 0; i < radiobtnArray.length; i++) {
				if (radiobtnArray[i].isSelected()) {

					optionSelected = radiobtnArray[i].getText();
				}
			}
		}
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		GraphicsUserInterface gui = new GraphicsUserInterface();
	}

	

}
