package test.kengine.version1;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import org.junit.BeforeClass;
import org.junit.Test;

import kengine.version1.Comm;
import kengine.version1.Doc;
import test.kengine.KEngineTestCase;
import utils.NotPossibleException;

/**
 * @overview A unit test for <code>Comm.getDocs</code>
 * 
 * @version 1.0
 * @author dmle
 * 
 */
public class CommTest extends KEngineTestCase {
  protected static String[] urls;
  protected static Object[] urlResults;

  @BeforeClass
  public static void setup() {
    final String sep = File.separator;
    final String urlPrefix = "file://" + getTestDir() + sep + "sites" + sep;

    urls = new String[] {//
    // invalid urls
        null, //
        "", //
        urlPrefix + "void", //
        // valid url
        urlPrefix + "hanu", //
    };

    urlResults = new Object[] { // 
        NotPossibleException.class, //
        NotPossibleException.class, //
        NotPossibleException.class, //
        new String[] { //
        "Hanoi University - Tin tức sinh viên", //
            "Hanoi University - Tin tức &amp; Sự kiện (2) ", //
            "Hanoi University - Tin tức &amp; Sự kiện", //
        }//
    };
  }

  /**
   * @effects if successfully 
   *          retrieves all documents from a site <code>u</code> in  
   *          <code>this.urls</code> or fails with correct
   *          exceptions being thrown then prints <code>OK!</code>, otherwise 
   *          throws <code>AssertionError</code>
   */
  @Test
  public void testGetDocs() {
    // test retrieving documents
    String url;
    Vector docTitles; // the retrieved docs
    Doc d;
    for (int i = 0; i < urls.length; i++) {
      url = urls[i];
      print("Test URL: \n" + url);
      try {
        Iterator it = Comm.getDocs(url);
        docTitles = new Vector();
        while (it.hasNext()) {
          d = (Doc) it.next();
          docTitles.add(d.title());
        }

        // make sure test result is expected
        assertArrayEquals("Test failed", (String[]) urlResults[i],
            docTitles.toArray());
        print("OK!");
      } catch (Exception e) {
        // make sure exception is expected
        assertEquals("Test failed", urlResults[i], e.getClass());
        printError("Error: " + e.getMessage());
      }
    }
  }
}
