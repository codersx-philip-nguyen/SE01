package ch15.mediator;

import java.util.Iterator;
import java.util.Vector;

import utils.NotPossibleException;

/**
 * @overview Represents a one-to-many mediator.
 * 
 * @author dmle
 *
 */
public class Mediator {
  // supports one-to-many interaction between one subject and several observers
  private Subject s;
  private Vector observers;
  
  /**
   * @effects  Initialises <code>this</code> with an empty list of observers
   */
  public Mediator() {
    observers = new Vector();
  }

  /**
   * @requires <code>s</code> is not <code>null</code>
   * @modifies  <code>this</code>
   * @effects  Updates <code>this.s</code> with <code>s</code>. Throws 
   *           <code>NotPossibleException</code> if <code>this.s != null</code>, 
   *           i.e. violating the one-to-many communication style of <code>this</code>
   */
  public void registerSubject(Subject s) throws NotPossibleException {
    if (this.s != null)
      throw new NotPossibleException("Mediator.registerSubject: one-to-many communication only");
    
    this.s = s;
  }
  
  /**
   * @requires  <code>o</code> is not <code>null</code>
   * @effects   Adds <code>o</code> to <code>this</code>
   */
  public void registerObserver(Observer o) {
    observers.add(o);
  }
  
  /**
   * @effects Sends an update notification to observers. The notification may or  
   *          may not contain data (i.e. <code>data</code> may be <code>null</code>).
   *          Throws <code>NotPossibleException</code> if none of the observers 
   *          successfully receive the update.
   */  
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
  
  /**
   * @effects Returns an <code>Object</code> containing the state data about
   *          the last update notification.  
   */
  public Object pull() {
    Object state = s.receiveQuery(); 
    
    System.out.println(this + " pulled " + state);
    
    return state;
  }  
}
