package Food;
import java.util.*;
public class Food_ordering extends Otp{
	static Scanner sc=new Scanner(System.in);
	static String name;
    static long phoneno;
    static String mail;
    static String password;
	public static void CreateUserAccount() {
		System.out.print("Enter the name: ");
		name=sc.next();
		//System.out.println(name);
		System.out.print("Enter your phone number: ");
		phoneno=sc.nextLong();
		//System.out.println(phoneno);
		System.out.print("Enter your Email: ");
		mail=sc.next();
		while(!mail.contains("@")) {
			System.out.println("Enter valid Email");
			System.out.print("Enter your Email: ");
			mail=sc.next();
		}
		//System.out.println(mail);
		System.out.print("Enter your password: ");
		password=sc.next();
		//System.out.println(password);
		Jdbc.CreateUserAccount(name, phoneno, mail, password);
		System.out.println("your user account created successfully");
		System.out.println();
		//Userpage();	
	}
	public static void Login() {
		while(true) {
		System.out.println("Login Page");
		System.out.println("--------------------------------------");
		System.out.println("1.Login with phoneno");
		System.out.println("2.Login with Email");
		System.out.println("3.Back");
		System.out.println("4.Exit");
		try {
		int choice=sc.nextInt();
		switch(choice){
		case 1:
			Phoneno();
			break;
		case 2:
			Email();
			break;
		case 3:
			Food_orderingpage();
			break;
		case 4:
			System.out.println("out of appilication");
		     System.exit(0);
		default:
            System.out.println("Invalid option");
		}
		}
		catch (InputMismatchException e) {
			System.out.println("enter only number");
			sc.nextLine();
		}
		
		}
	}
	public static void Phoneno() {
		System.out.println("Login with Phoneno");
		System.out.println("--------------------------------------");
		System.out.print("Enter your phone number: ");
		long phoneno=sc.nextLong();
		boolean result=Jdbc.phoneLogin(phoneno);
		if(result) {
		//System.out.println(phone_number);
		Food_ordering u= new Food_ordering();
		//u.Otp(1122);
		u.Get_otp();
		System.out.println("opt is: "+u.Otp1());
		System.out.print("Enter the OTP: ");
		int otp=sc.nextInt();
		int count=0;
		int temp=otp;
		while(temp!=0) {
			count++;
			temp=temp/10;
		}
			if(count==4) {
				if(otp==u.Otp1()) {
					System.out.println("opt is verified");
					 System.out.println("login sucessfully");
					 HomePage();
			}
				else {
					System.out.println("invalid otp");
				}
		}
			else {
				System.out.println("enter 4 digite otp");
			}
		//System.out.println(otp);
		}
		else {
			System.out.println("phone no is not found");
		}
	}
	 public static void Email() {

	        System.out.println("Login with EmailId");
	        System.out.println("--------------------------------------");

	        System.out.print("Enter your EmailId: ");
	        String Email = sc.next();

	        while (!Email.contains("@")) {
	            System.out.println("Enter valid Email");
	            System.out.print("Enter your EmailId: ");
	            Email = sc.next();
	        }
	        System.out.print("Enter your password: ");
	        String password1 = sc.next();
	        boolean result = Jdbc.emailLogin(Email, password1);

	        if (result) {
	            System.out.println("Login successful");
	            HomePage();
	        } else {
	            System.out.println("Invalid Email or Password");
	        }
	    }
	 public static void HomePage() {
		    while (true) {
		        System.out.println();
		        System.out.println("Home Page");
		        System.out.println("--------------------------------");
		        System.out.println("1. Veg");
		        System.out.println("2. Non-Veg");
		        System.out.println("3. Dessert");
		        System.out.println("4. View Cart");
		        System.out.println("5. Payment");
		        System.out.println("6. Logout");
		        try {
		            System.out.print("Enter your choice: ");
		            int choice = sc.nextInt();
		            switch (choice) {
		            case 1:
		                Veg();
		                break;
		            case 2:
		                NonVeg();
		                break;
		            case 3:
		                Dessert();
		                break;
		            case 4:
		                Jdbc.viewCart(phoneno);
		                break;
		            case 5:
		                Payment();
		                break;
		            case 6:
		                System.out.println("Logout successful");
		                return;
		            default:
		                System.out.println("Invalid option");
		            }
		        } catch (InputMismatchException e) {
		            System.out.println("Enter only numbers");
		            sc.nextLine();
		        }
		    }
		}
	 public static void Veg() {

		    System.out.println();
		    System.out.println("Veg Food");
		    System.out.println("--------------------------------");

		    Jdbc.displayVegFood();
		    SelectFood("Veg");
		}

