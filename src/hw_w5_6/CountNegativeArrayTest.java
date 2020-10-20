package hw_w5_6;

import org.junit.Test;

/**
 * @overview A test driver for the <code>countNegative</code> method.
 */
public class CountNegativeArrayTest {
	
//	private static int [][] tcA;
//	private static int[] tcX;
//	private static Object[][] results;
//	
//	@BeforeCalss
//	public static void setUp() {
//		// test cases 
//		tcA = new int[][] {
//			null,
//			{},
//			{}
//		};
//	}
	
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
	 * 		R1 = set of non-empty integer arrays containing the elements that are negative integer
	 * 		R2 = set of non-empty integer arrays containing the elements that are positive	integer
	 * 		R1 = set of non-empty integer arrays containing the elements that contain both negative and positive integer
	 * TDS(s):
	 * 		TDS1 = {		//elements are negative integer ( count = a.length )
	 * 			[-1], [-1, -2],
	 * 		}
	 * 
	 * 		TDS2 = {		// elements are positive integer (count = 0 )
	 * 			[1], [2, 3], [5, 7, 9], [1, 2, 3 ,5 ,6 ,8 ,9, 10]
	 * 		}
	 * 
	 * 		TDS3 = {		// elements are both negative and positive integer 
	 * 			[1, -2], [-5 , 1]	// a.length = 2
	 * 			[10, 6, -7, -10, -9, 2, 1], [-2, -1, -3, 1], [2, 4, 1, 3]	// a.length > 2
	 * 		}	
	 * @param a
	 * @return
	 */
	@Test
	public void countNegative() throws AssertionError {
		int[][] TDS1 = {
				{-1}, {-2, -1} , {-5, -7, -8},
				{1}, {1, 2}, {1 ,3 ,7},
				{1, -2}, {10, 4, -7, -10, -9, 2, 1}
		};
	}
}
