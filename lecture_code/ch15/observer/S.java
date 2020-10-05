package ch15.observer;

import java.util.Iterator;
import java.util.Vector;

import utils.NotPossibleException;

public class S implements Subject {
  // implements Subject interface
  
  private String myState;
  // a counter to keep track of the object ids
  private static int counter = 0;
  private int id;
  
  // maintains a list of all the observers
  private Vector observers;
  
  public S() {
    observers = new Vector();
    id = ++counter;
  }
  
  /**
   * @effects Returns true if at least one of the observers is alive, else
   *          returns false
   */
  public boolean keepAlive() {
    // use a null notification to check if any of observers is alive
    try {
      update(null);
    } catch (NotPossibleException e) {
      // none of the observers is alive
      return false;
    }
    return true;
  }
  
  public void someMethod() {
    // simulate a user code that changes the state of this object
    if (myState == null) {
      myState = "hello world";
    } else {
      myState += ".";
    }
    
    // notify observers
    try {
      update(myState);
    } catch (NotPossibleException e) {
      // ignore
    }
  }
  
  public String toString() {
    return "S:"+id;
  }
  
  // implements interface methods
  public void registerObserver(Observer o) {
    observers.add(o);
  }
  
  public void update(Object data) throws NotPossibleException {
    // update all observers
    boolean success = false;
    for (Iterator it = observers.iterator(); it.hasNext();) {
      Observer o = (Observer) it.next();
      boolean result = o.receiveUpdate(data);
      if (result) success = true;
    }
    
    if  (!success) {
      throw new NotPossibleException("S.update");
    }
  }

  public Object receiveQuery() {
    // returns state data
    return myState;
  }
}
