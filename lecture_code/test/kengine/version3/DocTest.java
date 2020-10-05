package test.kengine.version3;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import kengine.version1.Doc;
import test.kengine.KEngineTestCase;
import test.kengine.version1.model.Document;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @overview DocTest performs a unit-test for <code>Doc</code>
 * 
 * @author dmle
 * 
 */
public class DocTest extends KEngineTestCase {
  protected static String[] docFolders;
  protected static String[] docTestTitleResults;
  protected static Object[] docTestBodyResults;

  protected final boolean VIEW_BODY = false;
  protected final boolean VIEW_WORDS = false;

  /**
   * 
   * @effects Initialises <code>docFolders</code> with test data and
   *          <code>docTestResults</code> with the corresponding test results
   */
  @BeforeClass
  public static void setUp() {
    // initialise some sample HTML pages
    // good
    docFolders = new String[] { //
    "doc0", // null body
        "doc1", // empty
        "doc2", // empty with a control tag
        "doc3", // english (no control tags)
        "doc4", // vietnamese text
        "doc5", // english (simple control tags)
        "doc6", // english (complex control tags)
        // invalid results
        //"doctestgoogle", //
    // "dochanufit", // verifiable not matches
    };

    docTestBodyResults = new Object[] { //
        null, //
        Boolean.TRUE, //
        Boolean.TRUE, //
        Boolean.TRUE, //
        Boolean.TRUE, //
        Boolean.TRUE, //
        Boolean.TRUE, //
        // invalid results
        //Boolean.FALSE, //
    };
  }

  /**
   * @effects Scans each test directory in <code>testFolders</code> and
   *          processes a test <code>Doc</code> and its text file equivalent
   *          <code>Document</code> stored in that directory. If failed to read
   *          a file throws <code>IOException</code>, else if the two files
   *          content do not match as specified in <code>testDocResults</code>
   *          then throws <code>AssertionError</code> with an error message
   *          displaying the differences.
   * 
   */
  @Test
  public void test() throws IOException, AssertionError {
    Doc doc = null;
    Document tdoc = null;
    Iterator wit1, wit2;
    String fname;

    Map fpairs = null;

    final String LINE = "====================================";
    final String SUBLINE = "...................................";

    // process each file and its source file to compare
    for (int i = 0; i < docFolders.length; i++) {
      File dir = new File(getTestDir() + File.separator + docFolders[i]);
      print(LINE);
      print("Doc dir: " + dir);

      File[] files = dir.listFiles();
      fpairs = new HashMap();
      Object[] docs;

      for (int j = 0; j < files.length; j++) {
        File f = files[j];
        fname = f.getName();
        String ffname = fname.substring(0, fname.lastIndexOf("."));
        docs = (Object[]) fpairs.get(ffname);
        if (docs == null) {
          docs = new Object[2];
          fpairs.put(ffname, docs);
        }

        if (!fname.endsWith(".dat")) {
          // parse HTML-based file
          print(SUBLINE);
          print("Parsing document " + fname);

          doc = parseFile(f);
          docs[0] = doc;
        } else {
          // parse data file
          print(SUBLINE);
          print("text document " + fname);
          tdoc = parseTextFile(f);
          docs[1] = tdoc;
        }
      }

      // display
      for (Iterator pairs = fpairs.keySet().iterator(); pairs.hasNext();) {
        String ffname = (String) pairs.next();
        docs = (Object[]) fpairs.get(ffname);
        doc = (Doc) docs[0];
        tdoc = (docs[1] != null) ? (Document) docs[1] : null;

        wit1 = doc.words();
        wit2 = (tdoc != null) ? tdoc.words() : null;

        print(SUBLINE);
        print("Displaying file " + ffname);
        print("title: " + doc.title());

        if (VIEW_BODY) {
          print("expected body: \n" + ((tdoc != null) ? tdoc.body() : null));
          print(SUBLINE);
          print("actual body: \n" + doc.body());
        }
        
        if (VIEW_WORDS) {
          if (wit2 != null) {
            print("expected words: ");
            while (wit2.hasNext()) {
              String w = (String) wit2.next();
              print(w);
            }
          }
          print(SUBLINE);
          if (wit1 != null) {
            print("actual words: ");
            while (wit1.hasNext()) {
              String w = (String) wit1.next();
              print(w);
            }
          }
        }

        // compare
        Vector notFound = null;
        Object result;
        if (wit1 != null && wit2 != null) {
          notFound = compare(doc, tdoc);
          result = new Boolean((notFound == null));
        } else if (wit1 == null && wit2 == null) {
          result = null;
        } else {
          result = Boolean.FALSE;
        }
        
        assertEquals("Test failed!", docTestBodyResults[i], result);
        
        if (notFound != null) {
          String debug = "";
          print(SUBLINE);          
          debug = "The following words are NOT expected by Doc.words:\n";
          for (Iterator nfit = notFound.iterator(); nfit.hasNext();) {
            debug += nfit.next();
          }
          print(debug);
        }
        
        print(SUBLINE);
      }
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

  private Document parseTextFile(File f) throws IOException {
    String d;
    Document doc;
    String line;
    BufferedReader fr = new BufferedReader(new FileReader(f));
    d = "";
    line = null;
    while ((line = fr.readLine()) != null) {
      d += line + "\n";
    }
    doc = new Document(d);
    return doc;
  }

  /**
   * Compare and return a vector of words that could not be found
   * 
   */
  private Vector compare(Doc doc, Document tdoc) {
    String w1, w2;
    Vector notFound = null;
    Iterator wit1 = doc.words();

    while (wit1.hasNext()) {
      w1 = (String) wit1.next();
      w1.trim();
      if (w1.equals(""))
        continue;

      boolean found = false;
      for (Iterator wit2 = tdoc.words(); wit2.hasNext();) {
        w2 = (String) wit2.next();
        if (w2.equals(""))
          continue;

        if (w1.equals(w2)) {
          // found it
          found = true;
          break;
        }
      }

      if (!found) {
        // System.err.println("Could not find word " + w1);
        if (notFound == null)
          notFound = new Vector();

        notFound.add(w1);
      }
    }

    return notFound;
  }
}
