

	import java.util.ArrayList;
	/**
	 * @author   Roger Ferguson
	 */
	public class Table implements ClockListener {
		private ArrayList<Voter> Q = new ArrayList<Voter>();
		
		private int timeOfNextEvent = 0;
		private int maxQlength = 0;
		private Voter person;   // this is the person at the booth. 
		private int completed = 0;
		private ArrayList<Voter> middleQ;
		private int numLeftQ;
		
		
		public Table(ArrayList<Voter> middleQ) {
			this.middleQ = middleQ;
		}
		
		public void add (Voter person)
		{
			Q.add(person);
			if (Q.size() > maxQlength)
				maxQlength = Q.size();
		}
		
		public void event (int tick) {
		
			
			if (tick >= timeOfNextEvent) {
				
				if (Q.size() >= 1) {
					person = Q.remove(0);
					timeOfNextEvent = tick + (int) (person.getCheckInTime() + 1);
					middleQ.add(person);					
					person = null;
					completed++;										
				}	
			}
			
			//check for people leaving table Q 
			for(int i = 0; i < Q.size(); i++) {
				if (tick >= Q.get(i).getLeaveTime()) {;
					numLeftQ++;
					Q.remove(i);				
				}
			}
			
			//check for people leaving q in middle q
			for(int i = 0; i < middleQ.size(); i++) {
				if (tick >= middleQ.get(i).getLeaveTime()) {
					numLeftQ++;
					middleQ.remove(i);				
				}
			}
		}
		
		public int getLeft() {
			return Q.size();
		}
		
		public int getMaxQlength() {
			return maxQlength;
		}

		public int getThroughPut() {
			return completed;
		}

		public int getNumLeftQ() {
			return numLeftQ;
		}

		public void setNumLeftQ(int numLeftQ) {
			this.numLeftQ = numLeftQ;
		}
	}

