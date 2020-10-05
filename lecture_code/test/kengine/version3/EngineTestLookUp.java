package test.kengine.version3;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import utils.NotPossibleException;

/**
 * @overview A unit test for <code>Engine.query</code> methods 
 * 
 * @version 3.0
 * @author dmle
 *
 */

public class EngineTestLookUp extends EngineTest {

  private static String[] keys;
  private static Object[] results;
  
  @BeforeClass public static void setUp() {
    EngineTest.setUp();
    
    // ///// words
    keys = new String[] { //
        // non words
        "aaa",//
        // non keys
        "the",//
        // valid keys
        "sinh", //
    };
    
    results = new Object[] { // 
        NotPossibleException.class, //
        NotPossibleException.class, //
        // valid matches
        Boolean.TRUE, //
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

    addDocs();
    
    // query test keys
    for (int i = 0; i < keys.length; i++) {
      print("Test word: " + keys[i]);
      try {       
        eng.queryFirst(keys[i]);
        // in this version, we can only use boolean T/F to check 
        // because we have not implemented Query yet
        assertEquals("Test failed", results[i], Boolean.TRUE);
        print("success!");
      } catch (Exception e) {
        // makr sure exception is correct
        assertEquals("Test failed", results[i], e.getClass());
        printError(e);
      }
    }
  }
}
