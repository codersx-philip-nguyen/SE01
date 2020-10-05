package test.kengine.version4;

import java.util.Vector;

import kengine.version4.Sorting;
import test.kengine.KEngineTestCase;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @overview Unit test for <code>Sorting</code>
 * 
 * @author dmle
 *
 */
public class SortingTest extends KEngineTestCase {
  private static int[][] testVects;
  private static Integer[][] testAscResults;
  private static Integer[][] testDescResults;
  
  @BeforeClass
  public static void setUp() {
    
    testVects = new int[][] { //
        {1}, //
        {1,1}, //
        {1,2}, //
        {1,2,3}, //
        {2,1,3}, //
        {1,2,3,4,5}, //
        {1,2,5,3,1}, //
        {4,2,5,3,1}, //
        {37, 2, 6, 4, 89, 8, 10, 12, 68, 45}, //
    };

    testAscResults = new Integer[][] {
        {1}, //
        {1,1}, //
        {1,2}, //
        {1,2,3}, //
        {1,2,3}, //
        {1,2,3,4,5}, //
        {1,1,2,3,5}, //
        {1,2,3,4,5}, //
        {2, 4, 6, 8, 10, 12, 37, 45, 68, 89}, //        
    };

    testDescResults = new Integer[][] {
        {1}, //
        {1,1}, //
        {2,1}, //
        {3,2,1}, //
        {3,2,1}, //
        {5,4,3,2,1}, //
        {5,3,2,1,1}, //
        {5,4,3,2,1}, //
        {89,68,45,37,12,10,8,6,4,2}, //        
    };

  }
  
//  @Test
//  public void testQuickSort() {
//    Vector v; 
//    for (int i = 0; i < testVects.length; i++) {
//      v = new Vector();
//      for (int j = 0; j < testVects[i].length; j++) {
//        v.add(new Integer(testVects[i][j]));
//      }
//      
//      print("================================");
//      print("Vector in the original order: ");
//      print(v.toString());
//      
//      // sort v
//      Sorting.quickSortAsc(v);
//      
//      assertArrayEquals("Test failed", testAscResults[i], v.toArray());
//
//      print("Vector in ascending order: ");
//      print(v.toString());
//    }    
//  }
  
  @Test
  public void testQuickSortDesc() {
    Vector v; 
    for (int i = 0; i < testVects.length; i++) {
      v = new Vector();
      for (int j = 0; j < testVects[i].length; j++) {
        v.add(new Integer(testVects[i][j]));
      }
      
      print("================================");
      print("Vector in the original order: ");
      print(v.toString());
      // sort v
      Sorting.quickSort(v);
      Assert.assertArrayEquals("Test failed", testDescResults[i], v.toArray());    
      print("Vector in descending order: ");
      print(v.toString());
      
      // ascending
//      Sorting.quickSortAsc(v);
//      print("Vector in ascending order: ");
//      print(v.toString());
    }    
  }  
}
