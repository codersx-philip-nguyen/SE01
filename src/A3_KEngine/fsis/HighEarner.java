package a3_1801040111.fsis;

import utils.*;

/**
 * @overview HighEarner is a sub-class of Customer representing the wealthy customers whose income are above a given threshold.
 * @attributes
 * 	income 		Float
 * @object
 * 		A typical HighEarner is
 * 
 *         <pre>
 * 				h = <i, n, p, a, in>
 *         </pre>
 * 
 *         , where
 * 
 *         <pre>
 * 				id(i), name(n), phoneNumber(p), income(in)
 *         </pre>
 * @abstract_properties
 * 	P_Customer /\ min(id) = 10000000 /\
 * 	mutable(income)=true /\ optional(income)=false /\ min(income)= 10000000
 * @rep_invariant id >= 10000000 		/\ name != null 				/\ name.length() <= 50 /\
 *                phoneNumber != null 	/\ phoneNumber.length() <= 10 	/\ address
 *                != null 				/\ address.length() <= 100 income != null /\ income >10000000
 * @author nguyenvanhuyen
 *
 */
public class HighEarner extends Customer {
	
	private final int MIN_ID = 10000000;
	private final int MIN_INCOME = 10000000;
	
	@DomainConstraint(type="Float",mutable=true, optional=false, min=MIN_INCOME)
	private float income;
	
	//constructor methods
	/**
	 * @effects
	 * 
	 *          <pre>
	 * 			if i, n, p, a, in are valid
	 * 				initialize this as HighEarner:<i,n,p,a,in>
	 * 			else
	 * 				throw NotPossibleException
	 *          </pre>
	 */
	public HighEarner(@AttrRef("id") int id,@AttrRef("name") String name,
					  @AttrRef("phoneNumber") String phone,
					  @AttrRef("address") String address, 
					  @AttrRef("income") float income) throws NotPossibleException {
		super(id, name, phone, address);
		// TODO Auto-generated constructor stub
		if (!validateIncome(income)) {
			throw new NotPossibleException("HighEarner<init>: invalid income: " + income);
		}
		this.income = income;
	}
	
	public HighEarner() {}
	
	//GETTER AND SETTES FOR ATTRIBUTE
	/**
	  * 
	  * @effects return <tt>this.income</tt>
	  */
	 @DOpt(type=OptType.Observer)
	 @AttrRef("income")
	 public float getIncome() {
			return income;
	 }


	 /**
	   * @effects <pre>
	   *            if income is valid
	   *              set this.income = income
	   *            else 
	   *              throws NotPossibleException</pre>
	   */
	 @DOpt(type=OptType.Mutator) 
	 @AttrRef("income")
	 public void setIncome(float income) throws NotPossibleException {
		 if (validateIncome(income))
				this.income = income;
			else
				throw new NotPossibleException("HighEarner.setIncome: invalid income: " + income);

	 }
	
	
	//validation methods
	/**
	* @effects <pre>
	*            if income is valid 
	*              return true 
	*            else 
	*              return false</pre> 
	*/
	private boolean validateIncome(float income) {
        return income >= MIN_INCOME;

	}
	
   /**
   * @effects <pre>
   *   if id is valid 
   *     return true 
   *   else 
   *     return false</pre> 
   */
	@Override
	@DomainConstraint(type="Float",mutable = true, optional=false, min=MIN_ID)
	protected boolean validateId(int id) {
        return super.validateId(id) && id > MIN_ID;

  }


	@Override
	public String toString() {
		return "HighEarner(" + getName() + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof HighEarner) || (obj == null)) {
			return false;
		}
		return super.getId() == ((HighEarner) obj).getId() && super.getName() == ((HighEarner) obj).getName()
				&& super.getPhoneNumber() == ((HighEarner) obj).getPhoneNumber()
				&& super.getAddress() == ((HighEarner) obj).getAddress()
				&& this.income == ((HighEarner) obj).getIncome();
	}
	
	@Override
	public boolean repOK() {
		if (!(super.repOK())) {
			return false;
		}
		return validateIncome(this.income);
	}
	
	@Override
	public String toHtmlDoc() {
		return "<html> "
				+ "<head>"
					+ "<title>Customer: "+this.getId()+"-"+this.getName()+"</title>"
				+ "</head>"
				+ "<body>"
					+ this.getId() + " " + this.getName() + " " + this.getPhoneNumber() + " " + this.getAddress() + " " + this.income
				+ "</body>"
			+ "</html>";
	}
}
