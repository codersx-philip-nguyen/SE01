package lect04_ch6.maths;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ch5.maths.NegativeExponentException;

/**
 * @overview <code>Poly</code>s are immutable polynomials with integer
 *           coefficients. A typical <code>Poly</code> is
 *           <code>c0 + c1.x + ... + cn.xn</code>. It is <code>cloneable</code>
 *           in the sense that all the cloned objects of a <code>Poly</code>
 *           have the same state.
 * @version 2.0 implements an iterator method <code>terms</code>
 * 
 * @author dmle
 * 
 */
public class Poly implements Cloneable {
  private int[] trms;
  private int deg;

  /**
   * @effects Initialises this to be the zero polynomials
   */
  public Poly() {
    trms = new int[1];
    deg = 0;
  }

  /**
   * 
   * @param c
   *          a coefficient
   * @param n
   *          the degree
   * @effects If <code>n < 0</code> throws
   *          <code>NegativeExponentException</code> else initialises this to be
   *          <code>c.x^n</code>.
   */
  public Poly(int c, int n) throws NegativeExponentException {
    if (n < 0)
      throw new NegativeExponentException(
          "Poly(int,int) constructor: negative exponent " + n);

    if (c == 0) {
      trms = new int[1];
      deg = 0;
    } else {
      trms = new int[n + 1];
      // coeffs from x^0 to x^(n-1) are zero
      for (int i = 0; i < n; i++) {
        trms[i] = 0;
      }
      // the last coeff is c
      trms[n] = c;
      deg = n;
    }
  }

  /**
   * A private constructor to create a zero <code>Poly</code> degree
   * <code>n</code>.
   * 
   * @param n
   */
  private Poly(int n) {
    trms = new int[n + 1];
    deg = n;
  }

  /**
   * @effects Returns the degree of <code>this</code>, i.e. the largest exponent
   *          with a non-zero coefficient. Rturns 0 if <code>this</code> is the
   *          zero <code>Poly</code>.
   */
  public int degree() {
    return deg;
  }

  /**
   * @param d
   *          an exponent
   * @effects Returns the coefficient of the term of <code>this</code> whose
   *          exponent is <code>d</code>
   */
  public int coeff(int d) {
    if (d < 0 || d > deg)
      return 0;
    else
      return trms[d];
  }

  /**
   * 
   * @param q
   *          a polynomial to add
   * @effects if <code>q</code> is <code>null</code> throws
   *          <code>NullPointerException</code>; else returns the
   *          <code>Poly</code> <code>this + q</code>
   */
  public Poly add(Poly q) throws NullPointerException {
    Poly la, sm;

    if (deg > q.deg) {
      la = this;
      sm = q;
    } else {
      la = q;
      sm = this;
    }

    int newdeg = la.deg; // uses the larger degree for the new Poly

    // if the two polys have the same degree then check if any of the higher
    // order coefficients are summed to zero, if so decrement the
    // newdeg by the same number
    if (deg == q.deg) { // unless there are trailing zeros
      for (int k = deg; k > 0; k--) {
        if (trms[k] + q.trms[k] != 0)
          break;
        else
          newdeg--;
      }
    }

    // create the new Poly
    Poly r = new Poly(newdeg);
    int i;
    for (i = 0; i <= sm.deg && i <= newdeg; i++) {
      r.trms[i] = sm.trms[i] + la.trms[i];
    }

    for (int j = i; j <= newdeg; j++) {
      r.trms[j] = la.trms[j];
    }

    return r;
  }

  /**
   * 
   * @param q
   *          a polynomial to multiply
   * @effects if <code>q</code> is <code>null</code> throws
   *          <code>NullPointerException</code>; else returns the
   *          <code>Poly</code> <code>this * q</code>
   */
  public Poly mul(Poly q) throws NullPointerException {
    if (q == null)
      throw new NullPointerException("Poly.mul");

    // returns zero poly if one of the polies is zero
    if ((q.deg == 0 && q.trms[0] == 0) || (deg == 0 && trms[0] == 0))
      return new Poly();

    Poly r = new Poly(deg + q.deg);

    r.trms[deg + q.deg] = 0; // prepare to compute coeefs

    for (int i = 0; i <= deg; i++) {
      for (int j = 0; j <= q.deg; j++) {
        r.trms[i + j] = r.trms[i + j] + trms[i] * q.trms[j];
      }
    }

    return r;
  }

  /**
   * 
   * @param q
   *          a polynomial to subtract
   * @effects if <code>q</code> is <code>null</code> throws
   *          <code>NullPointerException</code>; else returns the
   *          <code>Poly</code> <code>this - q</code>
   */
  public Poly sub(Poly q) throws NullPointerException {
    return add(q.minus());
  }

  /**
   * 
   * @effects Returns the <code>Poly</code> <code>- this</code>
   */
  public Poly minus() {
    Poly r = new Poly(deg);
    for (int i = 0; i <= deg; i++) {
      r.trms[i] = -trms[i];
    }

    return r;
  }

  // / additional methods
  /**
   * To compare to <code>Poly</code> objects.
   */
  public boolean equals(Poly q) {
    if (q == null || deg != q.deg)
      return false;

    for (int i = 0; i <= deg; i++) {
      if (trms[i] != q.trms[i])
        return false;
    }

    return true;
  }

  /**
   * Overrides the default <code>equals</code> method.
   */
  public boolean equals(Object z) {
    if (!(z instanceof Poly))
      return false;

    return equals((Poly) z);
  }

  /**
   * Overrides the default <code>toString</code> method.
   */
  public String toString() {
    if (deg == 0 && trms[0] == 0) {
      return "0";
    }

    String s = "" + trms[0];

    int c;
    String sign;
    for (int i = 1; i <= deg; i++) {
      c = trms[i];
      sign = (c < 0) ? "-" : "+";
      if (i == 1) {
        s = s + sign + Math.abs(c) + "x";
      } else {
        s = s + sign + Math.abs(c) + "x^" + i;
      }
    }

    return s;
  }

  /**
   * 
   * @effects Returns a generator that will produce exponents of nonzero terms
   *          of <code>this</code> as <code>Integers</code> up to the degree, in
   *          order of increasing exponent.
   * @version 2.0 implements an iterator method
   */
  public Iterator terms() {
    return new PolyGen(this);
  }

  /**
   * @overview An inner class that implements the <code>Iterator</code> interface.
   * 
   * <p> The abstraction function is:
   * <code>
   * AF(c) = [x1,...,xn]</code> such that
   * each <code>xi</code> is an Integer and every 
   * index <code>i >= 1</code> of a non-zero element of <code>c.p.trms</code> 
   * is in the sequence and no other elements are in the sequence and
   * <code>xi > xj for all i > j >= 1</code>
   * 
   * <p> The rep invariant is:
   * <code>
   *  c.p != null && (0 <= c.n <= c.p.deg)
   * </code>
   * 
   * @author dmle
   *
   */
  private static class PolyGen implements Iterator {
    private Poly p;
    private int n; // next degree

    /**
     * @requires <code>p</code> is not <code>null</code>
     */
    public PolyGen(Poly p) {
      this.p = p;
      n = 0;
    }

    public boolean hasNext() {
      return (n <= p.deg);
    }

    public Object next() throws NoSuchElementException {
      for (int i = n; i <= p.deg; i++) {
        if (p.trms[i] != 0) {
          n = i + 1;
          return new Integer(i);
        }
      }
      
      throw new NoSuchElementException("Poly.terms");
    }
    
    public void remove() {
      // not implement yet
    }
  } // end PolyGen
}
