package lect04_ch6.usage;

import java.util.Iterator;
import java.util.NoSuchElementException;

import lect04_ch6.maths.Poly;
import utils.EmptyException;
import utils.Tool;

public class Comp {
  /**
   * @effects <pre>
   *            if p is null
   *              throws NullPointerException
   *            else 
   *              returns the poly obtained
   *              by differentiating p</pre>
   */
  public static Poly diff(Poly p) throws NullPointerException {
    if (p == null)
      throw new NullPointerException("Comp.diff");

    Poly q = new Poly();

    // add terms to q

    Iterator g = p.terms();
    while (g.hasNext()) {
      int exp = ((Integer) g.next()).intValue();
      if (exp == 0)
        continue;

      q = q.add(new Poly(exp * p.coeff(exp), exp - 1));
    }

    return q;
  }

  /**
   * @requires <tt>g</tt> contains only <tt>Integer</tt>s
   * @effects <pre>
   *            if g is null 
   *              throws NullPointerException
   *            else if  g is empty,
   *              throws EmptyException
   *            else 
   *              consumes all elements of g and returns the largest int in
   *              g</pre>
   */
  public static int max(Iterator g) throws EmptyException, NullPointerException {
    try {
      int m = (Integer) g.next();
      while (g.hasNext()) {
        int x = (Integer) g.next();
        if (m < x)
          m = x;
      }
      return m;
    } catch (NoSuchElementException e) {
      throw new EmptyException("Comp.max");
    }
  }

  public static void main(String[] args) {
    Poly p = new Poly();
    // p = p.add(new Poly(1, 0)).
    // add(new Poly(2, 1)).
    // add(new Poly(1, 2));
    // 3x^3 + x2 + 2x + 1
    p = p.add(new Poly(1, 0)).add(new Poly(2, 1)).add(new Poly(1, 2))
        .add(new Poly(3, 3));

    try {
      Tool.print("p: " + p);

      Poly q = Comp.diff(p);
      Tool.print("diff(p):");
      Tool.print(q);
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }
}
