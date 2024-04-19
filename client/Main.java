package client;

import server.models.ShoppingCart;
import common.Product;
import common.StoreInterface;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try{
            Scanner scanner = new Scanner(System.in);
            StoreInterface store = (StoreInterface) Naming.lookup("//in-csci-rrpc01.cs.iupui.edu/store");
            String out = handleAuthenticate(scanner,store);
            if(out==null)return;
            else if(out.equals("1")) {
                label:
                while (true){
                    handleBrowseItems(store.browseProducts());
                    System.out.println("To add products press 1, To update product press 2, To remove product press 3,To add an admin press 4,To exit press any other button");
                    String choice = scanner.nextLine();
                    switch (choice) {
                        case "1": {
                            Product product = handleProductDetails(scanner,store);
                            store.addProductsAdmin(product);
                            //handleBrowseItems(store.browseProducts());
                            break;
                        }
                        case "2": {
                            System.out.println("Enter Product number of the item to update");
                            int id = Integer.parseInt(scanner.nextLine());
                            Product product = handleProductDetails(scanner,store);
                            product.setId(id);
                            store.updateProduct(product);
                            //handleBrowseItems(store.browseProducts());
                            break;
                        }
                        case "3": {
                            System.out.println("Enter Product number to delete");
                            int id = Integer.parseInt(scanner.nextLine());
                            store.removeProduct(id);
                            //handleBrowseItems(store.browseProducts());
                            break;
                        }
                        case "4":
                            System.out.println("Enter username");
                            String adminName = scanner.nextLine();
                            System.out.println("Enter password");
                            String adminPassword = scanner.nextLine();
                            store.register(adminName, adminPassword, true);
                            System.out.println("Admin added successfully");
                            break;
                        default:
                            break label;
                    }
                }
            }
            else{
            //handleAuthenticate(scanner,store);
            handleBrowseItems(store.browseProducts());
                label1:
                while (true){
                    System.out.println("Select an action: To add products to cart press 1, To buy items in cart press 2,To view items press 3,To remove items from cart press 4, Press anything else to exit");
                    String choice = scanner.nextLine();
                    switch (choice) {
                        case "1":
                            System.out.println("Enter Product number to add");
                            int num = Integer.parseInt(scanner.nextLine());
                            Product curr = store.browseProducts().stream().filter(x -> x.getId() == num).findFirst().orElse(null);
                            if (curr != null) {
                                System.out.println("Enter the quantity to purchase");
                                int quantity = Integer.parseInt(scanner.nextLine());
                                if (quantity > curr.getQuantity()) {
                                    System.out.println("Enter a quantity below or equal to available stock");
                                    return;
                                }
                                handlePrint(store.addToCart(new Product(curr.getId(),curr.getDescription(),curr.getCategory(),curr.getPrice(),quantity)));
                                handlePrint("Products added successfully");
                            } else {
                                System.out.println("Product not found");
                                return;
                            }
                            break;
                        case "2":
                            viewCart(store.viewCart());
                            System.out.println("Do you want to purchase all items in the cart. Press 1.Yes, 2.No, anything else to exit ");
                            String c = scanner.nextLine();
                            if (Objects.equals(c, "1")) {
                                store.purchaseItems();
                                handleBrowseItems(store.browseProducts());
                            } else if (Objects.equals(c, "2")) {
                                handleBrowseItems(store.browseProducts());
                            } else {
                                return;
                            }
                            break;
                        case "3":
                            viewCart(store.viewCart());
                            break;
                        case "4":
                            handlePrint("Enter id of the item to be deleted from cart");
                            int id = Integer.parseInt(scanner.nextLine());
                            store.removeItemsFromCart(id);
                            viewCart(store.viewCart());
                            break ;
                        default:
                            break label1;
                    }
                }
            }
        }
        catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    private static void handlePrint(String message){
        System.out.println(message);
    }
    private static void handleBrowseItems(ArrayList<Product> availableProducts)  {
        System.out.print("\033[H\033[2J");
        System.out.println("--------Products--------");
        System.out.println("ProductNumber ProductName   Quantity    Price    Category");
        for (Product product:availableProducts){
            if(product.getQuantity() < 1)continue;
            System.out.println(product.getId()+".             "+product.getDescription()+"            "+product.getQuantity()+"         "+product.getPrice()+"     "+product.getCategory());
        }
    }
    private static void viewCart(ShoppingCart shoppingCart){
        System.out.print("\033[H\033[2J");
        System.out.println("Cart");
        System.out.println("ProductNumber ProductName   Quantity    Price");
        for (Product product:shoppingCart.getProducts()){
            System.out.println(product.getId()+".             "+product.getDescription()+"            "+product.getQuantity()+"         "+product.getPrice()+"     ");
        }
        System.out.println("Total price is                     "+shoppingCart.getTotal());
    }
    private static String handleAuthenticate(Scanner scanner, StoreInterface store) throws RemoteException {
        System.out.println("Online Store");
        System.out.println("Existing Customer? If Yes press 1, Otherwise press 0");
        String out = scanner.nextLine();
        while (!Objects.equals(out, "1") && !Objects.equals(out, "0")) {
            System.out.println("Re-enter the choice. If existing customer press 1 or 0");
            out = scanner.nextLine();
        }
        if(!out.equals("1")) {
            handlePrint("To Register");
            System.out.println("Enter username");
            String username = scanner.nextLine();
            System.out.println("Enter password");
            String password = scanner.nextLine();
            store.register(username, password, false);
            System.out.println("Enter details for login");
            System.out.println("Enter username");
            String name = scanner.nextLine();
            System.out.println("Enter password");
            String password1 = scanner.nextLine();
            if (!store.authenicate(name,password1,false)){
                System.out.println("Authentication failed");
                return null;
            }
            System.out.println("Logged in successfully");
        }
        else{
            handlePrint("To Login");
            System.out.println("Enter username");
            String username = scanner.nextLine();
            System.out.println("Enter password");
            String password = scanner.nextLine();
            if (!store.authenicate(username, password, true)) {
                System.out.println("Authentication failed");
                return null;
            }
            System.out.println("Logged in successfully");
            return "1";
        }
        return "0";
    }
    private static Product handleProductDetails(Scanner scanner,StoreInterface store) throws RemoteException {
        System.out.println("Enter item name");
        String name = scanner.nextLine();
        System.out.println("Enter quantity");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter category");
        String category = scanner.nextLine();
        System.out.println("Enter price");
        double price = Double.parseDouble(scanner.nextLine());
        return new Product(store.browseProducts().size() + 1, name, category, price, quantity);
    }
}