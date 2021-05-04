package nordside.model.price;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import nordside.model.AbstractBaseEntity;
import nordside.model.nomenclature.Nomenclature;
import nordside.model.nomenclature.Unit;
import nordside.model.order.Order;

import javax.persistence.*;
import java.io.Serializable;
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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //TODO @@JsonBackReference(value = "unit") //закомм., потому что не возвращ. единица изм в запросе
    @JoinColumn(name = "unit")
    private Unit unit;

    @Column
    private double price;

    @ManyToMany
    @JoinTable(name =   "order_merchandise",
                        joinColumns = @JoinColumn(name = "merchandise_id"),
                        inverseJoinColumns = @JoinColumn(name = "order_id"))
    @JsonIgnore //чтобы не отображалась пара ""orders": null"
    private Set<Order> orders;


    public PriceTable(PriceVariant priceVariant, Nomenclature nomenclature, Unit unit, double price) {
        this.priceVariant = priceVariant;
        this.nomenclature = nomenclature;
        this.unit = unit;
        this.price = price;
    }

    public PriceTable(Integer id, PriceVariant priceVariant, Nomenclature nomenclature, Unit unit, double price) {
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
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