		public static void NonVeg() {

		    System.out.println();
		    System.out.println("Non-Veg Food");
		    System.out.println("--------------------------------");

		    Jdbc.displayNonVegFood();
		    SelectFood("Non-Veg");
		}

		public static void Dessert() {

		    System.out.println();
		    System.out.println("Dessert");
		    System.out.println("--------------------------------");

		    Jdbc.displayDessert();
		    SelectFood("Dessert");
		}
		public static void SelectFood(String category) {

		    try {

		        System.out.print("Enter Food ID: ");
		        int food_id = sc.nextInt();

		        System.out.print("Enter Quantity: ");
		        int quantity = sc.nextInt();

		        if (quantity <= 0) {
		            System.out.println("Quantity should be greater than 0");
		            return;
		        }

		        Jdbc.addToCart(phoneno, food_id, quantity, category);

		    } catch (InputMismatchException e) {

		        System.out.println("Enter only numbers");
		        sc.nextLine();
		    }
		}
	 public static void Payment() {

		    while (true) {

		        System.out.println();
		        System.out.println("------------ PAYMENT ------------");

		        System.out.println("1. UPI");
		        System.out.println("2. Card");
		        System.out.println("3. Cash on Delivery");
		        System.out.println("4. Back");

		        try {

		            System.out.print("Enter your choice: ");
		            int choice = sc.nextInt();

		            switch (choice) {

		            case 1:
		                System.out.println("UPI selected");
		                Jdbc.payment(phoneno, "UPI");
		                return;

		            case 2:
		                System.out.println("Card selected");
		                Jdbc.payment(phoneno, "Card");
		                return;

		            case 3:
		                System.out.println("Cash on Delivery selected");
		                Jdbc.payment(phoneno, "Cash on Delivery");
		                return;

		            case 4:
		                return;

		            default:
		                System.out.println("Invalid option");
		            }

		        } catch (InputMismatchException e) {

		            System.out.println("Enter only numbers");
		            sc.nextLine();
		        }
		    }
		}
	 public static void Mainpage() {
	    	while(true) {
	    		System.out.println("Food App");
	    		System.out.println("---------------------------------");
	    		System.out.println("1.Customer");
	    		System.out.println("2.Admin");
	    		System.out.println("3.Exit");
	    		try {
	    			int choice=sc.nextInt();
	    			switch(choice){
	    			case 1:
	    				Food_orderingpage(); 
	    				break;
	    			case 2:
	    				AdminLogin();
	    				break;
	    			case 3:
	    				System.out.println("out of food app");
	    				System.exit(0);
	    				
	    		}
	    	}
	    		catch (InputMismatchException e) {
	    			System.out.println("enter only number");
	    			sc.nextLine();
	    		}
		}
	    }
	 public static void AdminLogin() {

		    System.out.println();
		    System.out.println("------------ ADMIN LOGIN ------------");

		    System.out.print("Enter Admin Username: ");
		    String username = sc.next();

		    System.out.print("Enter Admin Password: ");
		    String password = sc.next();

		    boolean result = Jdbc.adminLogin(username, password);

		    if (result) {
		        System.out.println("Admin Login Successful");
		        Adminpage();
		    } else {
		        System.out.println("Invalid Username or Password");
		    }
		}
	 public static void Adminpage() {

		    while (true) {

		        System.out.println();
		        System.out.println("------------ ADMIN PAGE ------------");
		        System.out.println("1. Add Food");
		        System.out.println("2. View Food");
		        System.out.println("3. Update Food");
		        System.out.println("4. Delete Food");
		        System.out.println("5. View Orders");
		        System.out.println("6. Update Order Status");
		        System.out.println("7. Logout");

		        try {

		            System.out.print("Enter your choice: ");
		            int choice = sc.nextInt();

		            switch (choice) {

		            case 1:
		                AddFood();
		                break;

		            case 2:
		                ViewFood();
		                break;

		            case 3:
		                UpdateFood();
		                break;

		            case 4:
		                DeleteFood();
		                break;

		            case 5:
		                ViewOrders();
		                break;

		            case 6:
		                UpdateOrderStatus();
		                break;

		            case 7:
		                System.out.println("Admin Logout Successful");
		                return;

		            default:
		                System.out.println("Invalid option");
		            }

		        } catch (InputMismatchException e) {

		            System.out.println("Enter only numbers");
		            sc.nextLine();
		        }
		    }
		}
	 public static void AddFood() {

		    System.out.println();
		    System.out.println("------------ ADD FOOD ------------");

		    System.out.print("Enter Food Name: ");
		    String foodName = sc.next();

		    System.out.print("Enter Category: ");
		    String category = sc.next();

		    System.out.print("Enter Price: ");
		    double price = sc.nextDouble();

		    Jdbc.addFood(foodName, category, price);
		}
	 public static void ViewFood() {

		    System.out.println();
		    System.out.println("------------ VIEW FOOD ------------");

		    Jdbc.viewFood();
		}
	 public static void UpdateFood() {

		    System.out.println();
		    System.out.println("------------ UPDATE FOOD ------------");

		    System.out.print("Enter Food ID: ");
		    int foodId = sc.nextInt();
		    sc.nextLine();

		    System.out.print("Enter New Food Name: ");
		    String foodName = sc.nextLine();

		    System.out.print("Enter New Category: ");
		    String category = sc.nextLine();

		    System.out.print("Enter New Price: ");
		    double price = sc.nextDouble();

		    Jdbc.updateFood(foodId, foodName, category, price);
		}
	 public static void DeleteFood() {

		    System.out.println();
		    System.out.println("------------ DELETE FOOD ------------");

		    System.out.print("Enter Food ID: ");
		    int foodId = sc.nextInt();

		    Jdbc.deleteFood(foodId);
		}
	 public static void ViewOrders() {

		    System.out.println();
		    System.out.println("------------ ALL ORDERS ------------");

		    Jdbc.viewAllOrders();
		}
	 public static void UpdateOrderStatus() {

		    System.out.println();
		    System.out.println("------------ UPDATE ORDER STATUS ------------");

		    System.out.print("Enter Order ID: ");
		    int orderId = sc.nextInt();

		    System.out.println("1. Pending");
		    System.out.println("2. Preparing");
		    System.out.println("3. Out for Delivery");
		    System.out.println("4. Delivered");
		    System.out.println("5. Cancelled");

		    System.out.print("Enter status choice: ");
		    int choice = sc.nextInt();

		    String status;

		    switch (choice) {

		    case 1:
		        status = "Pending";
		        break;

		    case 2:
		        status = "Preparing";
		        break;

		    case 3:
		        status = "Out for Delivery";
		        break;

		    case 4:
		        status = "Delivered";
		        break;

		    case 5:
		        status = "Cancelled";
		        break;

		    default:
		        System.out.println("Invalid status");
		        return;
		    }

		    Jdbc.updateOrderStatus(orderId, status);
		}
	public static void main(String[] args) {
		Jdbc.fixFoodTable();
		Jdbc.fixCartTable();
		Mainpage();
	}
	public static void Food_orderingpage() {
		while(true) {
		System.out.println("Food Ordering System");
		System.out.println("-----------------------------------------");
		System.out.println("1.Create Account");
		System.out.println("2.Login");
		System.out.println("3.Back");
		System.out.println("4.Exit");
		int choose=sc.nextInt();
		try {
		switch(choose){
		case 1:
			CreateUserAccount();
			break;
		case 2:
			Login();
			break;
		case 3:
			 Mainpage();
			break;
		case 4:
            System.out.println("Exit from food ordering page");
            System.exit(0);

        default:
            System.out.println("Invalid option");
        }
        }
        catch (InputMismatchException e) {
			System.out.println("enter only number");
			sc.nextLine();
		}
		}
	}

}
