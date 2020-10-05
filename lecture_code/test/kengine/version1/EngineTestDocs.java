package test.kengine.version1;

import static org.junit.Assert.assertEquals;
import kengine.version1.Query;

import org.junit.Test;

/**
 * @overview A unit test for <code>Engine.addDocs</code>
 * 
 * @version 1.0
 * @author dmle
 * 
 */
public class EngineTestDocs extends EngineTest {

  @Test
  /**
   * @requires <code>eng != null</code>
   * @effects Update <code>eng</code> with new <code>Doc</code> objects obtained
   *          from sites specified in <code>urls</code>. 
   *          Throws <code>NotPossibleException</code> if an error occured.
   */
  public void testDocs() {
    if (eng == null)
      return;

    // add documents to engine
    String url;
    for (int i = 0; i < docUrls.length; i++) {
      url = docUrls[i];
      print("Test URL: \n" + url);
      try {
        Query q = eng.addDocs(url);
        // make sure test result is expected
        assertEquals("Test failed", docResults[i], Boolean.TRUE);
      } catch (Exception e) {
        // make sure exception is expected
        assertEquals("Test failed", docResults[i], e.getClass());
        printError("Error: " + e.getMessage());
      }
    }
  }
}
