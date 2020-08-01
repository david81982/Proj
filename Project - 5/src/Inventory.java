import java.util.*;
import java.io.*;
public class Inventory 
{
	String[] inventoryItems;
	String inventoryFilePath;
	String[] reservedItems;
	
	public Inventory()
	{
		inventoryItems = null;
		inventoryFilePath = "Inventory";
		reservedItems = null;
	}
	
	public boolean setCatalogItems()
	{
		File inventoryReader = new File(inventoryFilePath + "\\inventory.txt");
		
		Scanner fileReader;
		try
		{
			 fileReader = new Scanner(inventoryReader);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("The file was NOT found");
			return false;
		}
		
		String fileInput;
		while(fileReader.hasNext())
		{
			
			fileInput = fileReader.nextLine();
			
			//This checks for the reserved section
			if(fileInput.contains("R:"))
			{
				reservedItems = new String[1];
				reservedItems[0] = fileInput;
				break;
			}
			
			//We update old inventoryItems here.
			String[] tempItems = inventoryItems;
			
			if(inventoryItems != null)
				inventoryItems = new String[tempItems.length + 1];
			else
			{
				inventoryItems = new String[1];
			}
			
			int count;
			for(count = 0; count < inventoryItems.length - 1; count++)
				inventoryItems[count] = tempItems[count];
			
			inventoryItems[count] = fileInput;
			
		}
		
		//Start of reserved iterations.  Inherently same as inventoryItem setup.
		while(fileReader.hasNext())
		{
			fileInput = fileReader.nextLine();
			
			String[] tempItems = reservedItems;
			
			if(reservedItems != null)
				reservedItems = new String[tempItems.length + 1];
			else
			{
				reservedItems = new String[1];
			}
			
			int count;
			for(count = 0; count < reservedItems.length - 1; count++)
				reservedItems[count] = tempItems[count];
			
			reservedItems[count] = fileInput;
		}
		
		fileReader.close();
		return true;
	}
	
	//Will change by writing in.
	//Precondition:  that there has been some change to one of the instance variables.
	public void changeInventoryFile()
	{
		File inventoryReader = new File(inventoryFilePath + "\\inventory.txt");
		PrintWriter newFile;
		
		try
		{
			newFile = new PrintWriter(inventoryReader);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Inventory file not found!");
			return;
		}
		
		
		for(int i = 0; i < inventoryItems.length; i++)
		{
			
			newFile.println(inventoryItems[i]);
			
		}
		
		if(reservedItems != null)
		{
			for(int i = 0; i < reservedItems.length; i++)
			{
				newFile.println(reservedItems[i]);
			}
		}
		newFile.close();
		
	}
	
	//Will go around and ensure the files are correctly reserved.
	//Also checks to see if we have enough for the given item.
	
