package common;

import server.models.ShoppingCart;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CustomerInterface extends Remote {
    String  addToCart(Product product) throws RemoteException;
    ShoppingCart viewCart() throws RemoteException;
    void purchaseItems() throws RemoteException;
    void removeItemsFromCart(int id) throws RemoteException;
}
