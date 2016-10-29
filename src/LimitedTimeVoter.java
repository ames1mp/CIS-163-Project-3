
public class LimitedTimeVoter extends Voter{
	
	@Override
	public void setLeaveTime(int leaveTime) {
		// TODO Auto-generated method stub
		super.setLeaveTime((int) (leaveTime * 0.5));
	}
	@Override
	public void setVotingBoothTime(int votingBoothTime) {
		// TODO Auto-generated method stub
		super.setVotingBoothTime((int) (votingBoothTime * 0.5));
	}
	
	
}
