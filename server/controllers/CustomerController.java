package server.controllers;

import common.CustomerInterface;
import common.Product;
import server.models.Customer;
import server.models.ShoppingCart;
import server.models.Store;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class CustomerController implements CustomerInterface {
    private final Store store;
    private Customer currentUser;
    private final AuthenicateContoller authenicateContoller;
    public CustomerController(Store store, AuthenicateContoller authenicateContoller){
        this.store = store;
        this.authenicateContoller = authenicateContoller;
    }
    private Customer getCurrentUser(){
         return  authenicateContoller.getCurrentCustomer();
    }
    @Override
    public String addToCart(Product product) throws RemoteException {
        //Remove this code
        // this.currentUser = new Customer("a","a1",false);
        this.currentUser = getCurrentUser();
        this.currentUser.getShoppingCart().addProduct(product);
        return currentUser!=null ? "User exists" : "User not found";
    }

    @Override
    public ShoppingCart viewCart() throws RemoteException {
        this.currentUser = getCurrentUser();
        return this.currentUser.getShoppingCart();
    }

    @Override
    public void purchaseItems() throws RemoteException {
//        ShoppingCart cart = this.currentUser.getShoppingCart();
//        for (Product product:cart.getProducts()){
//            Product stProduct = store.getProducts().stream().filter(x->x.getId() == product.getId()).findFirst().orElse(null);
//            assert stProduct != null;
//            stProduct.setQuantity(stProduct.getQuantity()-product.getQuantity());
//        }
//        cart.setProducts(new ArrayList<Product>());
        this.currentUser = getCurrentUser();
        this.currentUser.purchaseItems(this.store);
    }

    @Override
    public void removeItemsFromCart(int id) throws RemoteException {
        this.currentUser = getCurrentUser();
        this.currentUser.getShoppingCart().getProducts().removeIf(x->x.getId() == id);
    }
}
