package test.kengine.version2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import kengine.version1.Doc;
import kengine.version1.Query;
import utils.NotPossibleException;

/**
 * @overview A unit test for <code>Engine.findDoc</code>
 * @author dmle
 * 
 */
public class EngineTestFind extends EngineTest {
  
  /**
   * @requires <code>eng != null</code> and <code>eng</code> contains all <code>Doc</code>
   *            objects necessary to test
   * 
   */
  @Test
  public void testFind() {
    if (eng == null)
      return;

    // add some valid documents
    addDocs();
    
    Doc d;

    // find good docs
    boolean result = false;
    for (int i = 0; i < titles.length; i++) {
      print("title: " + titles[i]);
      try {
        d = eng.findDoc(titles[i]);
        result = true;
        print("Found!");
      } catch (NotPossibleException e) {
        result = false;
        print("Not found!");
      } catch (Exception e) {
        assertFalse("Unexpected error occured: " + e.getMessage(), false);
      }

      // check result against test result
      assertEquals("Test failed for " + titles[i], titleResults[i], result);
    }
  }
  

  /**
   * @requires <code>eng != null</code>
   * @effects If a site <code>u</code> taken from <code>urls</code> is valid then 
   *          update <code>eng</code> with <code>Doc</code> objects obtained
   *          from <code>u</code>, else throws <code>AssertionError</code>.
   *          
   */
  protected void addDocs() {
    if (eng == null)
      return;

    String url = docUrls[3];
    print("Test URL: \n" + url);
    Query q = eng.addDocs(url);
    assertTrue("Test failed", (q != null));
  }
}
