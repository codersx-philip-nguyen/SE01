package test.kengine.version4;

import org.junit.BeforeClass;
import org.junit.Test;

import kengine.version1.Doc;
import kengine.version4.Query;
import utils.NotPossibleException;

/**
 * @overview A unit test for <code>Query.addDoc</code> method
 * 
 * @author dmle
 * 
 */
public class EngineTestQueryAddDoc extends EngineTest {

  private static String[] keyPair;
  private static Object[] results;

  private static String[] docDirs;

  /**
   * @effects Initialises the test data for this version
   */
  @BeforeClass
  public static void setUp() {
    // invoke super method first?
    EngineTest.setUp();

    keyPair = new String[] { "sinh", "viên" };

    final String sep = System.getProperty("file.separator");
    final String path = "file://" + getTestDir() + sep + "testQuery" + sep;

    docDirs = new String[] { //
    path + "none", //
        path + "partial", path + "complete" };

    final String[] matches = new String[] {
        "Hanoi University - Tin tức sinh viên", //
        "Hanoi University - Tin tức &amp; Sự kiện (2) ", //
        "Hanoi University - Tin tức &amp; Sự kiện" //
    };

    results = new Object[] {
        // new matches as result of adding each doc
        matches, // same
        matches, // same
        appendArray(matches, new String[] { 
            "test doc3", 
            "test doc4", 
            "test doc5" 
            }), };
  }

  /**
   * @requires <code>eng != null</code>
   */
  @Test
  public void testQueryAddDoc() {
    if (eng == null) {
      throw new NotPossibleException(this.getClass().getSimpleName()
          + ".testQueryAddDoc: no Engine instance is running!");
      // uncomment the following to run this NOT as part of a test suite
      //super.testEngine();
    }
    
    Query q;
    String docDir;
    String[] matches;
    Doc d;

    // now try adding a test document
    for (int i = 0; i < docDirs.length; i++) {
      print("-----------------------------------------------------");
      docDir = docDirs[i];
      
      // add some documents
      addDocs();

      // query keys first (this should work!)
      print("Initiating query <" + keyPair[0] + "," + keyPair[1]+ ">");
      q = eng.queryFirst(keyPair[0]);
      q = eng.queryMore(keyPair[1]);
      
      print("Initial matches: \n" + q.toString());

      // add the test doc to update the query (if any)
      print("Adding docs from: " + docDir);
      q = eng.addDocs(docDir);

      // make sure matches are correct
      matches = new String[q.size()];
      for (int di = 0; di < q.size(); di++) {
        d = q.fetch(di);
        matches[di] = d.title();
      }
    
      assertArrayEquals("Test failed", (String[]) results[i], matches);

      print("After addDoc\n" + q.toString());

      // reset engine
      eng.reset();
    }
  }
}
