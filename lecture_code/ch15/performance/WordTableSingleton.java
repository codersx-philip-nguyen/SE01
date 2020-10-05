package ch15.performance;

/**
 * @overview Same as <code>WordTable</code> except that it is designed to be a singleton
 * 
 * @author dmle
 *
 */
public class WordTableSingleton {
  private static WordTableSingleton instance;
  
  // private constructor method
  private WordTableSingleton() {
    //...
  }
  
  //  a static factory method to create a unique instance of this class
  public static WordTableSingleton getTable() {
    if (instance == null) {
      instance = new WordTableSingleton();
    }
    
    return instance;
  }
}
