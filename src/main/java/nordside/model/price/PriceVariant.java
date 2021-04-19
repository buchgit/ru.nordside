package nordside.model.price;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import nordside.model.AbstractNamedEntity;
import nordside.model.user.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "price_variant")
public class PriceVariant extends AbstractNamedEntity {

    @OneToMany(mappedBy = "priceVariant")
    @JsonManagedReference
    private Set<User> userList;

    @OneToMany(mappedBy = "priceVariant",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonManagedReference
    private Set<PriceTable> priceTable;


    public PriceVariant() {
    }

    public PriceVariant(Integer id, String name) {
        super(id, name);
    }

    public Set<User> getUserList() {
        return userList;
    }

    public void setUserList(Set<User> userList) {
        this.userList = userList;
    }

    public Set<PriceTable> getPriceTable() {
        return priceTable;
    }

    public void setPriceTable(Set<PriceTable> priceTable) {
        this.priceTable = priceTable;
    }
}
