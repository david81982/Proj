import java.util.Random;


public class MakeOrderRequest {
			
		// cart and total..
		public static int ValidRequest(CustomerAccount Customer, String[] cart, double total){
			
			
			if (Customer.getCreditCard().length() != 17 ) {
				
				System.out.println("Please log in with a valid credit card (16 digits)");
				return 0;
			}
			else if (total >100)
			{
				System.out.println("Credit limit has been reached, please try something else.");
				return 0;
			}
			else 
				{
				
				Random rand = new Random();
				int authorize = rand.nextInt(1000) + 1;
				
				
				System.out.println("Credit card is valid. Your authorization number is: " + authorize);
				
				OrderClass orderA = new OrderClass(Customer.getID(), cart, total, authorize,"Ordered");  
				
				CustomerInteraction.Orders.add(orderA);
				
				return authorize;
				
				
				
				}
		
			
			
			
		}
		
	
		//  System retrieves the customer’s credit card number from customer account.
		
	
		// over credit limit), the system prompts the customer to enter a different credit card number.
	
	
}
