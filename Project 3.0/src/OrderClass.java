
public class OrderClass {
	
	// System stores a delivery order containing order details, 
	//  customer Id, purchase authorization number, and order status as “ordered”.
	String userid;
	String cart[];
	double total;
	int authorize;
	String ostatus;
	
	public OrderClass(String userid,String[] cart, double total, int authorize, String ostatus) {
		
		this.userid = userid;
		this.cart = cart;
		this.total = total;
		this.authorize = authorize;
		this.ostatus = ostatus;
		
		
	}
	
	
	
	
}
