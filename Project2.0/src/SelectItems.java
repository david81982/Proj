
import java.io.*;
import java.util.Scanner;
//Need finish method check, to check if there are enough items and return true or false.
//Maybe find a way for Catalog class to display information.
//Case 9: needs to pass to check out and pass cart and total.

public class SelectItems {
	
	
	CustomerAccount Customer;
	int i = 0;
	int j;
	int amount;
	String[] cart = new String[10];
	int choice = -1;
	double total = 0.00;
	static Scanner sc = new Scanner(System.in);
	Catalog c = new Catalog();
	
	
	
	 void shop()throws IOException {
		while(choice != 0) {
			c.display();
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					System.out.println("How many apples would you like?");
					amount = sc.nextInt();
					
					for(j = 0; j < amount; j++) {
							cart[i] = "Apple";
							total = total + 1.00;
							i++;
					}
					break;
				case 2:
					System.out.println("How many hot dogs would you like?");
					amount = sc.nextInt();
					
					for(j = 0; j < amount; j++) {
							cart[i] = "Hot dogs";
							total = total + 2.00;
							i++;
					}
					break;
				case 9:
					if (Customer.getID() == "")
					{
						System.out.println("Please login with a valid id");
						break;
					}
					else
					{
					MakeOrderRequest.ValidRequest(Customer, cart, total);
					
					break;
					}
				
					
				case 0:
					break;
				}
		}
	}
}
class Catalog{
	void display() {
		System.out.println("Select Items to add to Cart");
		System.out.println("1. Apple: $1.00");
		System.out.println("2. Hot Dogs: $2.00");
		System.out.println("9. Check out");
		System.out.println("0. Exit without checking out");
	}
}
