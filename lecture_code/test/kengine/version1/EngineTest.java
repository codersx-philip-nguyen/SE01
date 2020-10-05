package test.kengine.version1;

import java.io.File;

import org.junit.BeforeClass;

import kengine.version1.Engine;
import test.kengine.KEngineTestCase;
import utils.NotPossibleException;

/**
 * @overview The master test case of the search engine.
 * 
 * @version 1.0
 * @author dmle
 * 
 */
public class EngineTest extends KEngineTestCase {
  protected static String[] docUrls;
  protected static Object[] docResults;
  
  protected static String[] titles;
  protected static boolean[] titleResults;
  
  protected static Engine eng; // the engine object used by all the tests

  public EngineTest() {
    super();
  }

  /**
   * @effects Initialises the test data
   */
  @BeforeClass
  public static void setUp() {
    // some sites
    final String sep = File.separator;
    final String urlPrefix = "file://" + getTestDir() + sep + "sites" + sep;

    docUrls = new String[] {//
        // invalid urls
        null, //
        "", //
        urlPrefix + "void", // 
        // valid url
        urlPrefix + "hanu", //
        // duplicate url
        urlPrefix + "hanu", //
    };
    
    docResults = new Object[] {
        NotPossibleException.class, //
        NotPossibleException.class, //
        NotPossibleException.class, //
        Boolean.TRUE, //
        NotPossibleException.class, //
    };
    
    // ////// document titles
    // titles data
    titles = new String[] { 
        // invalid titles
        null, 
        "",
        // junk titles
        "title aaa",//
        //"title bbb",//
        //"Facult? of IT - HANU :: Portal ",//
        // good titles
        "Hanoi University - Tin tức sinh viên", //
        //"Hanoi University - Tin tức &amp; Sự kiện", //
    };
    
    titleResults = new boolean[] {//
        false,// 
        false, // 
        false, // 
        //false, // 
        //false, //
        true, // 
        //true, // 
        };    
  }

  /**
   * @effects Returns an <code>Engine</code> object of this version. 
   *          Throws <code>NotPossibleException</code> if an error occured
   */
  // TODO: to test Engine() failures we need to add a new constructor method 
  // that receives a configuration file that has a setting for the directory 
  // path of the nk.dat file (loaded by WordTable())
  // This feature is not currently supported by the KEngine design.
  protected Object testEngine() throws NotPossibleException {
    // create a new Engine with some test documents and data
    eng = new Engine();

    print("Engine v1.0 created");

    String nonkeys = eng.getNonkeys();
    print("...with non-keys: \n" + nonkeys);

    return eng;
  }
}
