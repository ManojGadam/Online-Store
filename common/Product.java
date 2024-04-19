package common;
import java.util.Objects;
import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private int Id;
    private String description;
    private String category;
    private double price;
    private int quantity;
    public Product(int id,String description,String category,double price,int quantity){
        this.Id = id;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return Id;
    }
    public void setId(int Id){
        this.Id = Id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(Id); // Assuming id is used for hashCode calculation
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Id == product.Id;
    }
}
