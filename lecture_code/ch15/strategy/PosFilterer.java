package ch15.strategy;

/**
 * @overview Accepts all positive integers 
 * @author dmle
 *
 */
public class PosFilterer {
  public boolean check(Object x) throws ClassCastException {
    if (!(x instanceof Integer)) 
      throw new ClassCastException("PosFilterer.check: not an integer " + x);
     
    return ((Integer)x).intValue() > 0;  
  }
}
