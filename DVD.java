import java.util.Calendar;
import java.util.GregorianCalendar;

public class DVD extends LoanableItem {

	private int duration;
	
	public DVD(String title, String id, Integer duration) {
		this.title = title;
		this.id = id;
		this.setDuration(duration);
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	@Override
	public boolean issue(Member member) {
		borrowedBy = member;
		dueDate = new GregorianCalendar();
		dueDate.setTimeInMillis(System.currentTimeMillis());
		dueDate.add(Calendar.MONTH, 1);
		return true;
	}
	@Override
	public Member returnItem() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean renew(Member member) {
		if ((member.getId()).equals(borrowedBy.getId())) {
			return (issue(member));
		}
		return false;
	}

}
