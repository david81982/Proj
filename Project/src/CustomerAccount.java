import java.util.*;
import java.io.*;
public class CustomerAccount 
{

	//Customer type will have 2 modes:  1 = customer.  2 = supplier
	private int customerType;
	
	
	private String id;
	private String password;
	private String name;
	private String address;
	private String phoneNumber;
	private String creditCard;
	
	public CustomerAccount(int customerType)
	{
		this.customerType = customerType;
	}
	
	public CustomerAccount(String id, String password)
	{
		this.id = id;
		this.password = password;
	}
	
	public CustomerAccount(String id, String password, String name, String address, String phoneNumber, String creditCard)
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
			//Dont change customerType.  Assumed will automatically be set depending on either customer or supplier.
		
		
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Generalization for login.
	public boolean LogInAccount(String id, String userInputPassword) throws FileNotFoundException
	{
		if(customerType == 2)
		{
			return logInShipper(id, userInputPassword);
		}
		else
		{
			return logInCustomer(id, userInputPassword);
		}
	}
	
	public boolean logInShipper(String id, String userInputPassword) throws FileNotFoundException
	{
		Scanner fileReader;
		
		File idFile = new File("Supplier\\" + id + ".txt");
		
		fileReader = new Scanner(idFile);
		
		//We assume that password will be the first thing.
		if(fileReader.next().contentEquals("Password:"))
		{
			String accountPassword = fileReader.next();
			if(userInputPassword.equals(accountPassword))
			{
				password = accountPassword;
				this.id = id;
				
				fileReader.close();
				return true;
			}
		}
		else
		{
			System.out.println("Not able to open file!");
		}
		
		fileReader.close();
		return false;
		
		
	}
	
	public boolean logInCustomer(String id, String userInputPassword) throws FileNotFoundException
	{
		Scanner fileReader;
		File idFile = new File("Customer\\" + id + ".txt");
		
		fileReader = new Scanner(idFile);
		
		if(fileReader.nextLine().contentEquals("Password: " + userInputPassword))
		{
				this.id = id;
				password = userInputPassword;
				customerType = 2;
				name = fileReader.nextLine().substring(5);
				address = fileReader.nextLine().substring(8);
				phoneNumber = fileReader.nextLine().substring(13);
				creditCard = fileReader.nextLine().substring(12);
				
				fileReader.close();
				return true;
		}
		else
		{
			System.out.println("invalid login");
		}

		fileReader.close();
		return false;
		
		
	}
	
	public boolean checkIfLoggedIn()
	{
		if(id != null)
			return true;
		else
			return false;
	}

}
