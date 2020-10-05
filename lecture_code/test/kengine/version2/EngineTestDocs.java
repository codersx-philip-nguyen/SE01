package test.kengine.version2;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import utils.DuplicateException;

/**
 * @overview A unit test for <code>Engine.addDocs</code> 
 * 
 * @version 1.0
 * @author dmle
 *
 */
public class EngineTestDocs extends EngineTest {
  
  private static String urlDuplicateTitle;
  
  @BeforeClass
  public static void setUp() {
    final String sep = File.separator;
    final String urlPrefix = "file://" + getTestDir() + sep + "sites" + sep;
    urlDuplicateTitle = urlPrefix + "hanu2";

    docResults = new Object[] { DuplicateException.class, };
  }

  /**
   * @requires <code>eng != null</code>
   * @effects Validates duplicate titles 
   */
  @Test public void testDocs() {
    if (eng == null)
      return;

    String url = urlDuplicateTitle;
    
    print("Test URL1: \n" + url);
    try {
      eng.addDocs(url);
    } catch (Exception e) {
      // make sure exception is expected
      assertEquals("Test failed", docResults[0], e.getClass());
      printError("Error: " + e.getMessage());
    }
  }

}
