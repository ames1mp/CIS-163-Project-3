
public class SuperSpecialNeedsVoter extends SpecialNeedsVoter{
	
	private int checkInTime = (int) (super.checkInTime * 1.25);
	
	private int leaveTime = (int) (super.leaveTime * 1.25);
	
	private int votingBoothTime = (int) (super.votingBoothTime * 1.25);

	@Override
	public void setCheckInTime(int checkInTime) {
		// TODO Auto-generated method stub
		super.setCheckInTime((int) (checkInTime * 1.5));
	}

	@Override
	public void setLeaveTime(int leaveTime) {
		// TODO Auto-generated method stub
		super.setLeaveTime((int) (leaveTime* 1.5));
	}

	@Override
	public void setVotingBoothTime(int votingBoothTime) {
		// TODO Auto-generated method stub
		super.setVotingBoothTime((int) (votingBoothTime * 1.5));
	}
	
	

	
}
