package test.ch15;

import ch15.performance.WordTableSingleton;

public class SingletonApp {
  public static void main(String[] args) {
    // create/obtain the singleton object
    WordTableSingleton wt = WordTableSingleton.getTable();
    // use the object here...
  }
}
