import java.util.*;
import java.io.*;
public class OrderClass {
	

	String userid;
	String cart[];
	double total;
	int authorize;
	String ostatus;
	int orderNumber;
	String supplier;
	
	public OrderClass(String userid, String supplier, String[] cart, double total, int authorize, String ostatus) 
	{
		
		this.userid = userid;
		this.cart = cart;
		this.total = total;
		this.authorize = authorize;
		this.ostatus = ostatus;
		this.supplier = supplier;
		
		//Creating file directory, if needed.
		File orderDirectory = new File("Order");
		
		if(!orderDirectory.exists())
		{
			orderDirectory.mkdir();
			orderNumber = 1;
		}
		else
		{
			orderNumber = orderDirectory.listFiles().length + 1;
		}
		
		File orderFile = new File(orderDirectory.getName() + "/" + userid + "-" + supplier  + "-" + orderNumber + ".txt");
		
		PrintWriter orderFileWriter = null;
		
		try
		{
			orderFileWriter = new PrintWriter(orderFile);
			
			orderFileWriter.println(userid);
			
			for(String cartItems: cart)
			{	if(cartItems == null)
					break;
				orderFileWriter.println(cartItems);
			}
			
			
			
			orderFileWriter.println(total);
			orderFileWriter.println(authorize);
			orderFileWriter.println(ostatus);
			
		}
		catch(IOException e)
		{
			System.out.println("file can't be made.");
		}
		finally
		{
			orderFileWriter.close();
		}
	}
	
	public OrderClass(String fileName) throws FileNotFoundException
	{
		if(fileName.contains(".txt"))
		{
			fileName = fileName.substring(0, fileName.indexOf(".txt"));
			System.out.println(fileName);
			
		}
		
		String fileNameContent[] = fileName.split("-");
		supplier = fileNameContent[1];
		orderNumber = Integer.parseInt(fileNameContent[2]);
		
		File orderFile = new File("Order/" + fileName + ".txt");
		
		Scanner orderReader = new Scanner(orderFile);
		userid = orderReader.nextLine();
		
		cart = new String[1000];
		int size = 0;
		
		while(!orderReader.hasNextDouble())
		{
			cart[size++] = orderReader.nextLine();
		}
		
		total = orderReader.nextDouble();
		authorize = orderReader.nextInt();
		orderReader.nextLine();
		ostatus = orderReader.nextLine();
		
		orderReader.close();
			
	}
	
	public static void showOrders(String id)
	{
		File orderDirectory = new File("Order");
		File[] ordersList = orderDirectory.listFiles();
		for(File order: ordersList)
		{
			System.out.println(order.getName());
		}
	}
	
	public static LinkedList<OrderClass> getOrdersForSupplier(String id) throws FileNotFoundException
	{
		File orderDirectory = new File("Order");
		File[] ordersList = orderDirectory.listFiles();
		
		LinkedList<OrderClass> orders = new LinkedList<OrderClass>();
		
		for(File order: ordersList)
		{
			String fileName = order.getName();
			
			String[] fileNameSegments = fileName.split("-");
			if(fileNameSegments[1].equals(id))
			{
				
				OrderClass fileOrder = new OrderClass(fileName);
				orders.addLast(fileOrder);
			}
		}
		
		return orders;
	}
	
	public void printOrder()
	{
		System.out.println("Order for account:\t" + userid);
		System.out.println("Items bought:\t");
		
		for(String cartItems: cart)
		{	if(cartItems == null)
				break;
			System.out.println(cartItems);
		}
		
		System.out.println("Total:\t" + total);
		System.out.println("Authorization number:\t" + authorize);
		System.out.println("Order status:\t" + ostatus);
	}
	
	public boolean changeStatus(String status)
	{
		ostatus = status; //This will be for when we change the status of the order. 
		
		File orderFile =  new File("Order/" + userid + "-" + supplier  + "-" + orderNumber + ".txt");
		
		PrintWriter orderFileWriter = null;
		
		try
		{
			orderFileWriter = new PrintWriter(orderFile);
			
			orderFileWriter.println(userid);
			
			for(String cartItems: cart)
			{	if(cartItems == null)
					break;
				orderFileWriter.println(cartItems);
			}
			
			
			
			orderFileWriter.println(total);
			orderFileWriter.println(authorize);
			orderFileWriter.println(ostatus);
			
		}
		catch(IOException e)
		{
			System.out.println("file can't be made.");
			return false;
		}
		finally
		{
			orderFileWriter.close();
		}
		return true;
		
	}
	public String getOrderName()
	{
		return userid + "-" + supplier + "-" + orderNumber;
	}
	
	
	
	
}