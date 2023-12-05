package api.urbuy.domain.product;

import api.urbuy.domain.order.Order;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Table(name = "product")
@Entity(name = "Product")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String price;
    private String amount;
    private boolean active;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> Order;


    public Product() {
    }

    public Product(Long id, String name, String description, String price, String amount) {
        this.active = true;
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    public Product(registerProductsData data){
        this.active = true;
        this.name = data.name();
        this.description = data.description();
        this.price = data.price();
        this.amount = data.amount();
    }

    public void updateData(updateProductData data){
        if(data.name() != null){
            this.name = data.name();
        }

        if(data.description() != null){
            this.description = data.description();
        }

        if(data.price() != null){
            this.price = data.price();
        }

        if(data.amount() != null){
            this.amount = data.amount();
        }
    }

    public void softDelete(){
        this.active = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
