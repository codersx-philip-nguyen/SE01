package test.kengine.version4;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import kengine.version1.Doc;
import kengine.version4.Query;
import utils.NotPossibleException;

/**
 * @overview A unit test for <code>Query</code> query methods
 * 
 * @author dmle
 *
 */
public class EngineTestQueryMore extends EngineTest {
  
  private static String[][] keyPairs; 
  
  /**
   * @effects Initialises the test data for this version
   */
  @BeforeClass
  public static void setUp() {
    // invoke super method first?
    EngineTest.setUp();

    keyPairs = new String[][] {
        {"sinh", "viên"},
//        {"tư", "diễn"},
//        {"sinh", "diễn"},
//        {"diễn", "nội"},
//        {"diễn", "student"},
        // invalid
        {"sinh", "the"},
        {"sinh", "sinh"},
        {"sinh", "student"},
    };
    
    queryResults = new Object[] {
        // valid matches (by title)
        new String[] { "Hanoi University - Tin tức sinh viên", "Hanoi University - Tin tức &amp; Sự kiện (2) ", "Hanoi University - Tin tức &amp; Sự kiện"}, //
//        new String[] { "Hanoi University - Tin tức &amp; Sự kiện (2) ", "Hanoi University - Tin tức &amp; Sự kiện"}, //
//        new String[] { "Hanoi University - Tin tức sinh viên", "Hanoi University - Tin tức &amp; Sự kiện (2) ", "Hanoi University - Tin tức &amp; Sự kiện"}, //
//        new String[] { "Hanoi University - Tin tức sinh viên", "Hanoi University - Tin tức &amp; Sự kiện (2) ", "Hanoi University - Tin tức &amp; Sự kiện"}, //
        NotPossibleException.class, //
        NotPossibleException.class, //
        NotPossibleException.class, //
    };
  }

  /**
   * @requires  <code>eng != null</code>
   */
  @Test
  public void testQueryMore() {
    if (eng == null)
      throw new NotPossibleException(this.getClass().getSimpleName()
          + ".testQueryMore: no Engine instance is running!");

    // add some documents 
    addDocs();
    
    Query q;
    String w;
    
    // query keys
    String[] matches;
    Doc d;
    for (int i = 0; i < keyPairs.length; i++) {
      try {
        String[] pair = keyPairs[i];
        print("======================================================");
        print("query first: " + pair[0]);
        q = eng.queryFirst(pair[0]);
        print("query more: " + pair[1]);
        q = eng.queryMore(pair[1]);
        
        // make sure matches are correct
        matches = new String[q.size()];
        for (int di = 0; di < q.size(); di++) {
          d = q.fetch(di);
          matches[di] = d.title();
        }
        assertArrayEquals("Test failed", (String[])queryResults[i], matches);
        
        print(q.toString());
        
      } catch (Exception e) {
        // make sure exception is correct
        assertEquals("Test failed", queryResults[i], e.getClass());
        printError(e);
      }
    }
  }
}
