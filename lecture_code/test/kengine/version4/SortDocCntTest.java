package test.kengine.version4;

import java.util.Arrays;
import java.util.Vector;

import kengine.version1.Doc;
import kengine.version4.DocCnt;
import kengine.version4.Sorting;
import test.kengine.KEngineTestCase;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @overview Unit test for <code>Sorting</code> that supports 
 *           a <code>Vector</code> of <code>Comparable</code> objects 
 * 
 * @author dmle
 *
 */
public class SortDocCntTest extends KEngineTestCase {
  private static Vector testVect;
  private static Object[] testResult;

  @BeforeClass
  public static void setUp() {
    DocCnt[] darr = new DocCnt[] { //
        new DocCnt(new Doc("<title>d1</title>d1"), 1), // 
        new DocCnt(new Doc("<title>d10</title>d10"), 10), //
        new DocCnt(new Doc("<title>d8</title>d8"), 8), //
        new DocCnt(new Doc("<title>d2</title>d2"), 2), //
        new DocCnt(new Doc("<title>d7</title>d7"), 7), //
        new DocCnt(new Doc("<title>d4</title>d4"), 4), //
        new DocCnt(new Doc("<title>d3</title>d3"), 3), //
        new DocCnt(new Doc("<title>d5</title>d5"), 5), //
        new DocCnt(new Doc("<title>d6</title>d6"), 5)//
    };

    testResult = new DocCnt[] { //
        new DocCnt(new Doc("<title>d10</title>d10"), 10), //
        new DocCnt(new Doc("<title>d8</title>d8"), 8), //
        new DocCnt(new Doc("<title>d7</title>d7"), 7), //
        new DocCnt(new Doc("<title>d5</title>d5"), 5), //
        new DocCnt(new Doc("<title>d6</title>d6"), 5),//
        new DocCnt(new Doc("<title>d4</title>d4"), 4), //
        new DocCnt(new Doc("<title>d3</title>d3"), 3), //
        new DocCnt(new Doc("<title>d2</title>d2"), 2), //
        new DocCnt(new Doc("<title>d1</title>d1"), 1), // 
    };

    testVect = new Vector();
    testVect.addAll(Arrays.asList(darr));    
  }

  /**
   * @modifies  <code>testVect</code>
   * @effects   Sort the elements of <code>testVect</code> in descending order.
   *            Throws <code>AssertionError</code> if sorting was done incorrectly
   */
  @Test
  public void testQuickSortDocCnt() {    
    System.out.println("================================");    
    System.out.println("DocCnts in the original order");
    System.out.println(testVect);
    Sorting.quickSort(testVect);
    
    assertArrayEquals("Test failed", testVect.toArray(), testResult);
    
    print("DocCnts in the descending order");
    print(testVect.toString());
  }
}
