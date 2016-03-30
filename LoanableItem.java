import java.util.Calendar;

public abstract class LoanableItem {
	protected String id;
	protected String title;
	protected Member borrowedBy;
	protected Calendar dueDate;
	/**
	 * Marks the item as issued to a member
	 * 
	 * @param member the borrower
	 * @return true iff the item be issued.
	 * 
	 * Periodicals cannot be issued
	 */
	public abstract boolean issue(Member member);

	/**
	 * Marks the item as returned
	 * 
	 * @return The member who had borrowed the item
	 */
	public abstract Member returnItem();

	/**
	 * Renews the item
	 * 
	 * @param member who wants to renew the item
	 * @return true iff the item could be renewed
	 */
	public abstract boolean renew(Member member);

	/**
	 * Getter for id
	 * 
	 * @return id of the item
	 */
	public String getId() {
		return id;
	}

	/**
	 * getter for title
	 * 
	 * @return title of the item
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Getter for due date
	 * 
	 * @return the date on which the item is due
	 */
	public String getDueDate() {
		return (dueDate.getTime().toString());
	}
	/**
	 * Getter for borrower
	 * 
	 * @return the member who borrowed the item
	 */
	public Member getBorrower() {
		return borrowedBy;
	}

}
