package ch15.composite.visitor;

import java.util.Stack;

public class TypeCheckVisitor implements Visitor {
  // implement the type-checking phase
  // provides a method for each concrete node type
  private Stack els;

  public void ifStmt(IfStmt n) {
    String s2 = null;
    if (n.hasElse())
      s2 = (String) els.pop(); // else statement
    
    String s1 = (String) els.pop(); // then statement
    String e = (String) els.pop(); // if statement

    if (e.equals("boolean") && s1.equals("correct")
        && (s2 == null || s2.equals("correct")))
      els.push("correct");
    else
      els.push("error");
  }
  
  // other type-checking methods
}
