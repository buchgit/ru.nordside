package nordside.model.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import nordside.model.AbstractBaseEntity;
import nordside.model.user.User;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends AbstractBaseEntity {

    @Column
    private String number;

    @Column
    private Date date;

    @Column
    private String numberFor1c;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client")
    @JsonBackReference(value = "client")
    private User client;

    @Column
    private double totalAmount;

    @Column
    private double totalVolume;

    @Column
    private double totalWeight;

    @Column
    private OrderStatus status;

    @OneToMany(mappedBy = "order_id",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonManagedReference(value = "order_id")
    private Set<OrderMerchandise> merchandise;


}
