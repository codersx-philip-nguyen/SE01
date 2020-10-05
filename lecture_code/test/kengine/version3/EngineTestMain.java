package test.kengine.version3;

import org.junit.Test;

/**
 * @overview A unit test for initialising <code>Engine</code> in this version
 * @version  3.0
 * @author dmle
 *
 */
public class EngineTestMain extends EngineTest {
  
  /**
   * Initialises 
   */
  @Test
  public void testEngineMain() {
    String propFile = null; //"kengine.properties";
    super.testEngine(propFile);
  }
}
