package nordside.model.price;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import nordside.model.AbstractBaseEntity;
import nordside.model.nomenclature.Nomenclature;
import nordside.model.order.ClientOrder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "price_table")
public class PriceTable extends AbstractBaseEntity implements Comparable{

    //@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference(value = "price_variant")
    @JoinColumn(name = "price_variant")
    private PriceVariant priceVariant;

    //@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ManyToOne(cascade = CascadeType.PERSIST)
    //TODO @JsonBackReference(value = "nomenclature") //закомм., потому что не возвращ.номенклатура в запросе
    @JoinColumn(name = "nomenclature")
    private Nomenclature nomenclature;

    @Column
    private String unit;

    @Column
    private double price;

    @Column
    private double count;

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getSumma() {
        return summa;
    }

    public void setSumma(double summa) {
        this.summa = summa;
    }

    @Column
    private double summa;



//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name =   "order_merchandise",
//                        joinColumns = @JoinColumn(name = "merchandise_id"),
//                        inverseJoinColumns = @JoinColumn(name = "order_id"))
//    @JsonIgnore //чтобы не отображалась пара ""clientOrders": null"
//    private Set<ClientOrder> clientOrders;


    public PriceTable(PriceVariant priceVariant, Nomenclature nomenclature, String unit, double price, double count, double summa) {
        this.priceVariant = priceVariant;
        this.nomenclature = nomenclature;
        this.unit = unit;
        this.price = price;
        this.count = count;
        this.summa = summa;
    }

    public PriceTable(Integer id, PriceVariant priceVariant, Nomenclature nomenclature, String unit, double price, double count, double summa) {
        super(id);
        this.priceVariant = priceVariant;
        this.nomenclature = nomenclature;
        this.unit = unit;
        this.price = price;
        this.count = count;
        this.summa = summa;
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

//    public Set<ClientOrder> getOrders() {
//        return clientOrders;
//    }
//
//    public void setOrders(Set<ClientOrder> clientOrders) {
//        this.clientOrders = clientOrders;
//    }

    @Override
    public int compareTo(Object o) {
        PriceTable priceTable = (PriceTable) o;
        return nomenclature.getName().length() - priceTable.nomenclature.getName().length();
    }
}
