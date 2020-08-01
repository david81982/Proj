import java.util.*;
import java.io.*;

public class MakeOrderRequest {
			
		// cart and total..
		public static String ValidRequest(CustomerAccount customer, String[] cart, double total, String supplier) throws IOException
		{
			int bankAuthorize = BankInterface.authorizePayment(customer.getCreditCard(), total);
			System.out.println(bankAuthorize);
			
			if (bankAuthorize < 0) 
			{
				while(bankAuthorize < 0)
				{
					
					System.out.println("Invalid card! Enter a new card, or type nothing to exit");
					
					Scanner r = new Scanner(System.in);
					
					String newCreditCard = r.nextLine();
					if(newCreditCard.equals(""))
						return null;
					
					bankAuthorize = BankInterface.authorizePayment(customer.getID(), total);
					
					if(bankAuthorize > 0)
					{
						System.out.println("valid card.  Editing account for new card.");
						
						//might add customerAccount to have better change later.
						CustomerAccount.changeCustomerCreditCard(customer, newCreditCard);
					}
					
				}				
			}
				
	
			
			OrderClass orderA = new OrderClass(customer.getID(),supplier, cart, total, bankAuthorize ,"Ordered");
			
			return "A new order delivery base been created with the name:\t" + orderA.getOrderName();
			
			
				
				
				
			}
		
			
			
			
	}
		
	
		//  System retrieves the customer’s credit card number from customer account.
		
	
		// over credit limit), the system prompts the customer to enter a different credit card number.
	
	