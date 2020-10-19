package hw_w12;

import utils.AttrRef;
import utils.DomainConstraint;
import utils.DOpt;
import utils.OptType;

/**
 * @overview a counter that starts with a positive int that user input and increase by a number that user input
 * if that number larger than 0 for each time calling incr
 * @object a typical Ex7_9_Counter3 is Ex7_9_Counter3 = {count} where count(count)
 * @abstract_properties
 * P_Ex7_8_Counter
 */
public class Ex7_10_Counter3 extends Ex7_8_Counter{
    /**
     * @effects <pre>
     *              initialise this as Ex7_9_Counter2:<count>
     *          </pre>
     */
    public Ex7_10_Counter3(@AttrRef("count") int count){
        super.count = count;
    }

  
    /**
     * increase counter value by n value if n value larger than 0
     * @requires input > 0
     * @modifies count
     * @effects count += input
     */
    public void incr(int n){
        /*
        Counter3 is not a legit subtype because it adds more params than supertype => violate subsitution principle
         */
        count += n;
    }
}
