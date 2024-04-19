package server.models;
import common.Product;

public class Customer extends User {
    private ShoppingCart shoppingCart;

    public Customer(String name, String password, boolean isAdmin) {
        super(name, password, isAdmin);
        this.shoppingCart = new ShoppingCart();
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    public void resetShoppingCart() {
        this.shoppingCart =  new ShoppingCart();
    }
     public void purchaseItems(Store store){
        PurchaseItemsTemplate purchaseItemsTemplate = new PurchaseItemsTemplateImpl(store,this);
        purchaseItemsTemplate.purchaseItems();
    }
}
