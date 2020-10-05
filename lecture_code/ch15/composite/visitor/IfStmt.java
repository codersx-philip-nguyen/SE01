package ch15.composite.visitor;


public class IfStmt implements Stmt {
  private Expr e; // if expression
  private Stmt s1; // then statement
  private Stmt s2; // else statement
  
  public void accept(Visitor v) {
    // recursively type check all descendents first, then type check this
    e.accept(v);
    s1.accept(v);
    if (s2 != null) {
      s2.accept(v);
    }
    
    v.ifStmt(this);
  }  

  // other methods
  
  public boolean hasElse() {
    return (s2 != null);
  }
}
