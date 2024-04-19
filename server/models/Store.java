package server.models;

import common.Product;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private ArrayList<Adminstrator> adminstrators;
    private ArrayList<Customer> customers;
    private ArrayList<Product> products;
    public Store(){
        this.adminstrators = new ArrayList<Adminstrator>();
        this.customers = new ArrayList<Customer>();
        this.products = new ArrayList<Product>();
    }
    public void addAdmin(Adminstrator user){
        this.adminstrators.add(user);
    }
    public List<Adminstrator> getAdministrators(){
       return this.adminstrators;
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

//    public void setProducts(ArrayList<Product> products) {
//        this.products = products;
//    }
    public void addProduct(Product product){
        this.products.add(product);
    }
    public void addProduct(ArrayList<Product> products){
        this.products.addAll(products);
    }
    public void updateProduct(Product product){
        this.getProducts().stream().filter(x -> x.getId() == product.getId()).forEach(x -> {
            x.setCategory(product.getCategory());
            x.setDescription(product.getDescription());
            x.setPrice(product.getPrice());
            x.setQuantity(product.getQuantity());
        });
    }
    public void removeProduct(Integer id){
        this.getProducts().removeIf(x->x.getId() == id);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }
}
