package test.ch2;

import test.RootTest;
import ch2.Num;

public class NumTest extends RootTest {
  public void testGCD() {
    print("Num.gcd:");
    
    int n = 38, d = 105;
    print("n: " + n);
    print("d: " + d);
    print("gcd(n,d): " + Num.gcd(n, d));    
  }
}
