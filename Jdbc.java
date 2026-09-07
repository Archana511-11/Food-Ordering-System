package Food;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc {
    public static void CreateUserAccount(String name, long phoneno,
            String Email, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&&password=root");
            PreparedStatement ps = con.prepareStatement("INSERT INTO useraccount VALUES (?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setLong(2, phoneno);
            ps.setString(3, Email);
            ps.setString(4, password);
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Account created successfully");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean emailLogin(String mail, String password) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&password=root");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM useraccount WHERE mail=? AND password=?");
            ps.setString(1, mail);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public static boolean phoneLogin(long phoneno) {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&&password=root");
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM useraccount WHERE phoneno = ?");
	        ps.setLong(1, phoneno);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	        	Food_ordering.name = rs.getString("name");
	        	Food_ordering.phoneno = rs.getLong("phoneno");
	        	Food_ordering.mail = rs.getString("mail");
	        	Food_ordering.password = rs.getString("password");
	            return true;

	        } else {

	            return false;
	        }

	    } catch (ClassNotFoundException | SQLException e) {

	        e.printStackTrace();
	    }

	    return false;
	}
    public static void displayVegFood() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&&password=root");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM food WHERE category = ?");
            ps.setString(1, "Veg");
            ResultSet rs = ps.executeQuery();
            System.out.println("--------------------------------------");
            System.out.println("ID\tFood Name\tPrice");
            System.out.println("--------------------------------------");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("food_id") + "\t" +
                        rs.getString("food_name") + "\t" +
                        rs.getDouble("price"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static void displayNonVegFood() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&&password=root");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM food WHERE category = ?");
            ps.setString(1, "Non-Veg");
            ResultSet rs = ps.executeQuery();
            System.out.println("--------------------------------------");
            System.out.println("ID\tFood Name\tPrice");
            System.out.println("--------------------------------------");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("food_id") + "\t" +
                        rs.getString("food_name") + "\t" +
                        rs.getDouble("price"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static void displayDessert() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&&password=root");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM food WHERE category = ?");
            ps.setString(1, "Dessert");
            ResultSet rs = ps.executeQuery();
            System.out.println("--------------------------------------");
            System.out.println("ID\tFood Name\tPrice");
            System.out.println("--------------------------------------");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("food_id") + "\t" +
                        rs.getString("food_name") + "\t" +
                        rs.getDouble("price"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addToCart(long phoneno, int food_id,int quantity, String category) {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&&password=root");
    		PreparedStatement ps = con.prepareStatement("SELECT food_name, price FROM food WHERE food_id = ? AND category = ?");
    		ps.setInt(1, food_id);
    		ps.setString(2, category);

    		ResultSet rs = ps.executeQuery();

    		if (rs.next()) {

    		String foodName = rs.getString("food_name");
    		double price = rs.getDouble("price");
    		double total = price * quantity;

    		PreparedStatement ps1 = con.prepareStatement("INSERT INTO cart (phoneno, food_id, food_name, price, quantity, total) VALUES (?, ?, ?, ?, ?, ?)");

    		ps1.setLong(1, phoneno);
    		ps1.setInt(2, food_id);
    		ps1.setString(3, foodName);
    		ps1.setDouble(4, price);
    		ps1.setInt(5, quantity);
    		ps1.setDouble(6, total);

    		int result = ps1.executeUpdate();

    		if (result > 0) {

    		System.out.println("Food added to cart successfully");
    		System.out.println("Food     : " + foodName);
    		System.out.println("Price    : ₹" + price);
    		System.out.println("Quantity : " + quantity);
    		System.out.println("Total    : ₹" + total);
    		}
}
    		else {
    			System.out.println("Invalid Food ID for " + category + " category");
}
    	}

    		catch (ClassNotFoundException | SQLException e) {
    			e.printStackTrace();
}
}
    public static void viewCart(long phoneno) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&&password=root");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cart WHERE phoneno = ?");
            ps.setLong(1, phoneno);
            ResultSet rs = ps.executeQuery();
            double grandTotal = 0;
            boolean found = false;
            System.out.println();
            System.out.println("--------------- CART ---------------");
            System.out.println("Food Name\tPrice\tQuantity\tTotal");
            System.out.println("------------------------------------");
            while (rs.next()) {
                found = true;
                String foodName = rs.getString("food_name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                double total = rs.getDouble("total");
                System.out.println(
                        foodName + "\t" +
                        price + "\t" +
                        quantity + "\t\t" +
                        total);
                grandTotal = grandTotal + total;
            }
            if (!found) {
                System.out.println("Cart is empty");
            } else {
                System.out.println("------------------------------------");
                System.out.println("Grand Total = ₹" + grandTotal);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static void payment(long phoneno, String paymentMethod) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&&password=root");
            PreparedStatement ps = con.prepareStatement("SELECT SUM(total) FROM cart WHERE phoneno = ?");

            ps.setLong(1, phoneno);

            ResultSet rs = ps.executeQuery();

            double grandTotal = 0;

            if (rs.next()) {
                grandTotal = rs.getDouble(1);
            }

            if (grandTotal == 0) {

                System.out.println("Cart is empty");

                rs.close();
                ps.close();
                con.close();

                return;
            }

           
            PreparedStatement ps1 = con.prepareStatement("INSERT INTO orders (phoneno, total, payment_method, order_status) VALUES (?, ?, ?, ?)");

            ps1.setLong(1, phoneno);
            ps1.setDouble(2, grandTotal);
            ps1.setString(3, paymentMethod);
            ps1.setString(4, "Confirmed");

            int result = ps1.executeUpdate();

            if (result > 0) {

                System.out.println();
                System.out.println("Payment Successful");
                System.out.println("Payment Method : " + paymentMethod);
                System.out.println("Amount         : ₹" + grandTotal);
                System.out.println("Order Confirmed Successfully!");
            }

            PreparedStatement ps2 = con.prepareStatement("DELETE FROM cart WHERE phoneno = ?");
            ps2.setLong(1, phoneno);
            ps2.executeUpdate();
            rs.close();
            

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }
    }
    public static void fixCartTable() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&password=root");

            PreparedStatement ps1 = con.prepareStatement("ALTER TABLE cart MODIFY cart_id INT NOT NULL AUTO_INCREMENT");
            ps1.executeUpdate();
            PreparedStatement ps2 = con.prepareStatement("ALTER TABLE orders MODIFY order_id INT NOT NULL AUTO_INCREMENT");
            ps2.executeUpdate();
           // System.out.println("Table configuration fixed successfully");
            

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean adminLogin(String username, String password) {

        boolean result = false;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&password=root");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM admin WHERE username=? AND password=?");

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void viewAllUsers() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&password=root");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM userinterface");
            ResultSet rs = ps.executeQuery();
            System.out.println();
            System.out.println("------------ ALL USERS ------------");

            while (rs.next()) {

                System.out.println("Name     : " + rs.getString("name"));
                System.out.println("Phone    : " + rs.getLong("phoneno"));
                System.out.println("Email    : " + rs.getString("email"));
                System.out.println("Password : " + rs.getString("password"));
                System.out.println("-----------------------------------");
            }

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }
    }
    public static void searchUser(long phone) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&password=root");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM userinterface WHERE phoneno=?");
            ps.setLong(1, phone);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println();
                System.out.println("User Found");
                System.out.println("Name     : " + rs.getString("name"));
                System.out.println("Phone    : " + rs.getLong("phoneno"));
                System.out.println("Email    : " + rs.getString("email"));
                System.out.println("Password : " + rs.getString("password"));

            } else {

                System.out.println("User not found");
            }
        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }
    }
    public static void updateUser(long phone, String name,
            String email, String password) {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");

    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&password=root");

    		PreparedStatement ps = con.prepareStatement("UPDATE userinterface SET name=?, email=?, password=? WHERE phoneno=?");

    		ps.setString(1, name);
    		ps.setString(2, email);
    		ps.setString(3, password);
    		ps.setLong(4, phone);

    		int rows = ps.executeUpdate();
    		if (rows > 0) {
    			System.out.println("User updated successfully");
    			} else {
    			System.out.println("User not found");
    			}

    			ps.close();
    			con.close();

    			} catch (ClassNotFoundException | SQLException e) {

    			e.printStackTrace();
    			}
    			}
    public static void deleteUser(long phone) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&password=root");

            PreparedStatement ps = con.prepareStatement("DELETE FROM userinterface WHERE phoneno=?");
            ps.setLong(1, phone);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("User deleted successfully");
            } else {
                System.out.println("User not found");
            }

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }
    }
    public static void addFood(String foodName, String category, double price) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&password=root");

            PreparedStatement ps = con.prepareStatement("INSERT INTO food(food_name, category, price) VALUES (?, ?, ?)");

            ps.setString(1, foodName);
            ps.setString(2, category);
            ps.setDouble(3, price);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Food added successfully");
            } else {
                System.out.println("Food not added");
            }
        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }
    }
    public static void viewFood() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&password=root");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM food");

            ResultSet rs = ps.executeQuery();

            System.out.println("---------------------------------------------");
            System.out.println("ID\tFood Name\tCategory\tPrice");
            System.out.println("---------------------------------------------");

            while (rs.next()) {

                int foodId = rs.getInt("food_id");
                String foodName = rs.getString("food_name");
                String category = rs.getString("category");
                double price = rs.getDouble("price");

                System.out.println(foodId + "\t" + foodName + "\t"
                        + category + "\t" + price);
            }

            System.out.println("---------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void updateFood(int foodId, String foodName,
            String category, double price) {
    	try {

    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&password=root");
    		PreparedStatement ps = con.prepareStatement("UPDATE food SET food_name=?, category=?, price=? WHERE food_id=?");
    		ps.setString(1, foodName);
    		ps.setString(2, category);
    		ps.setDouble(3, price);
    		ps.setInt(4, foodId);

    		int result = ps.executeUpdate();

    		if (result > 0) {
    		System.out.println("Food updated successfully");
    		} else {
    		System.out.println("Food ID not found");
    		}
    		} catch (ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    		}
    		}
    public static void deleteFood(int foodId) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&password=root");

            PreparedStatement ps = con.prepareStatement("DELETE FROM food WHERE food_id=?");

            ps.setInt(1, foodId);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Food deleted successfully");
            } else {
                System.out.println("Food ID not found");
            }

            ps.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static void viewAllOrders() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&password=root");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("--------------------------------");
                System.out.println("Order ID      : "+ rs.getInt("order_id"));
                System.out.println("Phone Number  : "+ rs.getLong("phoneno"));
                System.out.println("Total Amount  : "+ rs.getDouble("total_amount"));
                System.out.println("Payment Method: " + rs.getString("payment_method"));
                System.out.println("Order Status  : "+ rs.getString("order_status"));
            }

            System.out.println("--------------------------------");

            rs.close();
            ps.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }
    }
    public static void updateOrderStatus(int orderId, String status) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?user=root&password=root");

            PreparedStatement ps = con.prepareStatement("UPDATE orders SET order_status=? WHERE order_id=?");

            ps.setString(1, status);
            ps.setInt(2, orderId);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Order status updated successfully");

            } else {

                System.out.println("Order ID not found");
            }
        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }
    }
    public static void fixFoodTable() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project2?user=root&password=root"
            );

            Statement st = con.createStatement();

            st.executeUpdate("ALTER TABLE food MODIFY food_id INT NOT NULL AUTO_INCREMENT");

            System.out.println("Food table updated successfully");

            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}