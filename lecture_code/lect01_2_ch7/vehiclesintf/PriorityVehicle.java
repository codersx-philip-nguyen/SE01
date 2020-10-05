package lect01_2_ch7.vehiclesintf;

import lect01_2_ch7.vehicles.Vehicle;

/**
 * @overview PriorityVehicle represents vehicles that have priority over 
 *           others.
 */
public interface PriorityVehicle {

  /**
   * @effects <pre>
   *            if pv is null 
   *              return a positive number
   *            else
   *              if the total weight OR dimension of this is bigger than pv (
   *              dimension = width*length*height)
   *                return a positive number
   *              else if total weight and dimension of this are equal to pv
   *                return 0
   *              else 
   *                return a negative number
   *          </pre>
   */
  public int comparePriorityTo(Vehicle pv);
}
