package hw_w12;


/**
 * @overview a counter that starts at 0 and double counter value for each time calling incr
 * @abstract_properties
 * P_Ex_7_8_Counter
 */
public class Ex7_9_Counter2 extends Ex7_8_Counter {
	/**
     * @effects <pre>
     *           initialise this as Ex7_9_Counter2:<count>
     *          </pre>
     */
    public Ex7_9_Counter2(){
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
        Counter2 is a legit sub-type of Counter1 because it strengthen the post-conditions by double value of counter
         */
        count *= 2;
    }
}
