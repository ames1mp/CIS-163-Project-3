import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * @author   Roger Ferguson
 */
public class Clock implements ActionListener{

	private ClockListener[] myListeners;
	private int numListeners;
	private static final int TICKRATE = 500;
	private int MAX = 100;
	private int tick = 0;
	private Timer timer;

	public Clock() {
		numListeners = 0;
		myListeners = new ClockListener[MAX];
		timer = new Timer(TICKRATE, this);
	}

	public void run(int tick) {
			for (int j = 0; j < numListeners; j++)
				myListeners[j].event(tick);
		
	}
	
	public void runAtOnce(int endingTime) {
		for (int currentTime = 0; currentTime <= endingTime; currentTime++) {
			for (int j = 0; j < numListeners; j++)
				myListeners[j].event(currentTime);
		}
	}

	public void add(ClockListener cl) {
		myListeners[numListeners] = cl;
		numListeners++;
	}

	public ClockListener[] getMyListeners() {
		return myListeners;
	}

	public void setMyListeners(ClockListener[] myListeners) {
		this.myListeners = myListeners;
	}

	public int getNumListeners() {
		return numListeners;
	}

	public void setNumListeners(int numListeners) {
		this.numListeners = numListeners;
	}

	public int getMAX() {
		return MAX;
	}
	
	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		tick++;
		run(tick);
		
	}

}