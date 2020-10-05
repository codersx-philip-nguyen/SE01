package ch15.mediator;

/**
 * @overview Represents a user code that uses the observer pattern 
 * 
 * @author dmle
 *
 */
public class OMUser {
  public static void main(String[] args) {

    Mediator m = new Mediator();
    S s = new S(m);
    
    m.registerSubject(s);
    
    m.registerObserver(new T(m));
    m.registerObserver(new T(m));
    m.registerObserver(new T(m));
    
    // do something to s
    s.someMethod();
    
    // again
    s.someMethod();
    
    // check keep alive
    boolean isAlive = s.keepAlive();
    System.out.println(s+" keepAlive() " + isAlive);
  }
}
