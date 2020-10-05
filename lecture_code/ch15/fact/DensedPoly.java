package ch15.fact;

import ch5.maths.Poly;

public class DensedPoly extends Poly implements GenericPoly {
  // inherits all from the super type Poly
  public DensedPoly() { super(); }
  public DensedPoly(int c, int n) { super(c, n); }
}
