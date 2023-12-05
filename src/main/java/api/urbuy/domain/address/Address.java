package api.urbuy.domain.address;

import api.urbuy.domain.user.User;
import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "address")
@Entity(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String street;
    private String number;
    private String complement;

    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    public Address() {
    }

    public Address(Long id, String cep, String street, String number, String complement, User user) {
        this.active = true;
        this.id = id;
        this.cep = cep;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.user = user;
    }

    public Address(registerAddressData data){
        this.active = true;
        this.cep = data.cep();
        this.street = data.street();
        this.number = data.number();
        this.complement = data.complement();
    }

    public void updateData(updateAddressData data){
        if(data.cep() != null){
            this.cep = data.cep();
        }

        if(data.street() != null){
            this.street = data.street();
        }

        if(data.number() != null){
            this.number = data.number();
        }

        if(data.complement() != null){
            this.complement = data.complement();
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean active(){
        return active;
    }

    public void SetActive(){
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
