package ch15.fact;

import ch5.maths.NegativeExponentException;

/**
 * @overview A specialised factory class to create <code>SparsePoly</code> objects.  
 * 
 * @author dmle
 *
 */
public class PolyFactory2 implements PolyFactory {
  /**
   * @effects creates the zero polynomial
   */
  public GenericPoly makePoly() {
    return new SparsePoly();
  }
  
  /**
   * @param n the exponent
   * @param c the coefficient
   * 
   * @effects If <code>n < 0</code> throws <code>NegativeExponentException</code>, 
   *          else returns the polynomial <code>cx^n</code>
   */
  public GenericPoly makePoly(int n, int c) throws NegativeExponentException {
    return new SparsePoly(c, n);
  }
}
