package ch15.observer;

/**
 * @overview An implementation of <code>Observer</code> that supports one-to-many
 *           interaction between subjects and observers.
 *           
 * @author dmle
 */
public class T implements Observer {
  private Subject s;
  private boolean isAlive;
  
  // a counter to keep track of the object ids
  private static int counter = 0;
  private int id;
  
  /**
   * 
   * @requires  <code>s</code> is not <code>null</code>
   * @effects   initialises <code>this</code> with a given <code>Subject</code> s
   */
  public T(Subject s) {
    //
    this.s = s;
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
    Object state = s.receiveQuery(); 
    
    System.out.println(this + " pulled " + state);
    
    return state;
  }
}
