package ch15.fact;

import ch5.maths.NegativeExponentException;
import ch5.maths.Poly;

/**
 * @overview A factory interface for creating factory objects. 
 * 
 * @author dmle
 *
 */
public interface PolyFactory {
  
  /**
   * @effects creates the zero polynomial
   */
  public GenericPoly makePoly();
  
  /**
   * @param n the exponent
   * @param c the coefficient
   * 
   * @effects If <code>n < 0</code> throws <code>NegativeExponentException</code>, 
   *          else returns the polynomial <code>cx^n</code>
   */
  public GenericPoly makePoly(int n, int c) throws NegativeExponentException;
}
