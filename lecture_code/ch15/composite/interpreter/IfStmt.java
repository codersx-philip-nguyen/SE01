package ch15.composite.interpreter;


public class IfStmt implements Stmt {
  private Expr e; // if expression
  private Stmt s1; // then statement
  private Stmt s2; // else statement
  
  public boolean typeCheck() {
    if (!e.typeCheck() || !s1.typeCheck() || 
        !((s2 != null) && !s2.typeCheck())) { 
      return false;
    } else { 
      return true;
    }
  }
  
  public Node optX() {
    throw new utils.NotImplementedException("IfStmt.optX");
  }
}
