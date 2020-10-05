package ch15.mediator;

public interface Observer {
  /**
   * @effects Returns true if an update notification is successfully handled, 
   *          else returns false. 
   *          Note: <code>data</code> may be <code>null</code>  
   */
  public boolean receiveUpdate(Object data);
  
  /**
   * @effects Returns an <code>Object</code> containing the state data about
   *          the last update notification.  
   */
  public Object pull();
}
