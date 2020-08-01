import java.util.*;
import java.io.*;
public class CustomerAccount 
{

	
	
	private String id;
	private String password;
	private String name;
	private String address;
	private String phoneNumber;
	private String creditCard;
	

	
	public CustomerAccount(String id, String password, int customerType)
	{
		this.id = id;
		this.password = password;
	}
	
	public CustomerAccount(String id, String password, String name, String address, String phoneNumber, String creditCard, int customerType)
	{
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.creditCard = creditCard;
	}
	
	public String getID()
	{
		return id;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	
	public String getCreditCard()
	{
		return creditCard;
	}
	
	
	//True-false for both create indicates success/failure
	public static boolean createNewAccountCustomer(String id, String password, String name, String address, String phoneNumber, String creditCard) throws IOException
	{
		File idFile = new File("Customer\\");
		
		//If directory not found, will make one.
		if(!idFile.exists())
		{
			idFile.mkdir();
		}
		
		idFile = new File("Customer\\" + id + ".txt");
		if(idFile.exists())
		{
			return false;
		}
		
		
		PrintWriter fileWriter = new PrintWriter(idFile);
		
		fileWriter.println("Password: " + password);
		fileWriter.println("Name: " + name);
		fileWriter.println("Address: " + address);
		fileWriter.println("Phone Number: " + phoneNumber);
		fileWriter.println("Credit Card: " + creditCard);
		
		fileWriter.close();
		return true;
		
	}
	
	public static boolean changeCustomerCreditCard(CustomerAccount customer, String creditCardNumber)
	{
		File idFile = new File("Customer\\" + customer.getID() + ".txt");
		try
		{
			PrintWriter fileWriter = new PrintWriter(idFile);
			
			fileWriter.println("Password: " + customer.password);
			fileWriter.println("Name: " + customer.name);
			fileWriter.println("Address: " + customer.address);
			fileWriter.println("Phone Number: " + customer.phoneNumber);
			fileWriter.println("Credit Card: " + creditCardNumber);
			
			fileWriter.close();
			
			return true;
		}
		catch(IOException e)
		{
			return false;
		}

		
	}
	
	
	public static boolean createNewAccountSupplier(String id, String password) throws IOException
	{
		File idFile = new File("Supplier\\");
		
		//If directory not found, will make one.
		if(!idFile.exists())
		{
			idFile.mkdir();
		}
		
		idFile = new File("Supplier\\" + id + ".txt");
		if(idFile.exists())
		{
			return false;
		}
		
		PrintWriter fileWriter = new PrintWriter(idFile);	
		
		
		fileWriter.println("Password: " + password);
		fileWriter.close();
		return true;
		
	}
	
	public boolean LogOutAccount()
	{
		if(id != null)
		{
			id = null;
			password = null;
			name = null;
			address = null;
			phoneNumber = null;
			creditCard = null;
		
		
			return true;
		}
		else
		{
			return false;
		}
	}
	

	
	public static CustomerAccount logInShipper(String id, String userInputPassword) throws FileNotFoundException
	{
		Scanner fileReader;
		
		File idFile = new File("Supplier\\" + id + ".txt");
		
		fileReader = new Scanner(idFile);
		
		CustomerAccount loggedInAccount = null;
		
		//We assume that password will be the first thing.
		if(fileReader.next().contentEquals("Password:"))
		{
			String accountPassword = fileReader.next();
			if(userInputPassword.equals(accountPassword))
			{
				int customerType = 1;
				loggedInAccount = new CustomerAccount(id, userInputPassword, customerType);
			}
		
		}
		
		fileReader.close();
		return loggedInAccount;
		
		
	}
	
	public static CustomerAccount logInCustomer(String id, String userInputPassword) throws FileNotFoundException
	{
		Scanner fileReader;
		File idFile = new File("Customer\\" + id + ".txt");
		
		fileReader = new Scanner(idFile);
		
		CustomerAccount loggedInAccount = null;
		
		if(fileReader.nextLine().contentEquals("Password: " + userInputPassword))
		{
				int customerType = 1;
				String name = fileReader.nextLine().substring(5);
				String address = fileReader.nextLine().substring(8);
				String phoneNumber = fileReader.nextLine().substring(13);
				String creditCard = fileReader.nextLine().substring(13);
				
				
				loggedInAccount = new CustomerAccount( id,  userInputPassword,  name,  address,  phoneNumber,  creditCard, customerType);
		}

		fileReader.close();
		return loggedInAccount;
		
		
	}
	
	public static String getCreditCardForShipper(String userID)
	{
		Scanner fileReader = null;
		File idFile = new File("Customer\\" + userID + ".txt");
		
		String creditCard = null;
		try
		{
			fileReader = new Scanner(idFile);
			while(fileReader.hasNext())
			{
				String fileLine = fileReader.nextLine();
				if(fileLine.contains("Credit Card:"))
					creditCard = fileLine.substring(13);
			}
		}
		catch(IOException e)
		{
			System.out.println("error");
		}
		finally
		{
			fileReader.close();
		}
		return creditCard;
		
		
	}

}
