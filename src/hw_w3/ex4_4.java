package hw_w3;

import utils.EmptyException;
/**
 * @overview a stand alone procedure that multiples each element
 * 			 of array a to the sum of array b
 */
public class ex4_4 {
    /**
     * @modifies a
     * @effects
     * if a eq null || b eq null
     *      throw NullPointerException
     * else if a is empty || b is empty
     *      throw EmptyException
     * else
     * 	for each element in a
     *      element = element * sum(b)
     */
    public static void combine(int[] a, int[] b) throws NullPointerException,EmptyException{
        if (a == null || b == null){
            throw new NullPointerException("Invalid arrays");
        }
        else if(a.length == 0 || b.length == 0){
            throw new EmptyException("Invalid arrays");
        }
        
        int sum = ex4_3.sum_throw_exception(b);
        
        for (int el : a){
            el *= sum;
        }
    }
}