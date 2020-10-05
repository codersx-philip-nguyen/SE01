package ch15.mediator;

/**
 * @overview An implementation of <code>Observer</code> that supports one-to-many
 *           interaction between subjects and observers.
 *           
 * @author dmle
 */
public class T implements Observer {
  private boolean isAlive;
  
  // a counter to keep track of the object ids
  private static int counter = 0;
  private int id;
  
  // the mediator object with which to interact with subject
  private Mediator med;
  
  /**
   * 
   * @requires  <code>s</code> is not <code>null</code>
   * @effects   initialises <code>this</code> with a given <code>Subject</code> s
   */
  public T(Mediator med) {
    //
    this.med = med;
    isAlive = true;
    id = ++counter;
  }
  
  public String toString() {
    return "T:"+id;
  }
  
  // implements interface methods
  public boolean receiveUpdate(Object data) {
    if (data == null) {
      System.out.println(this + " isAlive " + isAlive);
      return isAlive;  // for keep alive
    } else if (isAlive) {
      // process the data here and returns the status
      System.out.println(this + " received " + data);
      return true;
    }
    
    return false;
  }
  
  public Object pull() {
    return med.pull();
  }
}
