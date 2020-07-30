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
			do
			{
			
				System.out.println("Please enter an id:\t");
				id = r.nextLine();
				if(id.equals("") || id.contains(" "))
					System.out.println("Error!  please enter a valid id!");
				
			}
			while(id.equals("") || id.contains(" "));
			
			do
			{
		
				System.out.println("Please enter a password:\t");
				password = r.nextLine();
				if(password.equals("") || password.contains(" "))
					System.out.println("Error!  please enter a valid password!");
				
			}
			while(password.equals("") || password.contains(" "));
		
			do
			{

				System.out.println("Please enter a name");
				name = r.nextLine();
				if(name.equals(""))
					System.out.println("Error!  please enter a valid name!");
				
			}
			while(name.equals(""));
		
			do
			{
				
				System.out.println("Please enter an address");
				address = r.nextLine();
				if(address.equals(""))
					System.out.println("Error!  please enter a valid address!");
				
			}
			while(address.equals(""));
			
			do
			{
				
				System.out.println("Please enter a phone number");
				phoneNumber = r.nextLine();
				if(phoneNumber.equals(""))
					System.out.println("Error!  please enter a valid phone number!");
				
			}
			while(phoneNumber.equals(""));
			
			do
			{
				System.out.println("Please enter a credit card number");
				creditCard = r.nextLine();
				
				if(creditCard.equals(""))
					System.out.println("Error!  please enter a valid credit card number!");
			}
			while(creditCard.equals(""));
		
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
	
	
	public static String login()
	{
		if(customerAccount.getID() != null)
		{
			System.out.println("Error - account already exists.");
			return "";
		}
		
		
		String id, password;
		
		System.out.println("Please enter an id:\t");
		id = r.next();
		
		System.out.println("Please enter a password:\t");
		password = r.next();
		try
		{
			
			if(customerAccount.LogInAccount(id, password)) {
				System.out.println("Login successful!");
				return id;
			}
			else {
				System.out.println("Login not successful - password was incorrect!");
				return "";
			}
			
		}
		catch(FileNotFoundException invalidFileName)
		{
			System.out.println("Login not found - id given didnt exist!");
			return "";
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
		
		String userInput;
		
		SelectItems SelectObject = new SelectItems();
				
		System.out.println("Welcome customer");
		
		System.out.println("Input options:");
		System.out.println("0 - create account");
		System.out.println("1 - login");
		System.out.println("2 - logout");
		System.out.println("3 - View Catalog");
		System.out.println("4 - View Order");
		System.out.println("-1 - exit");
		
		
		
		do
		{
			
			System.out.print("Please enter input:\t");
			userInput = r.nextLine();
			
			switch(userInput)
			{
				case "0":
					createAccount();
				
					break;
				case "1":
					login();
					break;
				case "2":
					logout();
					break;
				case "3":
					SelectObject.Customer = customerAccount;
					SelectObject.shop();
					break;
				case "4":
					// view order
					
					break;
			}
			
			
		}
		while(!userInput.equals("-1"));
	}

}