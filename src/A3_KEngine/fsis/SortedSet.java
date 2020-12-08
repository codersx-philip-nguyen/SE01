package a3_1801040111.fsis;
import utils.DOpt;
import utils.DomainConstraint;
import utils.Sorted;
import utils.SortOrder;
import utils.OptType;
import utils.EmptyException;
import utils.collections.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * @overview <code>SortedSet</code> are mutable, unbounded sets (possibly empty)
 *
 * @attributes
 *  elements    Vector<Comparable>
 *
 * @object
 * A typical SortedSet is {x1, ..., xn}
 *
 * <p>The abstraction function is:
 * <code>
 *      AF(c) = { c.els[i] | 0 <= i < c.els.size }
 * </code>
 *      <code>
 *          c.els is not null &&
 *          all elements of c.els are Comparable &&
 *          there are no duplicates in c.els &&
 *          elements are sorted in an ascending order
 *      </code>
 * @abstract_properties
 *  mutable(elements) = false /\ elements != null
 *  /\ foreach element of elements: element != null /\ element is Comparable
 *  /\ for i, j in range(0, len(elements)), i < j: elements[i] < elements[j]
 */
public class SortedSet implements Collection<Comparable> {

    @DomainConstraint(type = "Vector<Comparable>", optional = false)
    @Sorted(order = SortOrder.Asc)
    private final Vector<Comparable> elements;

    /**
     * @effects initialises this to be ()
     */
    public SortedSet() {
        this.elements = new Vector<>();
    }

    /**
     * @requires newElement != null
     * @modifies this
     * @effects
     *  if element already in this:
     *      do nothing and return false
     *  else:
     *      add the new element to this where it can maintains the ascending order
     */
    @DOpt(type = OptType.MutatorAdd)
    public boolean insert(Comparable newElement) {
        int indexToInsert = getIndexToAdd(newElement);

        // If the newElement already exists in this
        if (indexToInsert == -1)
            // Then do nothing and return false
            return false;

        // Else, add the new element to this with the calculated position and return true
        this.elements.add(indexToInsert, newElement);
        return true;
    }

    /**
     * @effects
     *  returns the position for the new element in this while maintaining the ascending order
     */
    @DOpt(type = OptType.Helper)
    private int getIndexToAdd(Comparable newElement) {
        return positionToAdd(newElement, 0, this.size() - 1);
    }

    /**
     * @param start The starting index for the current searching range
     * @param end The ending index for the current searching range
     * @requires newElement != null
     * @modifies this
     * @effects
     *  if element already in this:
     *      return -1
     *  else:
     *      return the position for the new element for which this can maintains the ascending order
     * @pseudocode
     *  # Binary search algorithm
     *  repeatedly comparing the new element to the middle element of the current searching range:
     *      if the searching range is reduced to contain just 1 element left:
     *          return the index of that one element
     *      else if the new element > middle element:
     *          recursive call with the right half of the current range
     *      else if the new element < middle element:
     *          recursive call with the left half of the current range
     *      else:
     *          return -1 since duplications are not allowed
     */
    @DOpt(type = OptType.Helper)
    private int positionToAdd(Comparable newElement, int start, int end) {

        if (start > end)
            return start;

        int middle = end - (end - start) / 2;

        Comparable middleElement = this.elements.get(middle);

        int comparisonResult = newElement.compareTo(middleElement);

        if (comparisonResult > 0)
            return positionToAdd(newElement, middle + 1, end);

        if (comparisonResult < 0)
            return positionToAdd(newElement, start, middle - 1);

        return -1;
    }

    /**
     * @effects
     *  returns the size of this
     */
    @DOpt(type = OptType.ObserverSize)
    public int size() {
        return this.elements.size();
    }

    /**
     * @effects
     *  if this is empty
     *    return true
     *  else
     *    return false
     */
    @DOpt(type = OptType.Observer)
    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    /**
     * @requires x != null
     * @effects
     *  if x is in this
     *    return true
     *  else
     *    return false
     */
    @DOpt(type = OptType.ObserverContains)
    public boolean contains(Object o) {
        if (o instanceof Comparable)
            return this.elements.contains(o);
        else
            return false;
    }

    /**
     * @requires x != null
     * @modifies this
     * @effects if this is empty or x is not in this
     *              do nothing
     *          else
     *              remove the x from this
     */
    @DOpt(type = OptType.MutatorRemove)
    public boolean remove(Comparable o) {
        return this.elements.remove(o);
    }

    /**
     * @modifies this
     * @effects clear all elements from this
     */
    public void clear() {
        this.elements.clear();
    }

    /**
     * @effects <pre>
     *  if this is empty
     *     throw EmptyException
     *   else
     *     return a generator that will produce all
     *     the elements of this in sequence.</pre>
     *
     * @requires <tt>this</tt> must not be modified
     *            while the generator is in use
     */
    @DOpt(type = OptType.ObserverIterator)
    public Iterator<Comparable> elements() throws EmptyException {
        if (size() == 0)
            throw new EmptyException("LinkedList.iterator");
        return new SortedSetGen();
    }
    
    /**
     * @overview
     *   SortedSet.SortedSetGen represents a generator of the
     *   elements of an SortedSet.
     * @attributes
     *  index Integer
     * @abstract_properties
     *  mutable(index)=false /\ min(index)=0 /\
     *  index<SortedSet.size() /\
     *  SortedSetGen.new = [x1,...] where
     *    each xi is in SortedSetGen.SortedSet and
     *    xis are arranged in same order as
     *         SortedSetGen.SortedSet's elements.
     */
    private class SortedSetGen implements Iterator<Comparable> {
        @DomainConstraint(type = "Integer", mutable = false, min = 0)
        int index;

        public SortedSetGen() {
            this.index = 0;
        }

        public boolean hasNext() {
            return this.index < SortedSet.this.elements.size();
        }

        public Comparable next() throws NoSuchElementException {
            if (hasNext())
                return SortedSet.this.elements.get(index++);

            throw new NoSuchElementException("SortedSet.iterator");
        }

        @Override
        public void remove() {
            // Do nothing
        }
    } /** end {@link SortedSetGen} */

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();

        for (Comparable element : elements)
            ret.append(element.toString()).append("\n");

        return ret.toString();
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof SortedSet))
            return false;

        SortedSet other = (SortedSet) obj;

        Iterator thisIterator = this.elements();

        Iterator otherIterator = other.elements();

        while (thisIterator.hasNext() && otherIterator.hasNext()) {

            Comparable thisElement = (Comparable) thisIterator.next();

            Comparable otherElement = (Comparable) otherIterator.next();

            if (thisElement.compareTo(otherElement) != 0)
                return false;
        }

        if (thisIterator.hasNext() != otherIterator.hasNext())
            return false;

        return true;
    }

    /**
     * @effects
     *  if this satisfies abstract properties
     *    return true
     *  else
     *    return false
     */
    public boolean repOK() {

        if (this.elements.get(0) == null)
            return false;

        for (int i = 1; i < size(); i++) {
            Comparable element = this.elements.get(i);

            if (element == null)
                return false;

            if (element.compareTo(this.elements.get(i - 1)) <= 0)
                return false;
        }
        return true;
    }
}
