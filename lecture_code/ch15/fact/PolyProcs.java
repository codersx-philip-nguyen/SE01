package ch15.fact;

import ch5.maths.NegativeExponentException;
import ch5.maths.Poly;

/**
 * @overview A factory class to create <code>Poly</code> objects.
 * 
 * @author dmle
 *
 */
public class PolyProcs {
  
  /**
   * @effects creates the zero polynomial
   */
  public static Poly makePoly() {
    return new Poly();
  }
  
  /**
   * @param n the exponent
   * @param c the coefficient
   * 
   * @effects If <code>n < 0</code> throws <code>NegativeExponentException</code>, 
   *          else returns the polynomial <code>cx^n</code>
   */
  public static Poly makePoly(int n, int c) throws NegativeExponentException {
    return new Poly(c, n);
  }
}
