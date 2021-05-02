package nordside.model.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import nordside.model.AbstractBaseEntity;
import nordside.model.price.PriceTable;

import javax.persistence.*;

@Entity
@Table(name = "order_merchandise")
public class OrderMerchandise extends AbstractBaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    @JsonBackReference(value = "order_id")
    private Order order_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "merchandise_id")
    @JsonManagedReference(value = "merchandise_id")
    private PriceTable merchandise_id;
}
