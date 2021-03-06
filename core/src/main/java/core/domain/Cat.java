package core.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cat extends BaseEntity<Long> {
    String name, breed;
    Integer catYears;

    public Cat() {

    }

    public Cat(Long id, String name, String breed, Integer catYears) {
        this.setId(id);
        this.name = name;
        this.breed = breed;
        this.catYears = catYears;
    }

    @OneToMany(mappedBy = "cat", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<Purchase> purchases;

    public Set<Purchase> getPurchases() {
        purchases = purchases == null ? new HashSet<>() : purchases;
        return Collections.unmodifiableSet(purchases);
    }

    public void addPurchase(Customer customer, int price, Date date, int review) {
        Purchase purchase = new Purchase(new CustomerPurchasePrimaryKey(customer.getId(), this.getId()), customer, this, price, date, review);
        purchases.add(purchase);
    }

    @OneToMany(mappedBy = "cat", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<Toy> toys;

    public Set<Toy> getToys() {
        return toys;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public Integer getCatYears() {
        return catYears;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setCatYears(Integer catYears) {
        this.catYears = catYears;
    }

    public Integer getHumanYears(){
        return catYears*15;
    }

    @Override
    public String toString() {
        return super.toString() +
                " Cat{name: " + this.name +
                "; breed: " + this.breed +
                "; catYears: " + this.catYears +
                "}";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Cat && this.getId().equals(((Cat) obj).getId());
    }
}
