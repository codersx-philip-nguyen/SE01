package ch15.mediator;

import utils.NotPossibleException;

public class S implements Subject {
  // implements Subject interface
  
  private String myState;
  // a counter to keep track of the object ids
  private static int counter = 0;
  private int id;
  
  // the mediator object which is used for communication with observers
  private Mediator med;
  
  public S(Mediator med) {
    this.med = med; 
    id = ++counter;
  }
  
  /**
   * @effects Returns <code>this.med</code> object 
   */
  public Mediator getMediator() {
    return med;
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
  public void update(Object data) throws NotPossibleException {
    med.update(data);
  }

  public Object receiveQuery() {
    // returns state data
    return myState;
  }
}
