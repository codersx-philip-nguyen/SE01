package test.kengine.version2;

import org.junit.BeforeClass;

import kengine.version2.Engine;
import utils.NotPossibleException;

/**
 * @overview The master test class for <code>Engine</code> suitable for this
 *           version
 * 
 * @version 2.0
 * @author dmle
 */
public class EngineTest extends test.kengine.version1.EngineTest {
  protected static Engine eng;

  /**
   * @effects Initialises the test data
   */
  @BeforeClass
  public static void setUp() {
    test.kengine.version1.EngineTest.setUp();
  }
  
  /**
   * @effects Returns a new <code>Engine</code> object of this version. Throws
   *          <code>NotPossibleException</code> if an error occured
   */
  protected Object testEngine() throws NotPossibleException {
    // create a new Engine with some test documents and data
    eng = new Engine();

    print("new engine 2.0 created");

    String nonkeys = eng.getNonkeys();
    print("...with non-keys: \n" + nonkeys);

    return eng;
  }
}
