package a3_1801040111.fsis;

import utils.*;
/**
 * @overview Customer represents customers that are of interest to the flower shop
 * @attributes
 * 	id				Integer	 		int
 * 	name			String
 * 	phoneNumber 	String
 * 	address			String
 * @object
 * 	A typical Customer is <pre> c = <i, n, p, a> </pre>, where
 * 		<pre>id(i), name(n), phoneNumber(p), address(a) </pre>
 * @abstract_properties
 * 	mutable(id)=false /\ optional(id)=false /\ min(id)=1 /\max(id)=1000000000
 * 	mutable(name)=true /\ optional(name)=false /\ length(name)=50
 * 	mutable(phoneNumber)=true /\ optional(phoneNumber)=false /\length(phoneNumber)=10
 * 	mutable(address)=true /\ optional(address)=false /\ length(address)=100
 * * @rep_invariant id >= 1 /\ id <= 1000000000 /\ name != null /\ name.length()
 *                	<= 50 /\ phoneNumber != null /\ phoneNumber.length() <= 10 /\
 *                	address != null /\ address.length() <= 100
 * @author nguyenvanhuyen
 *
 */
public class Customer implements Comparable<Object>, Document {
	
	private final int MIN_ID = 1;
	private final int MAX_ID = 1000000000;
	private final int LENGTH_NAME = 50;
	private final int LENGTH_PHONE = 10;
	private final int LENGTH_ADDRESS = 100;
	
