package hw_w12;

import utils.AttrRef;
import utils.DomainConstraint;
import utils.DOpt;
import utils.OptType;

/**
 * @overview a counter that starts at 0 and increase by 1 for each time calling incr
 * @attribute count Integer
 * @object a typical Ex7_8_Counter is Ex7_8_Counter = {count} where count(count)
 * @abstract_properties
 * 		mutable(counter) = false /\ optional(counter) = false
 */
public class Ex7_8_Counter {
    @DomainConstraint(type = "Integer", mutable = false, optional = false)
    protected int count;

    /**
     * @effects <pre>
     *           initialise this as Ex7_8_Counter:<count>
     *          </pre>
     */
    public Ex7_8_Counter(){
        count = 0;
    }

    /**
     * get the value of counter
     * @effects
     *      return count
     */
    public int getCounter(){
        return count;
    }

    /**
     * @modifies count
     * @effects increase count by 1
     */
    public void incr() {
    	count++;
    }

}