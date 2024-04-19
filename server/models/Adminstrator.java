package server.models;

import common.Product;

import java.util.ArrayList;

public class Adminstrator extends User {
    private Store store;
    public Adminstrator(String name, String password, boolean isAdmin) {
        super(name, password, isAdmin);
        this.store = new Store();
    }
    public ArrayList<Product> BrowseItems(){
        return this.store.getProducts();
    }
    public void addProducts(Product product){
        this.store.addProduct(product);
    }
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
