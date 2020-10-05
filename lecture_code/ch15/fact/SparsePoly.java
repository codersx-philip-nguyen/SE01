package ch15.fact;

import java.util.Vector;

import ch5.maths.NegativeExponentException;

/**
 * @overview The same class <code>SparsePoly</code> that was implemented in Chapter 6.
 * 
 * @author dmle
 *
 */
class SparsePoly implements GenericPoly {
  // use a vector of pairs as terms
  private Vector trms;
  private int deg;

  /**
   * @effects Initialises this to be the zero polynomials
   */
  public SparsePoly() {
    trms = new Vector();
    trms.add(new Pair(0, 0));
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
  public SparsePoly(int c, int n) throws NegativeExponentException {
    if (n < 0)
      throw new NegativeExponentException(
          "SparsePoly(int,int) constructor: negative exponent " + n);

    trms = new Vector();
    if (c == 0) {
      trms.add(new Pair(0, 0));
      deg = 0;
    } else {
      trms = new Vector();
      // there is no need to initialise the coefficients of all the
      // exponents to zero
      // the last coeff is c
      trms.add(new Pair(c, n));
      deg = n;
    }
  }

  /**
   * A private constructor to create a proper <code>SparsePoly</code> degree
   * <code>n</code>.
   * 
   * @param terms
   *          an array of <code>Pair</code> objects representing terms
   * @param n
   *          the degree
   * @requires <code>terms</code> is not <code>null</code> and 
   *           <code>n</code> is one of the exponents in
   *           <code>terms</code>
   */
  private SparsePoly(Vector terms, int n) {
    trms = new Vector();
    for (int i = 0; i < terms.size(); i++) {
      trms.add(terms.get(i));
    }
    deg = n;
  }

  /**
   * @effects Returns the degree of <code>this</code>, i.e. the largest exponent
   *          with a non-zero coefficient. Rturns 0 if <code>this</code> is the
   *          zero <code>SparsePoly</code>.
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
    else {
      for (int i = 0; i < trms.size(); i++) {
        Pair p = (Pair) trms.get(i);
        if (p.getExp() == d)
          return p.getCoeff();
      }
    }

    return 0;
  }

  /**
   * 
   * @param q
   *          a polynomial to add
   * @effects if <code>q</code> is <code>null</code> throws
   *          <code>NullPointerException</code>; else returns the
   *          <code>SparsePoly</code> <code>this + q</code>
   */
  public SparsePoly add(SparsePoly q) throws NullPointerException {
    SparsePoly la, sm;

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
        if (this.coeff(k) + q.coeff(k) != 0)
          break;
        else
          newdeg--;
      }
    }

    // create the new Poly with newdeg and the terms
    // here we will not use the empty private constructor like in Poly
    // but a constructor that takes a vector of terms (this is safer)
    //Pair[] terms = new Pair[Math.min(sm.deg, newdeg) + 1];
    Vector terms = new Vector();
    int i;
    int sc;
    
    // could improve this to loop using the trms vector
    for (i = 0; i <= sm.deg && i <= newdeg; i++) {
      sc = sm.coeff(i) + la.coeff(i);
      if (sc != 0) // only add if sum of coefficients are non-zero
        terms.add(new Pair(sc, i));
    }

    for (int j = i; j <= newdeg; j++) {
      sc = la.coeff(j);
      if (sc != 0) {
        terms.add(new Pair(sc, j));
      }
    }

    SparsePoly r = new SparsePoly(terms, newdeg);

