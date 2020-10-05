package ch15.strategy;

public interface Filterer {
  /**
   * 
   * @param x an object to check
   * @effects If <code>x</code> cannot be checked throws <code>ClassCastException</code>;
   *          if <code>x</code> passes the check returns true else returns false
   */
  public boolean check(Object x) throws ClassCastException;
}
