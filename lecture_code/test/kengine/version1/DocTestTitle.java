package test.kengine.version1;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import kengine.version1.Doc;
import test.kengine.KEngineTestCase;

/**
 * @overview Performs a unit-test for <code>Doc</code>
 * 
 * @author dmle
 * 
 */
public class DocTestTitle extends KEngineTestCase {
  protected static String docFolder;
  protected static Map docTestTitleResults;

  /**
   * 
   * @effects Initialises <code>docFolders</code> with test data and
   *          <code>docTestResults</code> with the corresponding test results
   */
  @BeforeClass
  public static void setUp() {
    // initialise some sample HTML pages
    // good
    docFolder = "testTitles";

    docTestTitleResults = new HashMap(); 
    docTestTitleResults.put("docNone.html", null);
    docTestTitleResults.put("docEmpty.html", "");
    docTestTitleResults.put("doc1.html", "a");
    docTestTitleResults.put("doc.html", " welcome to my page ");
  }

  /**
   * @effects Scans each test directory in <code>testFolders</code> and
   *          processes a test <code>Doc</code> and its text file equivalent
   *          <code>Document</code> stored in that directory. If failed to read
   *          a file throws <code>IOException</code>, else if the test
   *          <code>Doc</code>'s title is not correct throws
   *          <code>AssertionError</code>.
   * 
   */
  @Test
  public void testTitle() throws IOException, AssertionError {
    Doc doc = null;
    String fname;

    final String LINE = "====================================";
    final String SUBLINE = "...................................";

    // process each file and its source file to compare
    File dir = new File(getTestDir() + File.separator + docFolder);
    print(LINE);
    print("Doc dir: " + dir);

    File[] files = dir.listFiles();

    for (int j = 0; j < files.length; j++) {
      File f = files[j];
      fname = f.getName();
      String ffname = fname.substring(0, fname.lastIndexOf("."));

      // parse HTML-based file
      print(SUBLINE);
      print("Parsing document " + fname);

      doc = parseFile(f);
      print(SUBLINE);
      print("Displaying file " + ffname);
      print("title: " + doc.title());
      assertEquals("Incorrect title result", // 
          (String) docTestTitleResults.get(fname), //
          doc.title());
    }
  }

  private Doc parseFile(File f) throws IOException {
    String d;
    Doc doc;
    String line;
    BufferedReader fr = new BufferedReader(new FileReader(f));
    d = "";
    line = null;
    while ((line = fr.readLine()) != null) {
      d += line + "\n";
    }
    doc = new Doc(d);
    return doc;
  }
}
