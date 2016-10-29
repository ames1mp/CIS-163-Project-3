
public class SpecialNeedsVoter extends Voter{
	
	@Override
	public void setCheckInTime(int checkInTime) {
		// TODO Auto-generated method stub
		super.setCheckInTime((int) (checkInTime * 1.5));
	}

	@Override
	public void setLeaveTime(int leaveTime) {
		// TODO Auto-generated method stub
		super.setLeaveTime(leaveTime * 2);
	}

	@Override
	public void setVotingBoothTime(int votingBoothTime) {
		// TODO Auto-generated method stub
		super.setVotingBoothTime(votingBoothTime * 3);
	}
	
	
}
