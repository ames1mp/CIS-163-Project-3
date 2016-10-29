
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Roger Ferguson
 */
public class VoterProducer implements ClockListener {
	
	
	private int nextPerson = 0;
	
	//private int averageBoothTime;
	/** the amount of time the voter takes at the check in table*/
	private int checkInTime;
	/** the amount of time that must pass before the voter leaves the line */
	private int leaveTime;
	/** the amount of time spent in the voting booth */
	private int boothTime;
	/** the average rate at which new voters are produced */
	private int averageArrivalTime;
	
	//private Table tableAL;
//	private Table tableMZ;
	private ArrayList<Table> tables;

	
	private Random r = new Random();
	
	public VoterProducer(int averageArrivalTime, int boothTime, int checkInTime, int leaveTime, ArrayList<Table> tables) {
		
		
		this.averageArrivalTime = averageArrivalTime;
		this.boothTime = boothTime;
		this.checkInTime = checkInTime;
		this.leaveTime = leaveTime;
		//this.tableAL = tableAL;
	//	this.tableMZ = tableMZ;
		this.tables = tables;
		//r.setSeed(13);    // This will cause the same random numbers
	}
	
	public void event(int tick) {
		if (nextPerson <= tick) {
			nextPerson = (int) (tick + (averageArrivalTime*0.5*r.nextGaussian() + averageArrivalTime +.5));
			
			Voter person;
			
			int rNumber = (int)(Math.random() * 100);
			
			if(rNumber <= 10) {
				if(rNumber < 5) {
					person = new SuperSpecialNeedsVoter();
				} else {
					person = new SpecialNeedsVoter();
				} 			
				
			} else if (rNumber < 30 ) {
				 person = new LimitedTimeVoter();
			} else {
				 person = new RegularVoter();
			}

			person.setVotingBoothTime((int) (boothTime*0.5*r.nextGaussian() + boothTime +.5));
			person.setTickTime(tick);
			person.setLeaveTime(leaveTime + tick);
			person.setCheckInTime(checkInTime);
			
			
			if(tables.size() == 2) {
				if (rNumber < 50) {
					tables.get(0).add(person);
				} else {
					tables.get(1).add(person);
				}
			} else {
				if(tables.size() == 1 ) {
					tables.get(0).add(person);
				}
			}
			//	booth.add(person);
			
		//	person.setDestination(theLocationAfterTheBooth);  // You can save off where the voter should go.
		}
	}

}