package ch15.state;

import java.util.Iterator;

public class BigSet implements SetState {
  // similar to SmallSet except that it uses a Hashtable to store large number of objects
  
  // constructor method
  /**
   * @effects Initialises <code>this</code> to contain a collection of elements
   */
  public BigSet(Iterator elements) {
    // real implementation goes here
  }
  
  public int size() {
    // real implementation goes here
    return 0;
  }
  
  public void insert(Object x) {
    // real implementation goes here    
  }

  public Iterator elements() {
    // real implementation goes here
    return null;
  }
}
