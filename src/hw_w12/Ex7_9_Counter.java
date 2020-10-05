package hw_w12;


/**
 * @overview a counter that starts at 0 and double counter value for each time calling incr
 * @abstract_properties
 * P_Ex_7_8_Counter
 */
public class Ex7_9_Counter extends Ex7_8_Counter {
	/**
     * @effects <pre>
     *           initialise this as Ex7_9_Counter2:<count>
     *          </pre>
     */
    public Ex7_9_Counter(){
        super();
    }

    @Override
    /**
     * double value of counter
     * @modifies count
     * @effects count *= 2
     */
    public void incr(){
        /*
        with post-conditions (effects) of Counter2, it holds the substitution principle by strengthened
        the post-conditions (reducing the number of values of count by 2)
        => Counter2 is a legit sub-type of Counter1
         */
        count *= 2;
    }
}
