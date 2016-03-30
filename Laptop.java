import java.util.Calendar;
import java.util.GregorianCalendar;

public class Laptop extends LoanableItem {

	private String model;
	
	public Laptop(String title, String id) {
		this.title = title;
		this.id = id;
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
		Member borrower = borrowedBy;
		borrowedBy = null;
		return borrower;
	}

	@Override
	public boolean renew(Member member) {
		if ((member.getId()).equals(borrowedBy.getId())) {
			return (issue(member));
		}
		return false;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
