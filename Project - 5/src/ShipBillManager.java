import java.io.FileNotFoundException;
import java.util.LinkedList;

public class ShipBillManager 
{
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
	
	public static boolean confirmShipment(OrderClass order)
	{
		String creditCard = CustomerAccount.getCreditCardForShipper(order.userid);
		boolean chargeValid = BankInterface.commitCharge(creditCard,order.total);
		if(!chargeValid)
		{
			System.out.println("The credit card was invalid!");
			return false;
		}
		
		Inventory inventoryFile = new Inventory();
		String[] output =  inventoryFile.removeReserveInventoryItems(Integer.toString(order.orderNumber));
		if(output == null)
			return false;
		else
		{
			System.out.println("following items shipped:\t");
			for(String test: output)
			{
				if(test == null)
					break;
				System.out.println(test);
			}
			order.changeStatus("Shipped");
			return true;
			
		}
	}
}
