import java.util.Random;

public class BankInterface 
{
	public static String[] creditCards  = {"1111222233334444", "1234123412341234", "1243564213469932"};
	

	
	//Will return -1 if the overall card is not valid.  Will return -2 if the amount is higher than 
	public static int authorizePayment(String creditCardNumber, double total)
	{
		boolean validCard = false;
		for(String creditCard: creditCards)
		{
			if(creditCardNumber.equals(creditCard))
			{
				validCard = true;
			}
		}
		
		if(!validCard)
		{
			return -1;
		}
		else if(total > 100)
		{
			return -2;
		}
		else
		{
			Random rand = new Random();
			int authorize = rand.nextInt(1000) + 1;
			return authorize;
		}
	}
	
	public static boolean commitCharge(String creditCardNumber, double total)
	{
		System.out.println(creditCardNumber);
		boolean validCard = false;
		for(String creditCard: creditCards)
		{
			if(creditCardNumber.equals(creditCard))
			{
				validCard = true;
			}
		}
		
		if(!validCard)
			return false;
		else if(total > 100)
			return false;
		else
		{
			System.out.println("Charge committed");
			return true;
		}
	}
}
