

	import java.util.ArrayList;
	/**
	 * @author   Roger Ferguson
	 */
	public class Booth implements ClockListener {
		private ArrayList<Voter> Q = new ArrayList<Voter>();
		
		private int timeOfNextEvent = 0;
		private int maxQlength = 0;
		private Voter person;   // this is the person at the booth. 
		private int completed = 0;
		private int numLeftQ = 0;
		ArrayList<Voter> middleQ;
		ArrayList<Voter> completedVoters;
		private int maxMiddleQLength = 0;
		
		
		public int getMaxMiddleQLength() {
			return maxMiddleQLength;
		}

		public void setMaxMiddleQLength(int maxMiddleQLength) {
			this.maxMiddleQLength = maxMiddleQLength;
		}

		public Booth(ArrayList<Voter> middleQ, ArrayList<Voter>completedVoters) {
			this.middleQ = middleQ;
			this.completedVoters = completedVoters;
		}

		public void add (Voter person)
		{
			Q.add(person);
			if (Q.size() > maxQlength)
				maxQlength = Q.size();
		}
		
		public void event (int tick) {
			if(middleQ.size() > maxMiddleQLength) {
				maxMiddleQLength = middleQ.size();
			}
			if (tick >= timeOfNextEvent) {
				if(person != null) {
					person.setCompleteTime(tick);
					completedVoters.add(person);
					person = null;
				}	

				
				if (middleQ.size() >= 1) {
						
					person = middleQ.remove(0);
					timeOfNextEvent = tick + (int) (person.getVotingBoothTime() + 1);
					completed++;										
				}	
			}
			
			/*
			for(int i = 0; i < Q.size(); i++) {
				if (tick >= Q.get(i).getLeaveTime()) {;
					numLeftQ++;
					Q.remove(i);				
				}
			}
			*/
			//check leave time in MiddleQ
			
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