	//Postcondition - a new section of the reserved listing is generated, and we remove items to go to reserve.
	public boolean reserveInventoryItems(OrderClass order)
	{
		int invoiceSize = 0;
		boolean encounterError = false;
		
		if(!setCatalogItems())
		{
			System.out.println("There was an error while processing");
			return false;
		}
		
		//This will encode the orderClass to be able to handle the inventory.
		String[] invoiceItems = new String[10];
		int[] invoiceAmount = new int[10];
		String[] cartItems = order.cart;
		for(String cartItem: cartItems)
		{
			if(cartItem == null)
			{
				break;
			}
			
			for(int i = 0; i < 10; i++)
			{
				if(invoiceItems[i] == null || invoiceItems[i].equals(cartItem))
				{
					if(invoiceItems[i] == null)
					{
						invoiceItems[i] = cartItem;
					}
					invoiceAmount[i]++;
					break;
				}
			}
		}
		for(int i = 0; i < 10; i++)
		{
			if(invoiceItems[i] == null)
			{	
				invoiceSize = i;
				break;
			}
			invoiceItems[i] = invoiceItems[i] + " " + invoiceAmount[i];
		}
		
		
		//Will iterate until all invoiceItems are checked and made some reserve.
		String[] reserved = null;
		for(int i = 0; i < invoiceSize; i++)
		{
			System.out.println(invoiceItems[i]);
			String invoiceItemName = invoiceItems[i].substring(0,invoiceItems[i].indexOf(' '));
		
			//Block should get the last number of the string for the inventory.
			String temp = invoiceItems[i].substring(invoiceItems[i].indexOf(' ') + 1);
			
			int invoiceQuantity = Integer.parseInt(temp);
			
			
			
			
			for(int j = 0; j < inventoryItems.length; j++)
			{
				String inventoryItemName = inventoryItems[j].substring(0, inventoryItems[j].indexOf(' '));
				
				
				String inventoryQuantityString = inventoryItems[i].substring(inventoryItems[i].indexOf(' ') + 1);
				
				
				int inventoryQuantity = Integer.parseInt(inventoryQuantityString);
				
				
				if(invoiceItemName.contentEquals(inventoryItemName))
				{
					if(inventoryQuantity < invoiceQuantity)
					{
						System.out.println(inventoryItemName + "has not enough stock!");
						encounterError = true;
					}
					else
					{
						//what is this for?  This should be change for the overall inventory.
						inventoryItems[i] = inventoryItemName;
						inventoryItems[i] += " " + (inventoryQuantity - invoiceQuantity);
						
						//The inventory should just be form:
							//item .... amount
						
						String[] tempItems = reserved;
						
						if(reserved != null)
							reserved = new String[tempItems.length + 1];
						else
						{
							reserved = new String[1];
						}
						
						int count;
						for(count = 0; count < reserved.length - 1; count++)
							reserved[count] = tempItems[count];
						
						reserved[count] = "R:" + order.orderNumber + " " + inventoryItemName + " " + invoiceQuantity;
						
						
					}
				}
				
			}
			reservedItems = reserved;
		}

		
		if(encounterError == false)
		{
			changeInventoryFile();
			System.out.println("File should have been changed");
			return true;
		}
		System.out.println(("File did not work"));
		return false;
	}
	
	
	
	
	public boolean checkInventoryItems(OrderClass order)
	{
		int invoiceSize = 0;
		boolean encounterError = false;
		
		if(!setCatalogItems())
		{
			System.out.println("There was an error while processing");
			return true;
		}
		
		String[] invoiceItems = new String[10];
		int[] invoiceAmount = new int[10];
		String[] cartItems = order.cart;
		for(String cartItem: cartItems)
		{
			if(cartItem == null)
			{
				break;
			}
			
			for(int i = 0; i < 10; i++)
			{
				if(invoiceItems[i] == null || invoiceItems[i].equals(cartItem))
				{
					if(invoiceItems[i] == null)
					{
						invoiceItems[i] = cartItem;
					}
					invoiceAmount[i]++;
					break;
				}
			}
		}
		for(int i = 0; i < 10; i++)
		{
			if(invoiceItems[i] == null)
			{	
				invoiceSize = i;
				break;
			}
			invoiceItems[i] = invoiceItems[i] + " " + invoiceAmount[i];
		}
		
		String[] reserved = null;
		for(int i = 0; i < invoiceSize; i++)
		{
			
			String invoiceItemName = invoiceItems[i].substring(0,invoiceItems[i].indexOf(' '));
		
			//Block should get the last number of the string for the inventory.
			String temp = invoiceItems[i].substring(invoiceItems[i].indexOf(' ') + 1);
			
			
			int invoiceQuantity = Integer.parseInt(temp);
			
			
			
			
			for(int j = 0; j < inventoryItems.length; j++)
			{
				String inventoryItemName = inventoryItems[j].substring(0, inventoryItems[j].indexOf(' '));
				
				
				String inventoryQuantityString = inventoryItems[i].substring(inventoryItems[i].indexOf(' ') + 1);
				
				
				int inventoryQuantity = Integer.parseInt(inventoryQuantityString);
				
				
				if(invoiceItemName.contentEquals(inventoryItemName))
				{
					if(inventoryQuantity < invoiceQuantity)
					{
						System.out.println(inventoryItemName + "has not enough stock!");
						encounterError = true;
					}
				}
			}
		}
		
		return encounterError;
	}
		
		
	public String[] removeReserveInventoryItems(String reserveOrder)
	{
		String[] output = null;
		
		if(!setCatalogItems())
		{
			System.out.println("There was an error while processing");
			return null;
		}
		
		File inventoryReader = new File(inventoryFilePath + "\\inventory.txt");
		PrintWriter newFile;
		try
		{
			newFile = new PrintWriter(inventoryReader);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Inventory file not found!");
			return null;
		}
		
		for(int i = 0; i < inventoryItems.length; i++)
			newFile.println(inventoryItems[i]);
		
		for(int i = 0; i < reservedItems.length; i++)
		{
			String read = reservedItems[i];
			if(read.contains("R:"+reserveOrder))
			{
				
				String[] tempItems = output;
				
				if(output != null)
					output = new String[tempItems.length + 1];
				else
				{
					output = new String[1];
				}
				
				int count;
				for(count = 0; count < output.length - 1; count++)
					output[count] = tempItems[count];
				
				output[count] = read;
				continue;
			}
			
			newFile.println(reservedItems[i]);
			
		}
		
		
		newFile.close();
		
		return output;
		
	}
	
	public String[] getinventoryItems()
	{
		return inventoryItems;
	}

	public String[] readInventory()
	{
		setCatalogItems();
		return inventoryItems;
	}
	
	
}
