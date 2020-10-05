package lect03_ch4;

import java.util.Vector;

import utils.NonPositiveException;
import utils.NotFoundException;
import utils.NotPossibleException;
import utils.NotSmallException;

public class Toolkit {
  
  /**
   * @effects <pre>
   *            if n is non-positive  
   *              throws NonPositiveException
   *            else 
   *              returns the factorial of n</pre> 
   */
  public static int fact(int n) throws NonPositiveException {
    if (n < 0)
      throw new NonPositiveException("fact(): input is not positive: " + n);
    
    return ch2.Num.fact(n);
  }
  
  /**
   * @requires  <tt>a</tt> is sorted
   * @effects   <pre>
   *              if a is null 
   *                throws NullPointerException
   *              else if x is not in a
   *                throws NotFoundException 
   *              else 
   *                returns i such that a[i] = x</pre>
   */
  public static int search(int[] a, int x) 
      throws NullPointerException, NotFoundException {
    // implementation that uses a for loop
    return -1;
  }
  
  /**
   * @requires  all elements of <tt>v</tt> are Integers
   * @modifies  <tt>v</tt>
   * @effects   <pre>
   *              if v is null 
   *                throws NullPointerException 
   *              else if v contains an element larger than x 
   *                throws NotSmallException 
   *              else 
   *                adds x to v 
   */
  public static void addMax(Vector v, Integer x) 
    throws NullPointerException, NotSmallException {
    //
  }
  
  /**
   * @effects <pre>
   *            if a is sorted in ascending order 
   *              returns true 
   *            else 
   *              returns false</pre>
   */
  public static boolean sorted(int[] a) {
    int prev = a[0];
    
    for (int i = 1; i < a.length; i++) {
      if (prev <= a[i])
        prev = a[i];
      else
        return false;
    }    
    return true;
  }  
  
  ///// Application procedures
  /**
   * @effects <pre>
   *            if n is non-positive  
   *              log error and return -1
   *            else 
   *              return the factorial of n</pre> 
   */
  public static int computeFact1(int n) {
    try {
      int f = fact(n);
      return f;
    } catch (NonPositiveException e) {
      // log
      System.err.println("Error: invalid input " + 
                                    e.getMessage());
      // more details
      // e.printStackTrace();
      
      // mask using -1
      return -1;
    }   
  }


  /**
   * @effects <pre>
   *            if n is non-positive  
   *              throw NotPossibleException
   *            else 
   *              return the factorial of n</pre> 
   */
  public static void computeFact2(int n) 
      throws NotPossibleException {
    try {
      int f = fact(n);
      System.out.println("fact("+n+"): " + f);
    } catch (NonPositiveException e) {
      throw new NotPossibleException("Could not compute fact("+n+")");
    }   
  }

}
