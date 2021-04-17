package nordside.model.price;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import nordside.model.AbstractNamedEntity;
import nordside.model.nomenclature.Nomenclature;
import nordside.model.user.User;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "price_variant")
public class PriceVariant extends AbstractNamedEntity {

    @OneToMany(mappedBy = "price_variant")
    @JsonManagedReference
    private List<User> userList;


    public PriceVariant() {
    }

    public PriceVariant(Integer id, String name) {
        super(id, name);
    }


}
