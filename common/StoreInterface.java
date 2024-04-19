package common;
import server.models.ShoppingCart;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface StoreInterface extends Remote {
    boolean authenicate(String userName,String password,boolean isAdmin) throws RemoteException;
    void register(String userName, String password, boolean isAdmin) throws RemoteException;
    ArrayList<Product> browseProducts() throws RemoteException;
    void addProductsAdmin(Product products) throws RemoteException;
    void updateProduct(Product product) throws RemoteException;
    void removeProduct(Integer id) throws RemoteException;
    String  addToCart(Product product) throws RemoteException;
    ShoppingCart viewCart() throws RemoteException;
    void purchaseItems() throws RemoteException;
    void addItem(Product product) throws RemoteException;
    void removeItemsFromCart(int id) throws RemoteException;
}
