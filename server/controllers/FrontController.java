package server.controllers;

import common.Product;
import common.StoreInterface;
import server.models.Adminstrator;
import server.models.ShoppingCart;
import server.models.Store;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class FrontController extends UnicastRemoteObject implements StoreInterface {
    private final AuthenicateContoller authenticateController;
    private final AdminController adminController;
    private final CustomerController customerController;

    public FrontController() throws RemoteException {
        Store store = new Store();
        store.addProduct(new Product(1,"Milk", "food", 2.5, 10));
        store.addProduct(new Product(2,"Shoe","Footwear",30,5));
        store.addProduct(new Product(3,"Cola","Soda",1,50));

        Adminstrator admin = new Adminstrator("a", "a1", true);
        store.addAdmin(admin);
        this.authenticateController = new AuthenicateContoller(store);
        this.adminController  = new AdminController(store,this.authenticateController);
        this.customerController = new CustomerController(store,this.authenticateController);
    }

    //Authenication
    @Override
    public boolean authenicate(String userName, String password,boolean isAdmin) throws RemoteException {
        return this.authenticateController.authenicate(userName,password,isAdmin);
    }

    @Override
    public void register(String userName, String password,boolean isAdmin) throws RemoteException {
        this.authenticateController.register(userName,password,isAdmin);
    }


    //Admin Actions

    @Override
    public ArrayList<Product> browseProducts() throws RemoteException {
        return this.adminController.browseProducts();
    }

    @Override
    public void addProductsAdmin(Product products)  throws RemoteException{
        this.adminController.addProductsAdmin(products);
    }

    @Override
    public void updateProduct(Product product) throws RemoteException {
        this.adminController.updateProduct(product);
    }

    @Override
    public void removeProduct(Integer id) throws RemoteException {
        this.adminController.removeProduct(id);
    }

    @Override
    public void addItem(Product product) throws RemoteException {
        this.adminController.addItem(product);
    }


    //customer Actions
    @Override
    public String addToCart(Product product) throws RemoteException {
       return this.customerController.addToCart(product);
    }

    @Override
    public ShoppingCart viewCart() throws RemoteException {
        return this.customerController.viewCart();
    }

    @Override
    public void purchaseItems() throws RemoteException {
        this.customerController.purchaseItems();
    }

    @Override
    public void removeItemsFromCart(int id) throws RemoteException {
        this.customerController.removeItemsFromCart(id);
    }
}
