package lect04_ch6.usage;

import java.util.Iterator;

import lect04_ch6.LinkedList;

public class IteratorUsage {
  public static void evenIntegersUpTo(int n) {
    // loop controlled by hasNext
    LinkedList<Integer> list = new LinkedList<>();
    for (int i = 0; i < n; i++) 
      if (i % 2 == 0) list.add(i); // even numbers
    
    Iterator<Integer> g = list.iterator();
    final String format = "%"+((n+"").length()+1)+"d";
    
    int count = 0;
    while (g.hasNext()) {
      Integer x = (Integer) g.next();
      // use x
      System.out.printf(format,x);
      count++;
      if (count % 15 == 0) System.out.println();
    }
  }

  
  public static void main(String[] args) {
    System.out.println("LinkedList iterator");
    evenIntegersUpTo(200);
  }
}
