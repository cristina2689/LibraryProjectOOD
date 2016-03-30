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
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * The collection class for item objects
 * @author Brahma Dathan and Sarnath Ramnath
 *
 */
public class Catalog implements Serializable {
  private static final long serialVersionUID = 1L;
  private List items = new LinkedList();
  private static Catalog catalog;
  /*
   * Private constructor for singleton pattern
   * 
   */
  private Catalog() {
  }
  /**
   * Supports the singleton pattern
   * 
   * @return the singleton object
   */
  public static Catalog instance() {
    if (catalog == null) {
      return (catalog = new Catalog());
    }
    return catalog;
  }
  /**
   * Checks whether a item with a given item id exists.
   * @param itemId the id of the item
   * @return true iff the item exists
   * 
   */
  public LoanableItem search(String itemId) {
    for (Iterator iterator = items.iterator(); iterator.hasNext(); ) {
      LoanableItem item = (LoanableItem) iterator.next();
      if (item.getId().equals(itemId)) {
        return item;
      }
    }
    return null;
  }
  /**
   * Removes an item from the catalog
   * @param itemID
   * @return true iff item could be removed
   */
  public boolean removeItem(String itemId) {
    LoanableItem item = search(itemId);
    if (item == null) {
      return false;
    }
    return items.remove(item);
  }
  /**
   * Inserts a item into the collection
   * @param item the item to be inserted
   * @return true iff the item could be inserted. Currently always true
   */
  public boolean insertItem(LoanableItem item) {
    items.add(item);
    return true;
  }
  /**
   * Returns an iterator to all items
   * @return iterator to the collection
   */
  public Iterator getItems() {
    return items.iterator();
  }
  /*
   * Supports serialization
   * @param output the stream to be written to
   */
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(catalog);
    } catch(IOException ioe) {
      System.out.println(ioe);
    }
  }
  /*
   * Supports serialization
   *  @param input the stream to be read from
   */
  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (catalog != null) {
        return;
      } else {
        input.defaultReadObject();
        if (catalog == null) {
          catalog = (Catalog) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      System.out.println("in Catalog readObject \n" + ioe);
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
  /** String form of the collection
  * 
  */
  @Override
public String toString() {
    return items.toString();
  }
}
