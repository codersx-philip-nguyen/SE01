package test;

import junit.framework.TestCase;

public class RootTest extends TestCase {
  long runTime;
  
  public void print(String msg) {
    System.out.println(msg);
  }
  
  protected void startTimeNano() {    
    runTime = System.nanoTime();
  }

  protected void startTime() {    
    runTime = System.currentTimeMillis();
  }

  protected long stopTime() {
    runTime = System.currentTimeMillis() - runTime;
    return runTime;// / 1000D;
  }

}