    return r;
  }

  /**
   * 
   * @param q
   *          a polynomial to multiply
   * @effects if <code>q</code> is <code>null</code> throws
   *          <code>NullPointerException</code>; else returns the
   *          <code>SparsePoly</code> <code>this * q</code>
   */
  public SparsePoly mul(SparsePoly q) throws NullPointerException {
    if (q == null)
      throw new NullPointerException("Poly.mul");

    // returns zero poly if one of the polies is zero
    if ((q.deg == 0 && q.coeff(0) == 0) || (deg == 0 && coeff(0) == 0))
      return new SparsePoly();

    // new term vector
    Vector terms = new Vector();

    // r.trms[deg + q.deg] = 0; // prepare to compute coeefs
    Pair pi, pj, p;
    for (int i = 0; i < trms.size(); i++) {
      pi = (Pair) trms.get(i);
      for (int j = 0; j < q.trms.size(); j++) {
        pj = (Pair) q.trms.get(j);
        
        p = getTerm(terms, pi.getExp() + pj.getExp());
        if (p == null) {
          p = new Pair(pi.getCoeff() * pj.getCoeff(), pi.getExp() + pj.getExp());
          terms.add(p);
        } else {
          p.setCoeff(p.getCoeff() + pi.getCoeff() * pj.getCoeff());
        }
        // r.trms[i + j] = r.trms[i + j] + trms[i] * q.trms[j];
      }
    }

    SparsePoly r = new SparsePoly(terms, deg + q.deg);
    return r;
  }

  /**
   * A method to search for a term with a given exponent in a term vector
   * 
   */
  private Pair getTerm(Vector terms, int exp) {
    Pair p;
    for (int i = 0; i < terms.size(); i++) {
      p = (Pair) terms.get(i);
      if (p.getExp() == exp) 
        return p;
    }
    
    return null;
  }
  
  /**
   * 
   * @param q
   *          a polynomial to subtract
   * @effects if <code>q</code> is <code>null</code> throws
   *          <code>NullPointerException</code>; else returns the
   *          <code>SparsePoly</code> <code>this - q</code>
   */
  public SparsePoly sub(SparsePoly q) throws NullPointerException {
    return add(q.minus());
  }

  /**
   * 
   * @effects Returns the <code>SparsePoly</code> <code>- this</code>
   */
  public SparsePoly minus() {
    Vector terms = new Vector();

    Pair p;
    for (int i = 0; i < trms.size(); i++) {
      p = (Pair) trms.get(i);
      terms.add(new Pair(-1 * p.getCoeff(), p.getExp()));
      // r.trms[i] = -trms[i];
    }

    SparsePoly r = new SparsePoly(terms, deg);
    return r;
  }

  // / additional methods
  /**
   * To compare to <code>SparsePoly</code> objects.
   */
  public boolean equals(SparsePoly q) {
    if (q == null || deg != q.deg)
      return false;

    for (int i = 0; i <= deg; i++) {
      if (coeff(i) != q.coeff(i))
        return false;
    }

    return true;
  }

  /**
   * Overrides the default <code>equals</code> method.
   */
  public boolean equals(Object z) {
    if (!(z instanceof SparsePoly))
      return false;

    return equals((SparsePoly) z);
  }

  /**
   * Overrides the default <code>toString</code> method.
   */
  public String toString() {
    if (deg == 0 && coeff(0) == 0) {
      return "0";
    }

    String s = "";// + coeff(0);
    Pair p;
    int c, e;
    String sign = null;
    for (int i = 0; i < trms.size(); i++) {
      p = (Pair) trms.get(i);
      c = p.getCoeff();
      e = p.getExp();
      if (c < 0) 
        sign = "-";
      else if (trms.size() > 1 && i > 0)
        sign = "+";
      else 
        sign = "";
        
      if (e == 0) {
        s = s + sign + Math.abs(c);
      } else if (e == 1) {
        s = s + sign + Math.abs(c) + "x";
      } else {
        s = s + sign + Math.abs(c) + "x^" + e;
      }
    }

    return s;
  }

  /**
   * The rep invariant is:<br>
   * <code>
   *            c.trms != null && c.trms.size > 0 && c.deg = max(c.trms).exp && 
   *            c.deg > 0 => term(c.trms, deg).coeff != 0
   *            </code><br>
   * where: <code>max(trms)</code> returns the term <code>t</code> in
   * <code>trms</code> with the largest exponent, <br>
   * <code>term(trms,d)</code> returns a term <code>t</code> in
   * <code>trms</code>, whose exponent is <code>d</code> * @return
   */
  boolean repOK() {
    if (trms == null || trms.size() == 0)
      return false;
    
    Pair mt = null, dt = null;
    Pair p;
    for (int i = 0; i < trms.size(); i++) {
      p = (Pair) trms.get(i);
      if (mt == null || mt.getExp() < p.getExp()) {
        mt = p;
      }
      
      if (p.getExp() == deg) {
        dt = p;
      }
    }
    
    if (deg != mt.getExp())
      return false;
      
    if (deg > 0 && dt.getCoeff() == 0)
      return false;
    
    return true;
  }
}

/**
 * A record-type class that represents a pair <code>(coeff,exp)</code>.
 * 
 * @author dmle
 * 
 */
class Pair {
  private int coeff;
  private int exp;

  public Pair(int coeff, int exp) {
    this.coeff = coeff;
    this.exp = exp;
  }

  void setCoeff(int coeff) {
    this.coeff = coeff;
  }

  int getCoeff() {
    return coeff;
  }

  int getExp() {
    return exp;
  }
}