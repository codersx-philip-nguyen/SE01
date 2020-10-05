package ch15.state;

public class Set {
  private SetState els;
  private int t1; // the threshold for growing
  private int t2; // the threshold for shrinking
  
  public Set() {
    els = new SmallSet(); 
    // set the thresholds
    t1 = 100;
    t2 = 10;
  }
  
  public boolean empty() {
    return (els.size() == 0);
  }
  
  public void insert(Object x) {
    int sz = els.size();
    
    els.insert(x);
    
    if (sz == t1 && els.size() > t1) {
      els = new BigSet(els.elements());
    }
  }
  
  // other methods go here
}
