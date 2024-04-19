package server.models;

import common.Product;

import java.io.Serializable;
import java.util.ArrayList;

public class ShoppingCart implements Serializable {
   private ArrayList<Product> products;

    public ShoppingCart() {
        this.products = new ArrayList<Product>();
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
    public double getTotal(){
        double total=0;
        for (Product product : this.products) {
            total += product.getPrice() * product.getQuantity();
        }
        return total;
    }
    public void setProducts(ArrayList<Product> products){
        this.products = products;
    }
}