	@DomainConstraint(type="Integer", mutable=false, optional=false, min = MIN_ID, max= MAX_ID)
	private int id;
	@DomainConstraint(type="String",mutable=true, optional=false, length = LENGTH_NAME)
	private String name;
	@DomainConstraint(type="String",mutable=true, optional=false, length=LENGTH_PHONE)
	private String phoneNumber;
	@DomainConstraint(type="String",mutable=true, optional=false, length=LENGTH_ADDRESS)
	private String address;

	
	//constructor methods
	/**
	 * @effects  <pre>
	   *            if i, n, p, a are valid
	   *              initialize this as Vehicle:<n,d,h,l,w,c>
	   *            else
	   *              throw NotPossibleException
   *          </pre>
	 */
	public Customer(@AttrRef("id") int id,@AttrRef("name") String name, 
					@AttrRef("phoneNumber")String phoneNumber,
					@AttrRef("address") String address) throws NotPossibleException {
		if (!validateId(id)) {
			throw new NotPossibleException("Customer<init>: invalid id: " + id);
		}
		if (!validateName(name)) {
			throw new NotPossibleException("Customer<init>: invalid name: " + name);
		}
		if (!validatePhoneNumber(phoneNumber)) {
			throw new NotPossibleException("Customer<init>: invalid phoneNumber: " + phoneNumber);
		}
		if (!validateAddress(address)) {
			throw new NotPossibleException("Customer<init>: invalid address: " + address);
		}
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	public Customer() {}
	
	//methods
	/**
	 * 
	 * @effects return <tt>this.id</tt>
	 */
	@DOpt(type=OptType.Observer)
	@AttrRef("id")
	public int getId() {
			return id;
	 }
	 
	/**
	 * 
	 * @effects return <tt>this.name</tt>
	 */
	 @DOpt(type=OptType.Observer)
	 @AttrRef("name")
	 public String getName() {
			return name;
	 }

	 /**
	   * @effects <pre>
	   *            if name is valid
	   *              set this.name = name
	   *            else 
	   *              throws NotPossibleException</pre>
	   */
	 @DOpt(type=OptType.Mutator) 
	 @AttrRef("name")
	 public void setName(String name) throws NotPossibleException {
			if (validateName(name))
				this.name = name;
			else
				throw new NotPossibleException("Customer.setName: invalid name: " + name);
	 }

	 /**
	  * 
	  * @effects return <tt>this.phoneNumber</tt>
	  */
	 @DOpt(type=OptType.Observer)
	 @AttrRef("phoneNumber")
	 public String getPhoneNumber() {
			return phoneNumber;
	 }


	 /**
	   * @effects <pre>
	   *            if phone number is valid
	   *              set this.phoneNumber = phoneNumber
	   *            else 
	   *              throws NotPossibleException</pre>
	   */
	 @DOpt(type=OptType.Mutator) 
	 @AttrRef("phoneNumber")
	 public void setPhoneNumber(String phoneNumber) throws NotPossibleException {
		 if (validatePhoneNumber(phoneNumber))
				this.phoneNumber = phoneNumber;
			else
				throw new NotPossibleException("Customer.setPhoneNumber: invalid phone number: " + phoneNumber);

	 }

	 /**
	  * 
	  * @effects return <tt>this.address</tt>
	  */
	 @DOpt(type=OptType.Observer)
	 @AttrRef("address")
	 public String getAddress() {
			return address;
	 }

	 /**
	   * @effects <pre>
	   *            if address is valid
	   *              set this.address = address
	   *            else 
	   *              throws NotPossibleException</pre>
	   */
	 @DOpt(type=OptType.Mutator) 
	 @AttrRef("address")
	 public void setAddress(String address) throws NotPossibleException{
		 if (validateAddress(address))
			this.address = address;
		else
			throw new NotPossibleException("Customer.setAddress: invalid address: " + address);

	 }

	// validation methods
	 /**
	   * @effects <pre>
	   *            if id is valid 
	   *              return true 
	   *            else 
	   *              return false</pre> 
	   */
	  protected boolean validateId(int id) {
	    return id >= MIN_ID && id <= MAX_ID;
	  }

	  /**
	   * @effects <pre>
	   *            if n is a valid name
	   *              return true 
	   *            else 
	   *              return false</pre> 
	   */
	  protected boolean validateName(String name) {
	    return (name != null) &&  name.length() <= LENGTH_NAME;
	  }

	  /**
	   * @effects <pre>
	   *            if p is valid 
	   *              return true 
	   *            else 
	   *              return false</pre> 
	   */
	  protected boolean validatePhoneNumber(String p) {
	    return (p != null) && p.length() <= LENGTH_PHONE;
	  }

	  /**
	   * @effects <pre>
	   *            if a is valid 
	   *              return true 
	   *            else 
	   *              return false</pre> 
	   */
	  protected boolean validateAddress(String a) {
		  return (a !=  null) && (a.length() <= LENGTH_ADDRESS);

	  }
	  
	  
	  /**
	   * @effects <pre>
	   *            if this satisfies rep invariant
	   *              return true 
	   *            else
	   *              return false</pre>
	   */
	  public boolean repOK() {
		  return validateId(id) && validateName(name) && validatePhoneNumber(phoneNumber) 
					&& validateAddress(address);	  }

	@Override
	public String toString() {
		return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
	}
	
	  
  /**
   * @effects <pre>
   *            if o is null 
   *              throw NullPointerException 
   *            else if o is not a Vehicle object
   *              throw ClassCastException
   *            else 
   *              return this.name.compareTo(o.name)
   *          </pre>
   */
	@Override
  public int compareTo(Object o) 
    throws NullPointerException, ClassCastException {
    
    if (o == null)
    	throw new NullPointerException("Customer.compareByName");
    else if (!(o instanceof Customer))
    	throw new ClassCastException("Customer.compareByName: not a Customer " + o);
    
    	Customer c = (Customer) o;
    	return this.name.compareTo(c.name);
  }
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Customer) || (obj == null)) {
			return false;
		}
		return this.id == ((Customer) obj).getId() && this.name == ((Customer) obj).getName()
				&& this.phoneNumber == ((Customer) obj).getPhoneNumber()
				&& this.address == ((Customer) obj).getAddress();
	}
	
	@Override
	public String toHtmlDoc() {
		return "<html> "
				+ "<head>"
					+ "<title>Customer: "+this.id+"-"+this.name+"</title>"
				+ "</head>"
				+ "<body>"
					+ this.id + " " + this.name + " " + this.phoneNumber + " " + this.address
				+ "</body>"
			+ "</html>";
	}
}


