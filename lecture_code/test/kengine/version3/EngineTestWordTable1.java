package test.kengine.version3;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.BeforeClass;
import org.junit.Test;

import kengine.version3.Engine;
import utils.NotPossibleException;
/**
 * @overview A unit test for <code>WordTable</code>
 * 
 * @version 3.0
 * @author dmle
 * 
 */
public class EngineTestWordTable1 extends EngineTest {

  private static String[] propFiles;
  private static Object[] results;
  private static String dataDir;
  
  @BeforeClass
  public static void setUp() throws NotPossibleException {
    EngineTest.setUp();

    final String sep = System.getProperty("file.separator");
    dataDir = "testWordTable"+sep+"1";
    
    try {
      propFiles = new String[] { //
      getFilePath("kengine1.properties"), //
          getFilePath("kengine2.properties"), //
          getFilePath("kengine3.properties"), //
          getFilePath("kengine4.properties"), //
          getFilePath("kengine5.properties"), //
          getFilePath("kengine6.properties"), //
      };      
    } catch (URISyntaxException e) {
      throw new NotPossibleException(
          "EngineTestWordTable.setUp: failed to initialise test data "
              + e.getMessage());
    }
    
    // test results
    results = new Object[] { //
        NotPossibleException.class, // 
        NotPossibleException.class, //
        new String[] {"a"}, //
        new String[] {"a"}, //
        new String[] {"a", "an", "this", "that", "these", "those", "the", "i", "am"}, //
        new String[] {"a", "an", "this", "that", "these", "those", "the", "i", "am"}, //
    };
  }

  private static String getFilePath(final String fileName)
      throws URISyntaxException {
    final String sep = System.getProperty("file.separator");    
    return getTestDir() + sep + dataDir + sep + fileName;
  }

  /**
   * @requires
   * @effects
   */
  @Test
  public void testWordTable() throws IOException {
    String propFile = null;

    Engine eng;
    for (int i = 0; i < propFiles.length; i++) {
      propFile = propFiles[i];
      try {
        eng = (Engine) testEngine(propFile);
        // make sure the word table content is correct
        assertArrayEquals("Test failed", (String[])results[i], eng.getNonkeys());
        print("OK!");
      } catch (Exception e) {
        // make sure the exception is correct
        assertEquals("Test failed", results[i], e.getClass());
        printError(e);
      }
    }    
  }
}
