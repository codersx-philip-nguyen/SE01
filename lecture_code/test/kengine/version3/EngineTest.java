package test.kengine.version3;

import kengine.version3.Engine;

import org.junit.BeforeClass;

/**
 * @overview The master test class for <code>Engine</code> suitable for this
 *           version
 * 
 * @version 3.0
 * @author dmle
 */
public class EngineTest extends test.kengine.version1.EngineTest {
  protected static Engine eng;

  /**
   * @effects Initialises the test data for this version
   */
  @BeforeClass
  public static void setUp() {
    // invoke super method first?
    test.kengine.version1.EngineTest.setUp();
  }

//  /**
//   * @effects Returns a new <code>Engine</code> object of this version using the
//   *          default configuration file
//   */
//  protected Object testEngine() {
//    return testEngine(null);
//  }
//
//  /**
//   * @effects Returns a new <code>Engine</code> object of this version and
//   *          populate this engine with some documents. Throws
//   *          <code>NotPossibleException</code> if an error occured
//   */
//  protected Object testEngine(String propFile) {
//    newEngine(propFile);
//
//    // add some documents for testing
//    addDocs();
//
//    return eng;
//  }

  /**
   * @effects Returns a new <code>Engine</code> object of this version and
   *          populate this engine with some documents. Throws
   *          <code>NotPossibleException</code> if an error occured
   */
  protected Object testEngine(String propFile) {
    // create a new Engine with some test documents and data
    if (propFile != null) {
      eng = new Engine(propFile);
    } else {
      eng = new Engine();
    }

    print("new engine 3.0 created (property file: " + propFile + ")");

    String nonkeys = toString(eng.getNonkeys());
    print("...with non-keys: \n" + nonkeys);

    return eng;
  }

  /**
   * @requires <code>eng != null</code>
   * @effects Update <code>eng</code> with new <code>Doc</code> objects obtained
   *          from a valid site specified in <code>urls</code>.
   */
  protected void addDocs() {
    // add documents to engine
    String url = docUrls[3];
    print("Test URL: \n" + url);
    eng.addDocs(url);
  }
}
