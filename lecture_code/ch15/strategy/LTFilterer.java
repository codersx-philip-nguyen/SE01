package ch15.strategy;

/**
 * @overview All integers less than some bound pass the check; the bound is an argument 
 *           to the constructor.
 *            
 * @author dmle
 *
 */
public class LTFilterer {
  private int b;
  
  public LTFilterer(int x) {
    b = x;
  }
  
  public boolean check(Object x) throws ClassCastException {
    if (!(x instanceof Integer)) 
      throw new ClassCastException("LTFilterer.check: not an integer " + x);
     
    return ((Integer)x).intValue() < b;  
  }
}
