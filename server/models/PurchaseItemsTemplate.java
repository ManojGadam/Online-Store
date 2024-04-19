package server.models;

public abstract class PurchaseItemsTemplate {
    public final void purchaseItems()  {
        ShoppingCart cart = getCurrentUserCart();
        updateInventory(cart);
        clearCart(cart);
    }

    protected abstract ShoppingCart getCurrentUserCart();
    protected abstract void updateInventory(ShoppingCart cart);
    protected abstract void clearCart(ShoppingCart cart);
}
