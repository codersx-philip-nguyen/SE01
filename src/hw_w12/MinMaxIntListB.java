package hw_w12;

import java.util.*;

/**
 * @overview arrayList<Integer> that get min, max elements of array
 */
public class MinMaxIntListB extends ArrayList<Integer>{
    
    /**
     * @effects return maximum Integer element of the array 
     */
    public Integer getMax(){
        ArrayList a = (ArrayList) super.clone();
        Collections.sort(a);
        return (Integer) a.get(a.size() - 1);
    }
    
    /**
     * @effects return minimum Integer element of the array 
     */
    public Integer getMin(){
        ArrayList a = (ArrayList) super.clone();
        Collections.sort(a);
        return (Integer) a.get(0);
    }
}

