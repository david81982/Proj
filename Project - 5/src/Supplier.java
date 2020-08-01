import java.util.*;
import java.io.*;
public class Supplier 
{
	static Scanner r = new Scanner(System.in);
	static CustomerAccount shipperAccount;

	
	public static void createAccount() throws IOException
	{
		String id, password;
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
		
		
			try
			{
				accountSuccess = CustomerAccount.createNewAccountSupplier(id, password);
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
		
		if(shipperAccount != null)
		{
			System.out.println("error - account already exists.");
			return;
		}
		
		String id, password;
		
		System.out.println("Please enter an id:\t");
		id = r.nextLine();
		
		System.out.println("Please enter a password:\t");
		password = r.nextLine();
		try
		{
			shipperAccount = CustomerAccount.logInShipper(id, password);
			
			if(shipperAccount == null)
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
			System.out.println("Login not found - id given doesn't exist!");
		}
	}
	

	public static void logout()
	{
		
		if(shipperAccount == null)
		{
			System.out.println("Error - account already logged out.");
		}
		else
		{
			shipperAccount = null;
			System.out.println("Account logged out");
		}
		
		
	}
	
	public static void processDeliveryOrder()
	{
		//check if the supplier has logged in by calling status function in "supplier.java"
		boolean hasContent = false;
		Iterator<OrderClass> orderIterator;
		if(shipperAccount == null ) {
			System.out.println("Please sign in first");
			return;
		}
		LinkedList<OrderClass> orders = process_delivery_order.getDeliveryOrders(shipperAccount.getID());
		
		if(orders == null || orders.size() == 0)
		{
			System.out.println("There are no current orders");
		}
		else
		{
			orderIterator = orders.iterator();
			while(orderIterator.hasNext())
			{
				OrderClass showOrder = orderIterator.next();
				if(showOrder.ostatus.equals("Ordered"))
				{
					hasContent = true;
					System.out.println("Order:\t" + showOrder.getOrderName());
					
					showOrder.printOrder();
				}
			}
			
		}
		if(!hasContent)
		{
			System.out.println("No current orders to Process");
			return;
		}
		System.out.println("Please enter a valid supplier order");
		String orderFileName = r.nextLine();
		
		orderIterator = orders.iterator();
		while(orderIterator.hasNext())
		{
			OrderClass order = orderIterator.next();
			String orderName = order.getOrderName();
			if(orderName.equals(orderFileName))
			{
				if(order.ostatus.equals("Ordered"))
					process_delivery_order.reserveItems(order);
				else
				{
					System.out.println("Invalid order.");
					return;
				}
			}
		}
		
		
	}
	
	public static  void confirmShipment()
	{
		boolean hasContent = false;
		Iterator<OrderClass> orderIterator;
		if(shipperAccount == null ) {
			System.out.println("Please sign in first");
			return;
		}
		LinkedList<OrderClass> orders = ShipBillManager.getDeliveryOrders(shipperAccount.getID());
		
		if(orders == null || orders.size() == 0)
		{
			System.out.println("There are no current orders");
		}
		else
		{
			orderIterator = orders.iterator();
			while(orderIterator.hasNext())
			{
				OrderClass showOrder = orderIterator.next();
				if(showOrder.ostatus.equals("Ready"))
				{
					System.out.println("Order:\t" + showOrder.getOrderName());
					hasContent = true;
					
					showOrder.printOrder();
				}
			}
			
		}
		if(!hasContent)
		{
			System.out.println("No current orders to confirm");
			return;
		}
		System.out.println("Please enter a valid supplier order");
		String orderFileName = r.nextLine();
		
		orderIterator = orders.iterator();
		while(orderIterator.hasNext())
		{
			OrderClass order = orderIterator.next();
			String orderName = order.getOrderName();
			if(orderName.equals(orderFileName))
			{
				if(order.ostatus.equals("Ready"))
					ShipBillManager.confirmShipment(order);
				else
				{
					System.out.println("Invalid order.");
					return;
				}
				
			}
		}
		
		
	}
	
	
	public static void main(String[] args) throws IOException
	{
		String userInput;
		
		System.out.println("Welcome supplier");
		
		System.out.println("Input options:");
		System.out.println("0 - create account");
		System.out.println("1 - login");
		System.out.println("2 - logout");
		System.out.println("3 - Process Delivery Order");
		System.out.println("4 - confirm shipment");
		System.out.println("-1 - exit system");
		
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
					processDeliveryOrder();
					break;
				case "4":
					confirmShipment();
					break;
			}
	
			
			
		}
		while(!userInput.equals("-1"));
	}

}
