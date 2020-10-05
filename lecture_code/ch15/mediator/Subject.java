package ch15.mediator;

import utils.NotPossibleException;

public interface Subject { 
  /**
   * @effects Sends an update notification to observers. The notification may or  
   *          may not contain data (i.e. <code>data</code> may be <code>null</code>).
   *          Throws <code>NotPossibleException</code> if none of the observers 
   *          successfully receive the update.
   */
  public void update(Object data) throws NotPossibleException;
  
  /**
   * 
   * @effects Processes a pull query from an observer about the last update notification.
   *          Returns an <code>Object</code> containing the state data
   */
  public Object receiveQuery();
}
