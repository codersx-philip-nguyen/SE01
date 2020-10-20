package hw_w5_6;

import org.junit.Test;

public class MinArrayTest {
	/**
	 * @modifies System.out
	 * @effects 
	 *    initialise resources
	 *    for each test case 
	 *      run the unit with the test case
	 *      if expected result is not the same as actual result
	 *        throws AssertionError 
	 *      else 
	 *        displays result on the standard output
	 * 
	 * @testcases
	 * RANGE(s): 
	 * 	a: 
	 * 		R1 = set of non-empty integer arrays containing the min element at the *lowest* position
	 * 		R2 = set of non-empty integer arrays containing the min element at the *highest* position
	 * 		R1 = set of non-empty integer arrays containing the min element at the *normal* position	 
	 * 
	 * TDS(s):
	 * 		TDS1 = {		//min at position 0
	 * 			[-1], [1],
	 * 			[-1, 2], [1, 2], [-2, -1],
	 * 			[1,3,4,,7,6,10]
	 * 		}
	 * 
	 * 		TDS2 = {		// min at position a.length() - 1 (a.length > 1)
	 * 			[2, -1], [2, 1], [-1, -2],
	 * 			[10, 6, 7, 8, 9, 2, 1],
	 * 		}
	 * 
	 * 		TDS3 = {		// min at position a.length() - 1 (a.length > 1)
	 * 			[2, -3, -1], [2, 1, 8], [-1, -7, 5 , 2],	//min at position 1 (a.length > 2)
	 * 			[10, 6, -7, 8, 9, 2, 1], [-2, -1, -3, 1], [2, 4, 1, 3]	//min at position 2 (a.length > 3)
	 * 			[-1, 3, 2, 4, -5, -8, 5,6 ,7] 	//min at position 5 (a.length > 6)
	 * 		}	
	 * @param a
	 * @return
	 */
	@Test
	public void min() throws AssertionError {
		int[][] TDS1 = {
				{-1}, {1},
				{-1,2}, {1,2}, {-2, -1},
				{1, 3, 2, 4, 5, 10, 8, 9, 7, 6},
				{-1, 3,2 ,4 ,5, 10, 8, 9, 7 ,6}
		};
	}
}
