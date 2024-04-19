Online Store Management System - Include userfactory
The program consists of several folders to organize the code:

How to run the program:
First use make clean to remove all the .class files present followed by make compile to compile the code. Then use make createJar command to create the jar file. To run server use make runServer command. For the client use make runClient command.


Folders:
common: Contains classes used by both the client and server.
controllers: Contains all the controller classes.
client: Contains the client-side programs.
models: Contains all the model classes used in the MVC pattern, including Adminstrator, Customer, ShoppingCart, Store, and User.
server: Contains the program to register the server to RMI.
views: Contains the view classes.

Files: 

Client:
Main: This is the main client-side class that contains all the client-side code. It is responsible for initiating the interaction with the server and handling the user interface.

Common:
AdminInterface: This interface defines the methods and operations that an administrator can perform.
AuthenticationInterface: This interface defines the methods for user authentication.
CustomerInterface: This interface defines the methods and operations that a customer can perform.
StoreInterface: This is a common interface which has all methods of Admin,Customer and authenication.
Product: This class represents a product in the store, with attributes such as name, description, price, and quantity.

Server:
AdminController: This controller handles all the actions and requests from administrators.
AuthenticationController: This controller is responsible for user authentication, including login, signup.
CustomerController: This controller handles all the actions and requests from customers, such as browsing products, adding items to a shopping cart, and placing orders.
FrontController: This is the main entry point for the server-side application, responsible for routing requests to the appropriate controllers.

Models:
Administrator: This model represents a user with administrator privileges, with additional attributes and methods for managing the store.
Customer: This model represents a customer, with attributes and methods for managing their shopping cart, placing orders, and interacting with the store.
ShoppingCart: This model represents a customer's shopping cart, with methods for adding, removing, and managing the products in the cart.
Store: This model represents the store, with methods for managing products , inventory and users.
User: This is the base model for all users, with common attributes and methods.
PurchaseItemsTemplate: This abstract class defines the template method for purchasing items, with abstract hook methods that can be implemented by concrete subclasses.
PurchaseItemsTemplateImpl: This concrete implementation of the PurchaseItemsTemplate class handles the logic for purchasing items, including updating the inventory and clearing the shopping cart.

RemoteStore - Contains code to register the stub to RMI


Usage
Compilation: Use the 'make compile' command to compile the code.
Cleaning: Use 'make clean' to remove compiled files.
Running the Server: Use 'make runServer' to run the server.
Running the Client: Use 'make runClient' to run the client.
Creating a jar file: Use 'make createJar' to create the jar file

User Types
Administrator: Can perform administrative tasks, such as adding other administrators, adding,updating and removing products from the store.
Customer: Can browse and purchase items from the store.

Default Administrator Login
Username: a
Password: a1
To add more administrators login as an adminstrator and add them using option 4.


Customer Login
Use option 0 at the beginning choice to register as a customer.
Enter the username and password.
Then use the same to login as customer.


Administrator Options
Add products to the store.
Update existing products.
Remove products from the store.
Add other administrators.

Customer Options
Add items to the cart.
View items in the cart.
Purchase items.
Remove items from the cart.