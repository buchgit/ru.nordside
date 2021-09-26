package nordside.model.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nordside.model.AbstractBaseEntity;
import nordside.model.nomenclature.Nomenclature;
import nordside.model.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cart_items")
public class CartItem extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "nomenclature_cart_item",joinColumns = @JoinColumn(name = "cart_item_id"),
            inverseJoinColumns = @JoinColumn(name = "nomenclature_id"))
    private Set<Nomenclature> nomenclature = new HashSet<>();

    @Column
    private Double price;

    @Column
    private Double count;

    @Column(name = "create_date")
    private LocalDate creationDate = LocalDate.now();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Nomenclature> getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Set<Nomenclature> nomenclature) {
        this.nomenclature = nomenclature;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
