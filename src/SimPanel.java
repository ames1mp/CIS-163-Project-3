import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;



public class SimPanel extends JPanel implements ClockListener{

	
	boolean running = false;
	
	private Booth booth;
	private VoterProducer producer;
	private Clock clock;
	private Table tableAL, tableMZ;
	private ArrayList<Booth> booths;
	private ArrayList<Voter> middleQ;
	private ArrayList<Voter> completedVoters;
	private ArrayList<Table> checkIns; 
	
	private int averageArrivalTime = 20;
	private int checkInTime = 15;
	private int totalTime = 10000;
	private int numberOfBooths = 3;
	private int numCheckIns = 2;
	private int boothTime = 60;
	private int leaveTime = 900;
	private int previousNumCompletedVoters = 0;
	
	
	private JLabel label0, label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11,
	label12, label13, out1, out2, out3, out4, out5, out6, out7, out8, checkInsLabel; 
		
	private JButton startButton, quitButton, runSimAtOnceButton, changeParamsButton, resetSimButton;
	
	private JTextPane pane1, pane2, pane3, pane4, pane5, pane6, checkInsPane;
	
	private JCheckBox checkBox;
	
	public SimPanel() {
		
		checkBox = new JCheckBox();
		booths = new ArrayList<Booth>();
		checkIns = new ArrayList<Table>();
		clock = new Clock();
		middleQ = new ArrayList<Voter>();
		completedVoters = new ArrayList<Voter>();
		resetAndCreateCheckIns();
		resetAndCreateBooths();
		
		producer = new VoterProducer(averageArrivalTime, boothTime, checkInTime, leaveTime, checkIns);
		
		clock.add(producer);	
		clock.add(this);
		

		ButtonListener listener = new ButtonListener();
		
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(8, 2, 3, 2));
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(1, 2, 3, 2));
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(7, 2, 3, 2));
		
		
		label0 = new JLabel("Input Information");
		label1 = new JLabel("Seconds to Next Person");
		label2 = new JLabel("Average Seconds for Check in");
		label3 = new JLabel("Total time in seconds");
		label4 = new JLabel("Average Seconds for Voting");
		label5 = new JLabel("Seconds Before Person Leaves");
		label6 = new JLabel("Number of Booths");
		label7 = new JLabel("Output Information");
		label8 = new JLabel("Throughput");
		label9 = new JLabel("Average time for a voter from start to finish");
		label10 = new JLabel("Number of people who left line");
		label11 = new JLabel("Max Queue length Check in A-L");
		label12 = new JLabel("Max Queue length Check in M-Z");
		label13 = new JLabel("Max Queue length Voting Booth Line");
		checkInsLabel = new JLabel("1 or 2 Check in Booths?");
		
		
		out1 = new JLabel("~~~~~~~~");
		out2 = new JLabel("~~~~~~~~");
		out3 = new JLabel("~~~~~~~~");
		out4 = new JLabel("~~~~~~~~");
		out5 = new JLabel("~~~~~~~~");
		out6 = new JLabel("~~~~~~~~");
		out7 = new JLabel("~~~~~~~~");
		out8 = new JLabel("~~~~~~~~");
		
		
	
		startButton = new JButton();
		startButton.setText("Start Simulation");
		startButton.addActionListener(listener);
		
		quitButton = new JButton();
		quitButton.setText("Stop Simulation");
		quitButton.addActionListener(listener);
		
		runSimAtOnceButton = new JButton();
		runSimAtOnceButton.setText("Run Sim at Once");
		runSimAtOnceButton.addActionListener(listener);
		
		changeParamsButton = new JButton();
		changeParamsButton.setText("Update Parameters");
		changeParamsButton.addActionListener(listener);
		 
		resetSimButton = new JButton();
		resetSimButton.setText("Reset Simulation");
		resetSimButton.addActionListener(listener);
		
		
		pane1 = new JTextPane();
		pane2 = new JTextPane();
		pane3 = new JTextPane();
		pane4 = new JTextPane();
		pane5 = new JTextPane();
		pane6 = new JTextPane();
		checkInsPane = new JTextPane();
		
		pane1.setText(Integer.toString(averageArrivalTime));
		pane2.setText(Integer.toString(checkInTime));
		pane3.setText(Integer.toString(totalTime));
		pane4.setText(Integer.toString(boothTime));
		pane5.setText(Integer.toString(leaveTime));
		pane6.setText(Integer.toString(numberOfBooths));
		checkInsPane.setText(Integer.toString(numCheckIns));
			
		top.add(label0);
		top.add(out1);
		top.add(label1);
		top.add(pane1);
		top.add(label2);
		top.add(pane2);
		top.add(label3);
		top.add(pane3);
		top.add(label4);
		top.add(pane4);
		top.add(label5);
		top.add(pane5);
		top.add(label6);
		top.add(pane6);
		top.add(checkInsLabel);
		top.add(checkInsPane);
		
		center.add(startButton);
		center.add(quitButton);
		center.add(runSimAtOnceButton);
		center.add(changeParamsButton);
		center.add(resetSimButton);
		
		bottom.add(label7);
		bottom.add(out2);
		bottom.add(label8);
		bottom.add(out3);
		bottom.add(label9);
		bottom.add(out4);
		bottom.add(label10);
		bottom.add(out5);
		bottom.add(label11);
		bottom.add(out6);
		bottom.add(label12);
		bottom.add(out7);
		bottom.add(label13);
		bottom.add(out8);
		
		add(top, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);
		
		
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(startButton == e.getSource()) {
				if(running == false) {
					running = true;
					clock.start();
				}			
			}

			if(quitButton == e.getSource()) {
				if(running == true) {
					running = false;
					clock.stop();
				}
			}

			if(runSimAtOnceButton == e.getSource()) {
				if(running == false)
					updateParams();
					clock.runAtOnce(totalTime);
					
			}


			if(changeParamsButton == e.getSource()) {
				if(running == false) {
					updateParams();
				}
			}	
			
			if(resetSimButton == e.getSource()) {
				resetSim();
			}
		}	
	}

	private void resetAndCreateBooths() {
		if(booths.size() > 0)
			booths.clear();
		for(int i = 0; i < numberOfBooths; i++) {
			Booth booth = new Booth(middleQ, completedVoters);
			clock.add(booth);
			booths.add(booth);			
		}
	}
	
	private void resetAndCreateCheckIns() {
		if(checkIns.size() > 0)
			checkIns.clear();
		for(int i = 0; i < numCheckIns; i++) {
			Table table = new Table(middleQ);
			clock.add(table);
			checkIns.add(table);			
		}
	}
	
	private void resetSim() {
		averageArrivalTime = 20;
		checkInTime = 15;
	    totalTime = 10000;
		numberOfBooths = 3;
		boothTime = 60;
		leaveTime = 900;
		numCheckIns = 2;
		
		pane1.setText(Integer.toString(averageArrivalTime));
		pane2.setText(Integer.toString(checkInTime));
		pane3.setText(Integer.toString(totalTime));
		pane4.setText(Integer.toString(boothTime));
		pane5.setText(Integer.toString(leaveTime));
		pane6.setText(Integer.toString(numberOfBooths));
		checkInsPane.setText(Integer.toString(numCheckIns));
		
		clock = new Clock();
		middleQ = new ArrayList<Voter>();
		completedVoters = new ArrayList<Voter>();
		resetAndCreateCheckIns();
		resetAndCreateBooths();
		producer = new VoterProducer(averageArrivalTime, boothTime, checkInTime, leaveTime, checkIns);
		
		clock.add(producer);	
		clock.add(this);	
	}
	
	private void updateParams() {
		try {
			averageArrivalTime = Integer.parseInt(pane1.getText());
			checkInTime = Integer.parseInt(pane2.getText());
			totalTime = Integer.parseInt(pane3.getText());
			boothTime = Integer.parseInt(pane4.getText());
			leaveTime = Integer.parseInt(pane5.getText());
			numberOfBooths = Integer.parseInt(pane6.getText());
			if(checkInsValid() == true)
			numCheckIns = Integer.parseInt(checkInsPane.getText());
			
			pane1.setText(Integer.toString(averageArrivalTime));
			pane2.setText(Integer.toString(checkInTime));
			pane3.setText(Integer.toString(totalTime));
			pane4.setText(Integer.toString(boothTime));
			pane5.setText(Integer.toString(leaveTime));
			pane6.setText(Integer.toString(numberOfBooths));
			if(checkInsValid() == true)
				checkInsPane.setText(Integer.toString(numCheckIns));
			
			
		} catch(NumberFormatException e1) {
			e1.getMessage();
		}
		pane1.setText(Integer.toString(averageArrivalTime));
		pane2.setText(Integer.toString(checkInTime));
		pane3.setText(Integer.toString(totalTime));
		pane4.setText(Integer.toString(boothTime));
		pane5.setText(Integer.toString(leaveTime));
		pane6.setText(Integer.toString(numberOfBooths));
		
		checkInsPane.setText(Integer.toString(numCheckIns));
		
		clock = new Clock();
		middleQ = new ArrayList<Voter>();
		completedVoters = new ArrayList<Voter>();
		resetAndCreateCheckIns();
		resetAndCreateBooths();
		producer = new VoterProducer(averageArrivalTime, boothTime, checkInTime, leaveTime, checkIns);
		
		clock.add(producer);	
		clock.add(this);		
	}
	
	private boolean checkInsValid() {
		if (checkInsPane.getText().equals("1") || checkInsPane.getText().equals("2") ) {
			return true;
		}
		return false;		
	}
	
	private int getAverageTotalTime() {
		int runningTotal = 0;
		int numVoters = 0;
		int avgTime = 0;
			
			for(Voter v: completedVoters) {
				runningTotal += v.getCompleteTime() - v.getTickTime();
				numVoters++;		
		}
		if (numVoters != 0)
			avgTime = (int) (runningTotal/numVoters);
			
		return avgTime;
	}
	
	public void displayInput() {
		StringBuffer buffer = new StringBuffer();
		for(Voter v : middleQ) {
			if(v instanceof RegularVoter) {
				buffer.append("R ");
			} else {
				if(v instanceof LimitedTimeVoter) {
					buffer.append("L ");
				} else {
					if(v instanceof SpecialNeedsVoter) {
						buffer.append("S ");
					} else {
						buffer.append("! ");
					}
				}
			}
		}
		
		buffer.reverse();
		if(buffer.length() > 50) {
			int lengthOver50 = buffer.length() - 50;
			buffer.delete(0, lengthOver50 - 1);
		}
		int remainder = middleQ.size() - (buffer.length() / 2);
		String remainderString = Integer.toString(remainder);
		if(middleQ.size() > 25)
			buffer.append(" + " + remainderString + " more");
		out1.setText(buffer.toString());
	}
	
	public void displayOutput() {
		StringBuffer buffer = new StringBuffer();
		for(Voter v : completedVoters) {
			if(v instanceof RegularVoter) {
				buffer.append("R ");
			} else {
				if(v instanceof LimitedTimeVoter) {
					buffer.append("L ");
				} else {
					if(v instanceof SpecialNeedsVoter) {
						buffer.append("S ");
					} else {
						buffer.append("! ");
					}
				}
			}
		}
		
		
		if(buffer.length() > 50) {
			int lengthOver50 = buffer.length() - 50;
			buffer.delete(0, lengthOver50 - 1);
		}
			
		buffer.reverse();
		int remainder = completedVoters.size() - (buffer.length() / 2);
		String remainderString = Integer.toString(remainder);
		if(completedVoters.size() > 25)
			buffer.append(" + " + remainderString + " more");
		out2.setText(buffer.toString());
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Voting Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 400);
		frame.setVisible(true);
		JPanel panel = new SimPanel();
		frame.getContentPane().add(panel);
		
		SimPanel sim = new SimPanel();				
	}

	@Override
	public void event(int tick) {
		
		displayInput();
		displayOutput();
		
		out3.setText(Integer.toString(completedVoters.size()));		
		int avgTime = getAverageTotalTime();
		out4.setText(Integer.toString(avgTime));
		int leftQ = 0;
		for(Table t : checkIns) {
			leftQ += t.getNumLeftQ();
		}
		out5.setText(Integer.toString(leftQ));
		if (checkIns.size() == 2) {
			out6.setText(Integer.toString(checkIns.get(0).getMaxQlength()));
			out7.setText(Integer.toString(checkIns.get(1).getMaxQlength()));
		} else {
			label11.setText("Max queue length check in");
			label12.setText("Unused");
			out6.setText(Integer.toString(checkIns.get(0).getMaxQlength()));
			out7.setText("~~~~~~~~~~~ N/A ~~~~~~~~~~~");
		}
		
		
		int indexOfLastBooth = booths.size() - 1;
		out8.setText(Integer.toString(booths.get(indexOfLastBooth).getMaxMiddleQLength()));
	}


}
