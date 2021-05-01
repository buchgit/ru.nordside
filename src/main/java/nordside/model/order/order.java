package nordside.model.order;

import nordside.model.AbstractBaseEntity;
import nordside.model.price.PriceTable;
import nordside.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class order extends AbstractBaseEntity {

    @Column
    private String number;

    @Column
    private Date date;

    @Column
    private String numberFor1c;

    @Column
    private User client;

    private List<PriceTable> merchandiseTable;

    @Column
    private double totalAmount;

    @Column
    private double totalVolume;

    @Column
    private double totalWeight;

    @Column
    @Enumerated
    private OrderStatus status;


}
