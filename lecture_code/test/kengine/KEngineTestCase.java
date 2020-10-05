package test.kengine;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import kengine.version1.Engine;
import utils.NotPossibleException;

/**
 * @overview A generic test case class to be the super-type of all the test case
 *           classes. It defines the common test attributes.
 * 
 * @author dmle
 * 
 */
public class KEngineTestCase {
  private static String testDir;

  public KEngineTestCase() {
    // display the test name
    print("\n[** Running test class: "+this.getClass().getSimpleName() +" **]\n");
  }

  public static String getTestDir() throws NotPossibleException {
    // read test directory from the properties file
    Properties props = new Properties();
    try {
      props.load(new FileInputStream(new File(
          Engine.class.getResource(".."
          + System.getProperty("file.separator") + "kengine.properties").toURI())));
      testDir = props.getProperty("test.dir");
    } catch (Exception e) {
      throw new NotPossibleException("Failed to read test directory from property file: " + e.getMessage());
    }
    
    return testDir;
  }

  public void printError(String mesg) {
    System.out.println(mesg);
  }

  public void printError(Throwable t) {
    System.out.println("ERROR:" + t);
  }

  public void print(String mesg) {
    print(mesg, false);
  }

  public void print(String mesg, boolean highlighted) {
    if (highlighted)
      System.out.println("[* " + mesg + " *]");
    else
      System.out.println(mesg);
  }
  
  /**
   * Overrides <code>Assert.assertArrayEquals</code> to support equality 
   * check when two input arrays are not sorted 
   * 
   */
  protected void assertArrayEquals(String mesg, Object[] expected, Object[] actual)
  throws AssertionError {
    // check length
    assertEquals(mesg, expected.length, actual.length);

    Object o1 = null, o2 = null;
    boolean equal = false;
    for (int i = 0; i < expected.length; i++) {
      o1 = expected[i];
      equal = false;
      for (int j = 0; j < actual.length; j++) {
        o2 = actual[j];
        if (o1.equals(o2)) {
          equal = true;
          break;
        }
      }

      assertEquals(mesg + " expected["+i+"]: "+o1+ ", but not found in actual\n", equal, true);
    }
  }

  /**
   * @effects Returns a <code>space</code> delimited string of the elements
   *          of <code>arr</code>.
   */
  protected String toString(Object[] arr) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < arr.length; i++) {
      sb.append(arr[i]+"");
      if (i < arr.length - 1)
        sb.append(" ");
    }
    
    return sb.toString();
  }
  
  /**
   * @effects Returns a new <code>Object[]</code> array formed by append the 
   *          elements of <code>toAppend</code> array to the end of the <code>arr</code>
   *          array 
   * 
   */
  protected static String[] appendArray(final String[] arr, final String[] toAppend) {
    String[] newArr = new String[arr.length + toAppend.length];
    
    System.arraycopy(arr, 0, newArr, 0, arr.length);
    System.arraycopy(toAppend, 0, newArr, arr.length, toAppend.length);
    return newArr;
  }
}
