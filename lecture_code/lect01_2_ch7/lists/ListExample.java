package lect01_2_ch7.lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListExample {
  public static void main(String[] args) {
    // an example to show how to use List.iterator
    
    // create a list
    List list = new ArrayList();
    
    list.add(2);
    list.add(0);
    list.add(1);
    list.add(3);
    list.add(100);
    
    // obtain the iterator
    Iterator it = list.iterator();
    
    // print elements using iterator
    Object e;
    int sum = 0;
    // System.out.println("List before loop: " + list);
    
    while (it.hasNext()) {
      e = it.next();
      
      sum = sum + ((Integer)e);
      
      // remove e
      //it.remove();
      
      System.out.println(e);
    }
    
    System.out.println("Sum: " + sum);
    
    // System.out.println("List after loop: " + list);
    
    
  }
}
