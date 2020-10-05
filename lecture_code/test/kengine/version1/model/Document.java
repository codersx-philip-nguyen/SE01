package test.kengine.version1.model;

import java.util.Iterator;
import java.util.Vector;

/**
 * @overview Document represents a general document containing a sequence of words
 *  
 * @author dmle
 *
 */
public class Document {
  private String d; // the document content
  private Vector docWords; // the sequence of document words

  /**
   * Constructor method
   * 
   * @param d A string that contains the document content 
   * @effects makes <code>this</code> be the <code>Document</code> 
   *          corresponding to <code>d</code>
   *  
   */
  public Document(String d) {
    this.d = d;
  }

  /**
   * A getter method
   * 
   * @effects returns the document content
   */
  public String body() {
    return d;
  }
  
  /**
   * @effects If <code>this.d == null || d.length() = 0</code> returns <code>null</code>,
   *          else returns a generator that will yield all the words in the document
   *          as strings in the order they appear in the text
   */
  public Iterator words() {
    // scans the body to return only words
    // assumes that Doc is immutable
    if (docWords == null) {
      docWords = new Vector();
      final char NEW_LINE = '\n';
      final String[] SPECIALS = { "\t" };
      //final String[] JUNK_CHARS = { "`","``","''","(",")","-","[","]",":",";","\"",".",",","...","<",">"}; 

      // reads the content one character at a time, stop at new line
      // to process
      char[] chars = d.toCharArray();
      char c;
      String w = null;
      for (int i = 0; i < chars.length; i++) {

        c = chars[i];

        if (c == NEW_LINE) {
          if (w != null) {
            // process this line
            w.trim();
            for (int j = 0; j < SPECIALS.length; j++) {
              w = w.replaceAll(SPECIALS[j], " ");
            }

            if (!w.equals("")) {
              String[] witems = w.split(" ");
              for (int j = 0; j < witems.length; j++) {
                String witem = witems[j].trim();
                if (!witem.equals("")) {
                  //for (int x = 0; x < JUNK_CHARS.length; x++) witem = witem.replaceAll(JUNK_CHARS[x], "");
                  
                  docWords.add(witem);
                }
              }
            }
            // reset word
            w = null;
          }
          continue;
        } else {
          if (w == null) 
            w = c+"";
          else
            w += c;          
        }
      }
    } // end body processing

    // create a generator from the body words
    // here we use the built-in Vector.iterator() method
    if (docWords != null)
      return docWords.iterator();
    else
      return null;
  }
}
