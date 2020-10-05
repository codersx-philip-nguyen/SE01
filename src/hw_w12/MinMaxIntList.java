package hw_w12;

import java.util.ArrayList;
import java.util.Collections;
import utils.DomainConstraint;

/**
 * @overview arrayList<Integer> that get min, max elements of array
 * @attributes
 * min Integer
 * max Integer
 */
public class MinMaxIntList extends ArrayList<Integer> {
	
	@DomainConstraint(type="Integer")
	private Integer min;
	@DomainConstraint(type="Integer")
	private Integer max;
	
	public MinMaxIntList() {
		super();
	}
	
	/**
	 * @effects
	 *	appends e to the end of this list and
	 *	update min and max with e
	 * 	if succeeds return true
	 *  return false
	 * @param e
	 * @return
	 */
	@Override
	public boolean add(Integer e) {
		if( min == null || e < min) {
			min = e;
			return true;
		}
		
		else if( max == null || e > max) {
			max = e;
			return true;
		}
		super.add(e);
		
		return false;
	}
	
	/**
	 * @effects
	 * if e is present
	 *		remove the first occurrence of e from this
	 *		return true
	 * else
	 *	return false
	 * @param e
	 * @return
	 */
	@Override
	public boolean remove(Object e) {
		if(e == min) {
			//clone to array list
			ArrayList a = (ArrayList) super.clone();
			Collections.sort(a);
			
			min = (Integer) a.get(1);
			return true;
		}
		else if (e == max) {
			ArrayList a = (ArrayList) super.clone();
			Collections.sort(a);
			
			max = (Integer) a.get(a.size() - 2);
			return true;
		}
		return false;
	}
	
	
	/**
	 * @effects return min
	 * @return
	 */
	public Integer getMin() {
		return min;
	}
	
	/**
	 * @effects return max
	 * @return
	 */
	public Integer getMax() {
		return max;
	}
		
		
}

