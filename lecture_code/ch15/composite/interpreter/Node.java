package ch15.composite.interpreter;

public interface Node {
  /**
   * @effects Returns true if <code>this</code> type checks else returns false
   */
  public boolean typeCheck();
  
  /**
   * @effects If there is a way of reorganising <code>this</code> to reflect
   *          this optimisation, returns a new node that reflects the 
   *          optimisation else returns this
   */
  public Node optX();
}
