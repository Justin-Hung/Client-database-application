package Client;

/**
 * Class represents a client and holds information for the client, also 
 * provide methods to check proper formatting of information for client 
 * @author Justin, Robert
 */
public class Client {
	
	/**
	 * unique id for client 
	 */
	private int id;
	
	/**
	 * first name of client 
	 */
	private String fname;
	
	/**
	 * last name of client 
	 */
	private String lname;
	
	/**
	 * address of client 
	 */
	private String address;
	
	/**
	 * postal code of client 
	 */
	private String postalcode;
	
	/**
	 * phone number of client 
	 */
	private String phoneNum;
	
	/**
	 * client type, either 'C' or 'R' 
	 */
	private char type;
	
	/**
	 * constructor of client with parameters 
	 * @param i id of client 
	 * @param f first name of client 
	 * @param l last name of client 
	 * @param a address of client 
	 * @param p postal code of client
	 * @param ph phone number of client 
	 * @param c type of client 
	 */
	public Client(int i, String f, String l,  String a, String p, String ph, char c)
	{
		id = i;
		fname = f;
		lname = l;
		address = a;
		postalcode = p;
		phoneNum = ph;
		type = c;
	}
	
	/**
	 * set id 
	 * @param n id 
	 */
	public void setId(int n)
	{
		id = n;
	}
	
	/**
	 * set first name 
	 * @param f first name 
	 */
	public void setFname(String f)
	{
		fname = f;
	}
	
	/**
	 * set last name 
	 * @param f last name 
	 */
	public void setlname(String f)
	{
		lname = f;
	}
	
	/**
	 * set address
	 * @param f address
	 */
	public void setadd(String f)
	{
		address = f;
	}
	
	/**
	 * set postal code 
	 * @param f postal code 
	 */
	public void setpost(String f)
	{
		postalcode = f;
	}
	
	/**
	 * set phone number 
	 * @param f phone number 
	 */
	public void setNum(String f)
	{
		phoneNum = f;
	}
	
	/**
	 * set client type 
	 * @param f client type 
	 */
	public void setType(char f)
	{
		type = f;
	}
	
	/**
	 * return id 
	 * @return id 
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * return first name 
	 * @return first name 
	 */
	public String getfName()
	{
		return fname;
	}
	
	/**
	 * return last name 
	 * @return last name 
	 */
	public String getlName()
	{
		return lname;
	}
	
	/**
	 * get address
	 * @return address
	 */
	public String getaddress()
	{
		return address;
	}
	
	/**
	 * get postal code 
	 * @return postal code 
	 */
	public String getpost()
	{
		return postalcode;
	}
	
	/**
	 * get phone number 
	 * @return phone number 
	 */
	public String getphone()
	{
		return phoneNum;
	}
	
	/**
	 * get client type 
	 * @return client type 
	 */
	public char getType()
	{
		return type;
	}
	
	/**
	 * check client type for format 
	 * @return true if wrong format, false if correct format 
	 */
	public boolean checkClientType() 
	{
		boolean checkClient = (type != 'C' && type != 'R');
		return checkClient; 
	}
	
	/**
	 * check phone number format 
	 * @return false if wrong format, true if correct format 
	 */
	public boolean checkPhone() 
	{
		String checkPhone = phoneNum;

		int i = 0; 
		while (true)
		{
			if (!Character.isDigit(checkPhone.charAt(i))) 
			{
				return false;
			}
			if ( i == 11) {
				break;
			}
			i++; 
			if (i==3 || i==7) {
				i++;
			}
		}
		
		if (checkPhone.charAt(3) != '-' || checkPhone.charAt(7) != '-') {
			return false; 
		}
		
		if (checkPhone.length() != 12)
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * check postal code format 
	 * @return false if wrong format, true if correct format 
	 */
	public boolean checkPostal() 
	{
		if(postalcode.length() != 7)
		{
			return false; 
		}
		
		String checkPostal = postalcode;
		checkPostal = checkPostal.replaceAll("\\s+", "");

		for (int i = 0; i < checkPostal.length(); i=i+2)
		{
			if (!Character.isAlphabetic(checkPostal.charAt(i))) 
			{
				System.out.println(checkPostal.charAt(i));
				return false;
			}
		}	
		for (int j = 1; j < checkPostal.length(); j=j+2)
		{
			if (!Character.isDigit(checkPostal.charAt(j)))
			{
				System.out.println(checkPostal.charAt(j));
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * check length of first name, last name and address
	 * @return false if wrong format, true if correct 
	 */
	public boolean checkLength() {
		if(fname.length() > 20)
		{
			return false; 
		}
		if(lname.length() > 20)
		{
			return false; 
		}
		if(address.length() > 50)
		{
			return false; 
		}
		return true;
	}
	
	/**
	 * client to string 
	 */
	@Override
	public String toString()
	{
		return Integer.toString(id)  + " " + fname + " " + lname + " " + Character.toString(type);
	}
	
}
