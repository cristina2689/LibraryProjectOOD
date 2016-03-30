
/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS"AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  
 */
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Represents a single book
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 *
 */
public class Book extends LoanableItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private String author;
	private List holds = new LinkedList();

	/**
	 * Creates a book with the given id, title, and author name
	 * 
	 * @param title book title
	 * @param author author name
	 * @param id book id
	 */
	public Book(String title, String author, String id) {
		this.title = title;
		this.author = author;
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
	public boolean renew(Member member) {
		if (hasHold()) {
			return false;
		}
		if ((member.getId()).equals(borrowedBy.getId())) {
			return (issue(member));
		}
		return false;
	}

	@Override
	public Member returnItem() {
		Member borrower = borrowedBy;
		borrowedBy = null;
		return borrower;
	}

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

	/**
	 * Getter for author
	 * 
	 * @return author name
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * String form of the book
	 * 
	 */
	@Override
	public String toString() {
		return "title " + title + " author " + author + " id " + id + " borrowed by " + borrowedBy;
	}
}