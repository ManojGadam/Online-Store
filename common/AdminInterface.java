package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface AdminInterface extends Remote {
    ArrayList<Product> browseProducts() throws RemoteException;
    void addProductsAdmin(Product products) throws RemoteException;
    void updateProduct(Product product) throws RemoteException;
    void removeProduct(Integer id) throws RemoteException;
    void addItem(Product product) throws RemoteException;
}
