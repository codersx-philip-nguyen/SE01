package ch15.fact;


/**
 * @overview A program to test the factory object pattern. 
 * 
 * @author dmle
 *
 */
public class PolyApp {

  // a main module to create a suitable factory object and to pass it 
  // to other modules to use
  class M {
    public M() {
      PolyFactory f1 = new PolyFactory1();
      // use this line to change the factory class for creating factory objects
      //PolyFactory f2 = new PolyFactory2();

      P prog = new P(f1);
      prog.someMethod();   
      P2 anotherProg = new P2(f1);
      anotherProg.someOtherMethod();
    }
  }
  
  // a module using factory objects
  class P {
    private PolyFactory pf;
    public P(PolyFactory pf) {
      // initialise P with pf to use later
      this.pf = pf;
    }
    
    public GenericPoly someMethod() {
      // an example code that uses PolyFactory to create poly objects
      // to use
      return pf.makePoly();
    }
  }

  // another module using factory objects
  class P2 {
    private PolyFactory pf;
    public P2(PolyFactory pf) {
      // initialise P with pf to use later
      this.pf = pf;
    }
    
    public GenericPoly someOtherMethod() {
      // an example code that uses PolyFactory to create poly objects
      // to use
      return pf.makePoly(3, 5);
    }
  }
  
  public static void main(String[] args) {
    PolyApp app = new PolyApp();
    M main = app.new M();
  }
}
