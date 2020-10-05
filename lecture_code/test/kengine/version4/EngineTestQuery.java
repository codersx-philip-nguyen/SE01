package test.kengine.version4;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import kengine.version1.Doc;
import kengine.version4.Query;
import utils.NotPossibleException;

/**
 * @overview A unit test for <code>Engine.query</code>
 * 
 * @author dmle
 *
 */
public class EngineTestQuery extends EngineTest {
  
  /**
   * @effects Initialises the test data for this version
   */
  @BeforeClass
  public static void setUp() {
    // invoke super method first?
    EngineTest.setUp();
    
    // ///// words
    keys = new String[] { //
        // non words
        "student",//
        // non keys
        "the",//
        // valid keys
        "sinh", //
//        "nội", //
//        "tư", //
//        "diễn", //
    };
    
    queryResults = new Object[] {
        NotPossibleException.class, //
        NotPossibleException.class, //
        // valid matches (by title)
        new String[] { "Hanoi University - Tin tức sinh viên", "Hanoi University - Tin tức &amp; Sự kiện (2) ", "Hanoi University - Tin tức &amp; Sự kiện"}, //
//        new String[] { "Hanoi University - Tin tức sinh viên", "Hanoi University - Tin tức &amp; Sự kiện (2) ", "Hanoi University - Tin tức &amp; Sự kiện"}, //
//        new String[] { "Hanoi University - Tin tức &amp; Sự kiện (2) ", "Hanoi University - Tin tức &amp; Sự kiện"}, //
//        new String[] { "Hanoi University - Tin tức sinh viên", "Hanoi University - Tin tức &amp; Sự kiện (2) ", "Hanoi University - Tin tức &amp; Sự kiện"}, //
    };
  }

  /**
   * @requires  <code>eng != null</code>
   */
  @Test
  public void testQuery() {
    if (eng == null)
      throw new NotPossibleException(this.getClass().getSimpleName()
          + ".testQuery: no Engine instance is running!");

    // add some documents 
    addDocs();
    
    Query q;
    String[] matches;
    Doc d;
    
    // query test keys
    for (int i = 0; i < keys.length; i++) {
      print("Test word: " + keys[i]);
      try {       
        q = eng.queryFirst(keys[i]);
        // make sure that matching documents are correct
        matches = new String[q.size()];
        for (int di = 0; di < q.size(); di++) {
          d = q.fetch(di);
          matches[di] = d.title();
        }
        assertArrayEquals("Test failed", (String[]) queryResults[i], matches);

        print(q.toString());

      } catch (Exception e) {
        // makr sure exception is correct
        assertEquals("Test failed", queryResults[i], e.getClass());
        printError(e);
      }
    }
  }
}
