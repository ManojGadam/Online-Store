package server.models;
import common.Product;
    public class PurchaseItemsTemplateImpl extends PurchaseItemsTemplate {
        private Store store;
        private Customer customer;
        public PurchaseItemsTemplateImpl (Store store,Customer customer){
            this.store = store;
            this.customer = customer;
        }
        @Override
        protected ShoppingCart getCurrentUserCart() {
            return this.customer.getShoppingCart();
        }

        @Override
        protected void updateInventory(ShoppingCart cart) {
            for (Product product : cart.getProducts()) {
                Product stProduct = this.store.getProducts().stream().filter(x -> x.getId() == product.getId()).findFirst().orElse(null);
                assert stProduct != null;
                stProduct.setQuantity(stProduct.getQuantity() - product.getQuantity());
            }

        }

        @Override
        protected void clearCart(ShoppingCart cart) {
            this.customer.resetShoppingCart();
        }
    }

