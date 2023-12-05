package api.urbuy.domain.order;

import api.urbuy.domain.product.Product;
import api.urbuy.domain.product.updateProductData;
import api.urbuy.domain.user.User;
import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "order")
@Entity(name = "Order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String date;
    private String price;
    private String amount;
    private boolean active;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product")
    private Product product;

    public Order() {
    }

    public Order(Long id, String name, String date, String price, String amount, User user, Product product) {
        this.active = true;
        this.id = id;
        this.name = name;
        this.date = date;
        this.price = price;
        this.amount = amount;
        this.user = user;
        this.product = product;
    }

    public Order(registerOrderData data){
        this.name = data.name();
        this.date = data.date();
        this.price = data.price();
        this.amount = data.amount();
    }

    public void updateData(updateOrderData data){
        if(data.name() != null){
            this.name = data.name();
        }

        if(data.date() != null){
            this.date = data.date();
        }

        if(data.price() != null){
            this.price = data.price();
        }

        if(data.amount() != null){
            this.amount = data.amount();
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void softDelete(){
        this.active = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
