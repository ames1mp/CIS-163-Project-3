
/**
 * @author   Roger Ferguson
 */
public class Voter {
	/** the amount of time the voter takes at the check in table*/
	protected int checkInTime;
	/** the amount of time that must pass before the voter leaves the line */
	protected int leaveTime;
	/** the amount of time spent in the voting booth */
	protected int votingBoothTime;
	/** the time (tick) at which the voter was created */
	private int tickTime;
	/** the time (tick) at which the voter left the voting booth*/
	private int completeTime;
		
	/*
	public Voter(int checkInTime, int leaveTime, int votingBoothTime){
		this.checkInTime = checkInTime;
		this.leaveTime = leaveTime;
		this.votingBoothTime = votingBoothTime;
	}
	*/
	
	
	private Booth Destination;
	

	protected double boothTime;
	
	public int getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(int checkInTime) {
		this.checkInTime = checkInTime;
	}

	public int getLeaveTime() {
		return leaveTime;
	}
	
	/************************************************************************
    @return The number of columns on the game board
	************************************************************************/
	public void setLeaveTime(int leaveTime) {
		this.leaveTime = leaveTime;
	}

	public int getVotingBoothTime() {
		return votingBoothTime;
	}

	public void setVotingBoothTime(int votingBoothTime) {
		this.votingBoothTime = votingBoothTime;
	}
	
	public Booth getDestination() {
		return Destination;
	}

	public void setDestination(Booth destination) {
		Destination = destination;
	}
	
	public int getTickTime() {
		return tickTime;
	}

	public void setTickTime(int tickTime) {
		this.tickTime = tickTime;
	}

	public int getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(int completeTime) {
		this.completeTime = completeTime;
	}



}