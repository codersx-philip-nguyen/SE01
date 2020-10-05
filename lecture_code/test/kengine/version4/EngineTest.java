package test.kengine.version4;

import org.junit.BeforeClass;

import kengine.version4.Engine;
import utils.NotPossibleException;

/**
 * @overview The master test class for <code>Engine</code> suitable for this
 *           version
 * 
 * @version 4.0
 * @author dmle
 */
public class EngineTest extends test.kengine.version1.EngineTest {
  protected static Engine eng; 

  protected static String[] keys;
  protected static Object[] queryResults;
  
  /**
   * @effects Initialises the test data for this version
   */
  @BeforeClass
  public static void setUp() {
    // invoke super method first?
    test.kengine.version1.EngineTest.setUp();
  }

  /**
   * @effects Returns a new <code>Engine</code> object of this version. Throws
   *          <code>NotPossibleException</code> if an error occured
   */ 
  protected Object testEngine() {
    // create a new Engine with some test documents and data
    try {
      eng = new Engine();
      
      print("new engine 4.0 created");

      String nonkeys = toString(eng.getNonkeys());
      print("...with non-keys: \n" + nonkeys);
      
      return eng;
    } catch (NotPossibleException e) {
      printError(e);
    }

    return null;
  }
  
  /**
   * @effects add <code>Doc</code> objects from a valid site to <code>eng</code>
   */
  protected void addDocs() {
    if (eng == null)
      throw new NotPossibleException(this.getClass().getSimpleName()
          + ".addDocs: no Engine instance is running!");

    // add documents to engine
    String url = docUrls[3];
    print("Test URL: \n" + url);
    eng.addDocs(url);    
  }
}
