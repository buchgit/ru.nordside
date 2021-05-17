package nordside.model.price;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import nordside.model.AbstractBaseEntity;
import nordside.model.nomenclature.Nomenclature;
import nordside.model.order.Order;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "price_table")
public class PriceTable extends AbstractBaseEntity{

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference(value = "price_variant")
    @JoinColumn(name = "price_variant")
    private PriceVariant priceVariant;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //TODO @JsonBackReference(value = "nomenclature") //закомм., потому что не возвращ.номенклатура в запросе
    @JoinColumn(name = "nomenclature")
    private Nomenclature nomenclature;

    @Column
    private String unit;

    @Column
    private double price;

    @ManyToMany
    @JoinTable(name =   "order_merchandise",
                        joinColumns = @JoinColumn(name = "merchandise_id"),
                        inverseJoinColumns = @JoinColumn(name = "order_id"))
    @JsonIgnore //чтобы не отображалась пара ""orders": null"
    private Set<Order> orders;


    public PriceTable(PriceVariant priceVariant, Nomenclature nomenclature, String unit, double price) {
        this.priceVariant = priceVariant;
        this.nomenclature = nomenclature;
        this.unit = unit;
        this.price = price;
    }

    public PriceTable(Integer id, PriceVariant priceVariant, Nomenclature nomenclature, String unit, double price) {
        super(id);
        this.priceVariant = priceVariant;
        this.nomenclature = nomenclature;
        this.unit = unit;
        this.price = price;
    }

    public PriceTable() {

    }

    public PriceVariant getPriceVariant() {
        return priceVariant;
    }

    public void setPriceVariant(PriceVariant priceVariant) {
        this.priceVariant = priceVariant;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
