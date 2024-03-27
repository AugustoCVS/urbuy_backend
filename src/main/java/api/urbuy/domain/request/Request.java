package api.urbuy.domain.request;

import api.urbuy.domain.purchase.registerPurchaseData;
import api.urbuy.domain.purchase.updatePurchaseData;
import api.urbuy.domain.user.User;
import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "request")
@Entity(name = "Request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String date;
    private String category;
    private String price;
    private String amount;

    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    public Request() {
    }

    public Request(Long id, String name, String date, String category, String price, String amount, User user) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.category = category;
        this.price = price;
        this.amount = amount;
        this.user = user;
    }

    public Request(registerRequestData data){
        this.name = data.name();
        this.date = data.date();
        this.price = data.price();
        this.amount = data.amount();
        this.category = data.category();
    }

    public void updateData(updateRequestData data){
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

        if(data.category() != null){
            this.category = data.category();
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
        Request request = (Request) o;
        return Objects.equals(id, request.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
