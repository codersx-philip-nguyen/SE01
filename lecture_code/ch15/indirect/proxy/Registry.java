package ch15.indirect.proxy;

import java.util.Vector;

import utils.NotFoundException;

// implements the proxy pattern
public class Registry {
  
  /**
   * @effects If there is a mapping for <code>s</code> in <code>this</code> returns
   *          a vector that contains all the associated objects else throws
   *          <code>NotFoundException</code>
   */
  public Vector lookup(String s) throws NotFoundException {
    Vector v = null;
    // forward calls to the remote Registry object
    
    // return the result
    return v;
  }
}
