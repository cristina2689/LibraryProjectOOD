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
/**
 * Represents a single book
 * @author Brahma Dathan and Sarnath Ramnath
 *
 */

public class OlderBook extends Book implements Serializable  {
  private static final long serialVersionUID = 1L;
  private String title;
  private String author;
  private String id;
  private Member borrowedBy;
  
  /**
   * Creates a book with the given id, title, and author name
   * @param title book title
   * @param author author name
   * @param id book id
   */
  public OlderBook(String title, String author, String id) {
    this.title = title;
    this.author = author;
    this.id = id;
  }
  /**
   * Marks the book as issued to a member
   * @param member the borrower
   * @return true iff the book could be issued. True currently
   */
  @Override
public boolean issue(Member member) {
    borrowedBy = member;
    dueDate = new GregorianCalendar();
    dueDate.setTimeInMillis(System.currentTimeMillis());
    dueDate.add(Calendar.MONTH, 1);
    return true;
  }
  /**
   * Marks the book as returned
   * @return The member who had borrowed the book
   */
public Member returnBook() {
    if (borrowedBy == null) {
      return null;
    } else {
      Member borrower = borrowedBy;
      borrowedBy = null;
      return borrower;
    }
  }
  /**
   * Renews the book 
   * @param member who wants to renew the book
   * @return true iff the book could be renewed
   */
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
  /**
   * Getter for author
   * @return author name
   */
  public String getAuthor() {
    return author;
  }
  /**
   * getter for title
   * @return title of the book
   */
  public String getTitle() {
    return title;
  }
  /**
   * Getter for id
   * @return id of the book
   */
  public String getId() {
    return id;
  }
  /**
   * Getter for borrower
   * @return the member who borrowed the book
   */
  public Member getBorrower() {
    return borrowedBy;
  }
  
  /**
   * Getter for due date
   * @return the date on which the book is due
   */
  public String getDueDate() {
      return (dueDate.getTime().toString());
  }
  /** 
   * String form of the book
  * 
  */
  @Override
public String toString() {
    return "title " + title + " author " + author + " id " + id + " borrowed by " + borrowedBy;
  }

	// Older books are charged 15 bani for the first day and 5 bani for others
	@Override
	public boolean checkFines(Member member) {
		long dueDays = getExtraDays();
		
		if (dueDays == 1) {
			member.addAmountToFine(15 * getRaiseAmount());
			return true;
		}
		if (dueDays > 1) {
			member.addAmountToFine(5 * getRaiseAmount());
			return true;
		}
		return false;
	}
}