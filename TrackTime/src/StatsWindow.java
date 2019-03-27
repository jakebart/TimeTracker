import javax.swing.*;
import java.awt.*;

public class StatsWindow extends TimeStats {

	//TODO extand JFrame maybe?
	private int windowWidth = 300;
	private int windowHieght = 300;
	private JLabel allAverage, totalTime;
	
	public StatsWindow() {
		JFrame frame = new JFrame("Stats");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(null);
		frame.add(labels());
		frame.setSize(windowWidth,windowHieght);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	protected Panel labels() {
		Panel panel = new Panel();
		GroupLayout layout = new GroupLayout(panel);

		setLabels();
		
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(totalTime)
						.addComponent(allAverage)
					)		
		);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(totalTime)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(allAverage)
				)
				
		);
		
		panel.setSize(windowWidth, windowHieght);
		panel.setLocation(15, 50);
		panel.setVisible(true);
		return panel;
	}
	private void setLabels() {
		allAverage = new JLabel();
		allAverage.setText("All time average: " + allTimeAverage());
		totalTime = new JLabel();
		totalTime.setText("Total time spent: " + totalTime());
	}

}
