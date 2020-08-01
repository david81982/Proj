
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
	String[] cart = new String[1000];
	int choice = -1;
	double total = 0.00;
	String supplier = null;
	static Scanner sc = new Scanner(System.in);
	Catalog c = new Catalog();
	
	
	
	 void shop(String supplier)throws IOException {
		 
		 Catalog supplierCatalog = new Catalog();
		 supplierCatalog.display();
		 
		 
		 this.supplier = supplier;
		 choice = sc.nextInt();
		 while(choice != 0) {
			
			if(choice < supplierCatalog.size + 1)
			{

				System.out.println("How many " + supplierCatalog.content[choice] + "s would you like?");
				amount = sc.nextInt();
				
				for(j = 0; j < amount; j++) {
						cart[i] = supplierCatalog.content[choice];
						total = total + supplierCatalog.costs[choice];
						i++;
				}
				System.out.println("Items added");
				choice = sc.nextInt();
			}
		}
	}
}
class Catalog{
	
	String[] content;
	Double[] costs;
	int size;
	public Catalog()
	{
		content = new String[100];
		costs = new Double[100];
		Scanner fileReader = null;
		File catalogFile = new File("Catalog/catalog.txt");
		try
		{
			int count = 0;
			fileReader = new Scanner(catalogFile);
			while(fileReader.hasNext())
			{
				while(!fileReader.hasNextInt())
					fileReader.next();
				
				count = fileReader.nextInt();
				content[count] = fileReader.next();
				costs[count] = fileReader.nextDouble();
			}
			size = count;
			
		}
		catch(IOException e)
		{
			System.out.println("Catalog had an error.");
		}
		finally
		{
			
			fileReader.close();
		}
	}
	
	public void display() 
	{
		System.out.println("catalog");
		for(int i = 1; i < size + 1; i++)
		{
			System.out.println(i + ". Item:" +  content[i] + " Cost: $" + costs[i]);
		}
	}
}
