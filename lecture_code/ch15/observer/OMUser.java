package ch15.observer;

/**
 * @overview Represents a user code that uses the observer pattern 
 * 
 * @author dmle
 *
 */
public class OMUser {
  public static void main(String[] args) {
    S s = new S();
    
    // registers some observers of s
    s.registerObserver(new T(s));
    s.registerObserver(new T(s));
    s.registerObserver(new T(s));
    
    // do something to s
    s.someMethod();
    
    // again
    s.someMethod();
    
    // check keep alive
    boolean isAlive = s.keepAlive();
    System.out.println(s+" keepAlive() " + isAlive);
  }
}
