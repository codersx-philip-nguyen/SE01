package test.kengine.version3;

import org.junit.BeforeClass;
import org.junit.Test;

import utils.NotPossibleException;

/**
 * @overview A unit test for <code>WordTable</code>
 * 
 * @version 3.0
 * @author dmle
 * 
 */
public class EngineTestWordTable2 extends EngineTest {

  private static Object[] results;
  private static String[] docDirs;

  @BeforeClass
  public static void setUp() throws NotPossibleException {
    EngineTest.setUp();

    final String sep = System.getProperty("file.separator");
    final String dataDir = "file://" + getTestDir() + sep + "testWordTable" + sep + "2"
        + sep;

    docDirs = new String[] { //
    dataDir + "empty", //
        dataDir + "noninteresting", //
        dataDir + "distinct", //
        dataDir + "duplicates", //
    };

    // test results
    final String[] defNonKeys = new String[] { "a", "an", "this", "that",
        "these", "those", "the", "i", "am" };

    results = new String[][] { //
        defNonKeys, //
        defNonKeys, //
        (String[]) appendArray(defNonKeys, new String[] { "is", "test", "page", "for",
            "doc", "parser" }), //
        (String[]) appendArray(defNonKeys, new String[] { "test", "page", "to", "doc",
            "parser" }), //
    };
  }

  /**
   * @requires
   * @effects
   */
  @Test
  public void testWordTable() throws NotPossibleException {
    if (eng == null)
      throw new NotPossibleException(this.getClass().getSimpleName()
          + ".testWordTable: no Engine instance is running!");

    String docDir = null;

    for (int i = 0; i < docDirs.length; i++) {
      docDir = docDirs[i];
      print("Test url: " + docDir);
      eng.addDocs(docDir);
      // make sure the word table content is correct
      assertArrayEquals("Test failed", (String[]) results[i], eng.getWords());
      print("OK!");
      //print(eng.getWordTableAsString());
      // reset engine
      eng.reset();
    }
  }
}
