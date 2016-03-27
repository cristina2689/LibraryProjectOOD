import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

public abstract class Book {
	protected Calendar dueDate;
	private List holds = new LinkedList();

	/**
	 * Adds one more hold to the book
	 * 
	 * @param hold
	 *            the new hold on the book
	 */
	public void placeHold(Hold hold) {
		holds.add(hold);
	}

	/**
	 * Removes hold for a specific member
	 * 
	 * @param memberId
	 *            whose hold has to be removed
	 * @return true iff the hold could be removed
	 */
	public boolean removeHold(String memberId) {
		for (ListIterator iterator = holds.listIterator(); iterator.hasNext();) {
			Hold hold = (Hold) iterator.next();
			String id = hold.getMember().getId();
			if (id.equals(memberId)) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a valid hold
	 * 
	 * @return the next valid hold
	 */
	public Hold getNextHold() {
		for (ListIterator iterator = holds.listIterator(); iterator.hasNext();) {
			Hold hold = (Hold) iterator.next();
			iterator.remove();
			if (hold.isValid()) {
				return hold;
			}
		}
		return null;
	}

	/**
	 * Checks whether there is a hold on this book
	 * 
	 * @return true iff there is a hold
	 */
	public boolean hasHold() {
		ListIterator iterator = holds.listIterator();
		if (iterator.hasNext()) {
			return true;
		}
		return false;
	}

	/**
	 * Returns an iterator for the holds
	 * 
	 * @return iterator for the holds on the book
	 */
	public Iterator getHolds() {
		return holds.iterator();
	}

	public long diffDate(Date date2, TimeUnit timeUnit) {
		Date dueDay = dueDate.getTime();
		long diffInMillies = date2.getTime() - dueDay.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	public long getExtraDays() {
		Calendar today = new GregorianCalendar();
		today.setTimeInMillis(System.currentTimeMillis());
		return diffDate(today.getTime(), TimeUnit.DAYS);
	}
	public int getRaiseAmount() {
		if(this.hasHold()) {
			return 2;
		}
		return 1;
	}

	public abstract boolean issue(Member member);

	public abstract boolean renew(Member member);

	public abstract boolean checkFines(Member member);

}
