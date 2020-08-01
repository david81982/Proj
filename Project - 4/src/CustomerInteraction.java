import java.io.*;
import java.util.*;

public class CustomerInteraction {

	
	static Scanner r = new Scanner(System.in);
	static CustomerAccount customerAccount = null;
	static SelectItems cart = null;
	
	
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
	
	
	public static void login()
	{
		if(customerAccount != null)
		{
			System.out.println("Error - account already exists.");
			return;
		}
		
		
		String id, password;
		
		System.out.println("Please enter an id:\t");
		id = r.nextLine();
		
		System.out.println("Please enter a password:\t");
		password = r.nextLine();
		try
		{
			customerAccount = CustomerAccount.logInCustomer(id, password);
			
			if(customerAccount == null)
			{
				System.out.println("Invalid login credentials.");
			}
			else
			{
				System.out.println("account logged in");
			}
			
		}
		catch(FileNotFoundException invalidFileName)
		{
			System.out.println("Login not found - id given didnt exist!");
		}
	
	}
	
	public static void logout()
	{

		if(customerAccount == null)
		{
			System.out.println("Error - account already logged out.");
		}
		else
		{
			customerAccount = null;
			System.out.println("Account logged out");
		}
		
	}

	public static void selectItems() throws IOException
	{
		String shipper;
		System.out.println("Please enter a shipper that you want to buy from:\t");
		shipper = r.nextLine();
		
		//Possibly add a check here to see if shipper is correct?
		//ex: CustomerInteraction.checkShippers();
		
		Catalog.display();
		cart = new SelectItems();
		cart.shop(shipper);
	}
	
	public static void makeOrder() throws IOException
	{
		if(customerAccount == null)
		{
			System.out.println("You must be logged in to use!");
			return;
		}
		else if(cart == null)
		{
			System.out.println("You must purchase items before entering an order!");
			return;
		}
		
		String result = MakeOrderRequest.ValidRequest(customerAccount, cart.cart, cart.total, cart.supplier);
		
		if(result == null)
		{
			System.out.println("No output generated");
		}
		else
		{
			System.out.println("File created with name:\t" + result);
		}
	}
	
	public static void viewOrder()
	{
		
		if(customerAccount == null)
		{
			System.out.println("You must be logged in to use!");
			return;
		}
		
		OrderClass.showOrders(customerAccount.getID());
		System.out.println("Please select one of the files by typing in the name (DO NOT TYPE THE .TXT)");
		
		String fileName = r.nextLine();
		try
		{
			OrderClass newOrder = new OrderClass(fileName);
			newOrder.printOrder();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error!  The file name given is not valid!");
			return;
		}
				

	}
	

	public static void main(String[] args) throws IOException
	{
		
		String userInput;
		
		System.out.println("Welcome customer");
		
		System.out.println("Input options:");
		System.out.println("0 - create account");
		System.out.println("1 - login");
		System.out.println("2 - logout");
		System.out.println("3 - select Items from catalog");
		System.out.println("4 - make an Order");
		System.out.println("5 - view an Order");
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
					selectItems();
					break;
				case "4":
					makeOrder();
					break;
				case "5":
					viewOrder();
					break;
			}
			
			
		}
		while(!userInput.equals("-1"));
	}

}