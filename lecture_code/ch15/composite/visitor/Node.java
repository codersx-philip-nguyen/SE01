package ch15.composite.visitor;

public interface Node {
  public void accept(Visitor v);
}
