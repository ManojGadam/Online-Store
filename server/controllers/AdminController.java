package server.controllers;

import common.AdminInterface;
import common.Product;
import server.models.Adminstrator;
import server.models.Store;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class AdminController implements AdminInterface {
    private final Store store;
    private final Adminstrator currentAdministrator;
    public AdminController(Store store, AuthenicateContoller authenicateContoller){
        this.store = store;
        this.currentAdministrator = authenicateContoller.getCurrentAdmin();
    }
    @Override
    public ArrayList<Product> browseProducts() throws RemoteException {
        return this.store.getProducts();
    }

    @Override
    public void addProductsAdmin(Product products)  throws RemoteException{
        this.store.addProduct(products);
    }

    @Override
    public void updateProduct(Product product) throws RemoteException {
        this.store.updateProduct(product);
    }

    @Override
    public void removeProduct(Integer id) throws RemoteException {
        this.store.removeProduct(id);
    }
    @Override
    public void addItem(Product product) throws RemoteException {
        this.currentAdministrator.addProducts(product);
    }
}
