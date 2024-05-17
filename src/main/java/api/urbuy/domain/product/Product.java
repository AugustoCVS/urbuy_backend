package api.urbuy.domain.product;

import api.urbuy.domain.purchase.Purchase;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Table(name = "product")
@Entity(name = "Product")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String price;
    private int amount;
    private String img;
    private String brand;
    private String category;
    private String description;
    private boolean active;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Purchase> Purchase;


    public Product() {
    }

    public Product(Long id, String name, String price, int amount, String img, String brand, String category, String description) {
        this.active = true;
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.img = img;
        this.brand = brand;
        this.category = category;
        this.description = description;
    }

    public Product(registerProductsData data){
        this.active = true;
        this.name = data.name();
        this.description = data.description();
        this.price = data.price();
        this.amount = data.amount();
        this.img = data.img();
        this.brand = data.brand();
        this.category = data.category();
    }

    public void updateData(updateProductData data){
        if(data.name() != null){
            this.name = data.name();
        }

        if(data.price() != null){
            this.price = data.price();
        }

        if(data.amount() != 0){
            this.amount = data.amount();
        }

        if(data.img() != null){
            this.img = data.img();
        }

        if(data.brand() != null){
            this.brand = data.brand();
        }

        if(data.category() != null){
            this.category = data.category();
        }

        if(data.description() != null){
            this.description = data.description();
        }
    }

    public void softDelete(){
        this.active = false;
    }

    public void decreaseQuantity(int amount) {
        if (this.amount >= amount) {
            this.amount -= amount;
        } else {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque");
        }
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
