package ch15.state;

import java.util.Iterator;

/**
 * @overview <code>SetState</code> is used to implement <code>Set</code>; it provides
 *           most of the set methods. <code>SetState</code> objects are mutable. 
 * @author dmle
 *
 */
public interface SetState {
  // methods declarations go here...
  public int size();
  
  public void insert(Object x);
  
  public Iterator elements();
}
