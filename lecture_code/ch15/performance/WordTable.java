package ch15.performance;

import java.util.Hashtable;

/**
 * @overview A <code>WordTable</code> is a mutable collection of <code>Word</code> objects; 
 *           each distinct string has at most one entry in the table.
 *           
 * @author dmle
 *
 */
public class WordTable {
  
  private Hashtable t;
  
  // constructor method
  /**
   * @effects Makes <code>this</code> to be an empty <code>WordTable</code>
   */
  public WordTable() {
    t = new Hashtable();
  }
  
  /**
   * 
   * @param s a string
   * @modifies <code>this</code>
   * @effects If <code>s</code> is already in <code>this</code> as a reserved word returns
   *          the prestored object, else if it is in <code>this</code> as a nonreserved
   *          throws <code>WrongKindException</code> else adds <code>s</code> to <code>this</code>
   *          as a reserved word.
   */
  public Word makeReserved(String s) throws WrongKindException {
    // check s
    Word w = (Word) t.get(s);
    if (w == null) {
      w = new Word(s);
      t.put(s, w);
    } else if (!isReserved(w)) {
      throw new WrongKindException("WordTable.makeReserved: not a reserved word: " + s);
    }
    
    return w;
  }

  /**
   * 
   * @param s a string
   * @modifies <code>this</code>
   * @effects If <code>s</code> is already in <code>this</code> as a nonreserved word returns
   *          the prestored object, else if it is in <code>this</code> as a reserved
   *          throws <code>WrongKindException</code>, else adds <code>s</code> to <code>this</code>
   *          as a nonreserved word.
   */
  public Word makeNonReserved(String s) throws WrongKindException {
    // check s
    Word w = (Word) t.get(s);
    if (w == null) {
      w = new Word(s);
      t.put(s, w);
    } else if (isReserved(w)) {
      throw new WrongKindException("WordTable.makeNonReserved: reserved word: " + s);
    }
    
    return w;
  }
  
  /**
   * 
   * @param w a word
   * @effects If <code>w</code> starts with a special character <code>_</code> returns
   *          true, else returns false. 
   * 
   */
  private boolean isReserved(Word w) {
    // do real check ...
    if (w.getString().startsWith("_"))
      return true;
    else
      return false;
  }
  
  /**
   * @overview Represents a word that appears in some text. Words a immutable. 
   *           They are created by using <code>WordTable</code>
   * @author dmle
   *
   */
  class Word {
    private String s;
    
    public Word(String s) {
      this.s = s;
    }
    
    public String getString() {
      return s;
    }
  }  
}