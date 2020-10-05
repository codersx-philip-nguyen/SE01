package test.ch2;

import junit.framework.TestCase;
import utils.Tool;
import ch3.Arrays;

public class SortingTest extends TestCase {
  private int[][] testVects;

  public void setUp() {

    testVects = new int[][] { //
    { 1 }, //
        { 1, 1 }, //
        { 1, 2 }, //
        { 1, 2, 3 }, //
        { 2, 1, 3 }, //
        { 1, 2, 3, 4, 5 }, //
        { 1, 2, 5, 3, 1 }, //
        { 4, 2, 5, 3, 1 }, //
        { 37, 2, 6, 4, 89, 8, 10, 12, 68, 45 }, //
        { 8, 10, 11, 12, 1, 2, 9 }, //
    };

  }

  public void testSort() {
    for (int i = 0; i < testVects.length; i++) {
      System.out.println("================================");
      System.out.println("array in the original order: ");
      Tool.printArray(testVects[i]);
      Arrays.sort(testVects[i]);
      System.out.println("in ascending order: ");
      Tool.printArray(testVects[i]);
    }
  }
}
