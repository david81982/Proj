import java.io.*;
import java.util.*;

public class CustomerInteraction {

	
	static Scanner r = new Scanner(System.in);
	static CustomerAccount customerAccount = new CustomerAccount(1);
	
	
	public static void createAccount() throws IOException
	{
		String id, password, name, address, phoneNumber, creditCard;
		boolean accountSuccess = false;
		do
		{
		
		r.nextLine();
		System.out.println("Please enter an id:\t");
		id = r.nextLine();
		
		System.out.println("Please enter a password:\t");
		password = r.nextLine();
		
		System.out.println("Please enter a name");
		name = r.nextLine();
		
		System.out.println("Please enter an address");
		address = r.nextLine();
		
		System.out.println("Please enter a phone number");
		phoneNumber = r.nextLine();
		
		System.out.println("Please enter a credit card number");
		
		creditCard = r.nextLine();
		
			try
			{
				accountSuccess = CustomerAccount.createNewAccountCustomer(id, password, name, address, phoneNumber, creditCard);
			}
			catch(IOException fileError)
			{
				System.out.println("error!  File not able to open!");
			}
			
			if(!accountSuccess)
			{
				System.out.println("Error!  Account already exists!");
			}
		}
		while(!accountSuccess);
		
		System.out.println("Account created");
	}
	
	public static void login()
	{
		if(customerAccount.getID() != null)
		{
			System.out.println("error - account already exists.");
			return;
		}
		
		
		String id, password;
		
		System.out.println("Please enter an id:\t");
		id = r.next();
		
		System.out.println("Please enter a password:\t");
		password = r.next();
		try
		{
			if(customerAccount.LogInAccount(id, password))
				System.out.println("Login successful!");
			else
				System.out.println("Login not successful - password was incorrect!");
		}
		catch(FileNotFoundException invalidFileName)
		{
			System.out.println("Login not found - id given didnt exist!");
		}
	
	}
	
	public static void logout()
	{

		if(customerAccount.LogOutAccount())
		{
			System.out.println("The account was successfully logged out");
		}
		else
		{
			System.out.println("Error - already logged out");
		}
		
	}
	

	public static void main(String[] args) throws IOException
	{
		
		int userInput;
		
		System.out.println("Welcome customer");
		
		System.out.println("Input options:");
		System.out.println("0 - create account");
		System.out.println("1 - login");
		System.out.println("2 - logout");
		
		do
		{
			System.out.print("Please enter input:\t");
			userInput = r.nextInt();
			
			switch(userInput)
			{
				case 0:
					createAccount();
					break;
				case 1:
					login();
					break;
				case 2:
					logout();
					
			}
			
			
		}
		while(userInput != -1);
	}

}