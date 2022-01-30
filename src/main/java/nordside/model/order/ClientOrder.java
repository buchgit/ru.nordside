package nordside.model.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import nordside.model.AbstractBaseEntity;
import nordside.model.price.PriceTable;
import nordside.model.user.User;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
public class ClientOrder extends AbstractBaseEntity {

    @Column(name = "create_date")
    @NotNull
    private Date date;

    @Column(name = "number_for1c")
    private String number_For1c;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "client")
    @JsonBackReference(value = "client")
    private User client;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "total_volume")
    private double totalVolume;

    @Column(name = "total_weight")
    private double totalWeight;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

//    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name =   "order_merchandise",
//                        joinColumns = @JoinColumn(name="order_id"),
//                        inverseJoinColumns = @JoinColumn(name = "merchandise_id"))
//    private Set<PriceTable> priceTables;

    @OneToMany(mappedBy = "clientOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ClientOrderLine> clientOrderLineTable;

    public ClientOrder() {
    }

    public ClientOrder(Date date, String number_For1c, User client, double totalAmount, double totalVolume, double totalWeight, OrderStatus status, Set<ClientOrderLine> clientOrderLineTable) {
        this.date = date;
        this.number_For1c = number_For1c;
        this.client = client;
        this.totalAmount = totalAmount;
        this.totalVolume = totalVolume;
        this.totalWeight = totalWeight;
        this.status = status;
        this.clientOrderLineTable = clientOrderLineTable;
    }

    public ClientOrder(Integer id, Date date, String number_For1c, User client, double totalAmount, double totalVolume, double totalWeight, OrderStatus status, Set<ClientOrderLine> clientOrderLineTable) {
        super(id);
        this.date = date;
        this.number_For1c = number_For1c;
        this.client = client;
        this.totalAmount = totalAmount;
        this.totalVolume = totalVolume;
        this.totalWeight = totalWeight;
        this.status = status;
        this.clientOrderLineTable = clientOrderLineTable;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNumber_For1c() {
        return number_For1c;
    }

    public void setNumber_For1c(String number_For1c) {
        this.number_For1c = number_For1c;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(double totalVolume) {
        this.totalVolume = totalVolume;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Set<ClientOrderLine> getClientOrderLineTable() {
        return clientOrderLineTable;
    }

    public void setClientOrderLineTable(Set<ClientOrderLine> clientOrderLineTable) {
        this.clientOrderLineTable = clientOrderLineTable;
    }

    public void fillFrom(ClientOrder clientOrder){
        this.setDate(clientOrder.getDate());
        this.setNumber_For1c(clientOrder.getNumber_For1c());
        this.setClient(clientOrder.getClient());
        this.setTotalAmount(clientOrder.getTotalAmount());
        this.setTotalVolume(clientOrder.getTotalVolume());
        this.setTotalWeight(clientOrder.getTotalWeight());
        this.setStatus(clientOrder.getStatus());
        this.setClientOrderLineTable(clientOrder.getClientOrderLineTable());
    }
}
