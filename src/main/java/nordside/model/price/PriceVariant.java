package nordside.model.price;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import nordside.model.AbstractNamedEntity;
import nordside.model.user.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "price_variant")
public class PriceVariant extends AbstractNamedEntity {

    @OneToMany(mappedBy = "price_variant")
    @JsonManagedReference
    private Set<User> userList;

    @OneToMany(mappedBy = "price_variant",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonManagedReference
    private Set<PriceTable> priceTable;


    public PriceVariant() {
    }

    public PriceVariant(Integer id, String name) {
        super(id, name);
    }


}
