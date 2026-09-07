Food Ordering System
The Food Ordering System is a console-based Java application developed to provide a simple and efficient platform for managing food orders. The application uses Java, JDBC, and MySQL to handle customer accounts, food items, shopping carts, orders, payments, and administrative operations.

The system provides separate functionalities for customers and administrators. Customers can create an account, log in using their phone number with OTP verification or using their email and password, browse food items by category, add food to their cart, view the cart, and place orders using different payment methods.

The administrator section allows authorized admins to manage the food menu and orders. Admins can add, view, update, and delete food items, view customer orders, and update the status of orders.

Main Features
Customer Module
Create a new customer account.
Login using phone number with 4-digit OTP verification.
Login using email and password.
Browse food by categories:
Veg
Non-Veg
Dessert
Select food items and specify quantity.
Add food items to the shopping cart.
View cart items and calculate the grand total.
Place an order using:
UPI
Card
Cash on Delivery
Automatically clear the cart after a successful order.
Logout from the customer account.
Admin Module
Secure admin login.
Add new food items.
View all available food items.
Update food name, category, and price.
Delete food items.
View all customer orders.
Update order status:
Pending
Preparing
Out for Delivery
Delivered
Cancelled
Logout from the admin panel.
Technologies Used
Java — Application development and business logic
JDBC — Database connectivity
MySQL — Database management
OOP Concepts — Classes, inheritance, methods, encapsulation
Exception Handling — Handling invalid user input and database errors
PreparedStatement — Executing parameterized SQL queries
Java Scanner — Console-based user interaction
Project Structure
