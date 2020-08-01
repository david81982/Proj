/*
 * CHECK LIST
 * 
 * Check if supplier is logged in ***done
 * Display the orders ***Object needs to be corrected as it shows an error
 * Allow the supplier to select an order
 * Upon selection check to ensure inventory is available
 * Implement that if not available display that that item is needed
 * If in stock change status to ready ***ostatus is done but still need to change file
 * Have a display that states the order is reserved
 * 
 */
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.nio.file.*;

public class process_delivery_order 
{
	/*
	static Scanner sc = new Scanner(System.in);
	OrderClass orderC = new OrderClass();
	

	
	orderC.printOrder(); // 102 from OrderClass,java this will display the orders to the user
	
	/*public static void changeStatus(){ //this will be used to change the file
		
	}*/
	
	/*  		**start of code to read the file, needs to be corrected to write as well
	String file_lines;
	String text;
	void read_file_test(String file) throws IOException{
		int temp;
		FileReader fileread = null;
		try {
			fileread = new FileReader(file);
		}
		catch(FileNotFoundException fe){
			System.out.println("The file name for file one was not found");
		}
		
		//this is the change
		char temp2 = 0;
		while((temp=fileread.read())!=-1)
			temp2 = (char)temp;
			file_lines += Character.toUpperCase(temp2);
		fileread.close();
		*/
	
	/*
	 * void availablity(item){
	 * 		//if not available
	 * 		System.out.println("Item" + item + " is needed to be restocked");
	 * }
	 */
	
	
	public static LinkedList<OrderClass> getDeliveryOrders(String supplier)
	{
		LinkedList<OrderClass> orders;
		try
		{
			orders = OrderClass.getOrdersForSupplier(supplier);
		}
		catch(FileNotFoundException e )
		{
			return null;
		}
		
		return orders;

	}
	
	public static boolean reserveItems(OrderClass order)
	{
		Inventory inventoryItems = new Inventory();
		if(inventoryItems.checkInventoryItems(order))
			return false;
		inventoryItems.reserveInventoryItems(order);
		return order.changeStatus("Ready");
	}
}
/*
	I will be putting comments on what I did for the code down here.
	I have moved your check from the class to the overall supplier interaction to keep consistency with other methods.
	I have added a method that will first check all of the possible files we can check.  I have not added a check yet as to whether it is valid to look at (that is, if its ordered, etc.)
	
*/