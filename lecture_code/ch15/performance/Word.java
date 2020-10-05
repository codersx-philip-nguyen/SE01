package ch15.performance;

import java.util.Hashtable;

/**
 * @overview Words are strings that provide methods to produce them in various 
 *           forms. Words are immutable, and for each unique string there is 
 *           at most one word.
 *           
 * @author dmle
 *
 */
public class Word {
  
  private String s;
  
  private static Hashtable t; // maps strings to words

  static {
    t = new Hashtable();
  }
  
  // constructor method
  /**
   * @effects Makes <code>this</code> to be the word corresponding to <code>s</code>
   */
  private Word (String s) {
    this.s = s;
  }
  
  /**
   * @param s   a string 
   * @effects   returns the word corresponding to <code>s</code>
   */
  public static Word makeWord(String s) {
    Word w = (Word) t.get(s);
    
    if (w == null) {
      w = new Word(s);
      t.put(s, w);
    }
    
    return w;
  }
  
  /**
   * 
   * @param c a context object
   * @effects Returns the string corresponding to <code>this</code> in the form 
   *          suitable for context <code>c</code>.
   */
  public String mapWord(Context c) {
    if (c.isUppercase()) {
      return s.toUpperCase();
    } else {
      return s;
    }
  }
}
