package ch2.app.utils;

import java.util.Vector;

import utils.EmptyException;

/**
 * @overview <code>IntSet</code> are mutable, unbounded sets of integers.
 *           A typical IntSet is {x1, ..., xn}
 *           
 *           <p>The abstraction function is:
 *           <code>
 *           AF(c) = { c.els[i].intValue | 0 <= i < c.els.size }
 *           </code>
 *           
 *          <p>The rep invariant is:
 *          <code>
 *           c.els != null &&
 *           for all integers i . c.els[i] is an Integer && 
 *           for all integers i,j . (0 <= i < j < c.els.size => 
 *              c.els[i].intValue != c.els[j].intValue)
 *           </code>
 *           
 *           <p> or more informally:
 *           <code>
 *           c.els is not null &&
 *           all elements of c.els are Integers &&  
 *           there are no duplicates in c.els
 *           </code>
 *           
 * @author dmle
 *
 */
public class IntSet implements Cloneable {
}
