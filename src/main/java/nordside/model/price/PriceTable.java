package nordside.model.price;

import com.fasterxml.jackson.annotation.JsonBackReference;
import nordside.model.AbstractBaseEntity;
import nordside.model.nomenclature.Nomenclature;
import nordside.model.nomenclature.Unit;

import javax.persistence.*;

@Entity
@Table(name = "price_table")
public class PriceTable extends AbstractBaseEntity {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    @JoinColumn(name = "price_variant")
    private PriceVariant priceVariant;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //@JsonBackReference
    @JoinColumn(name = "nomenclature")
    private Nomenclature nomenclature;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //@JsonBackReference
    @JoinColumn(name = "unit")
    private Unit unit;

    @Column
    private double price;

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
}
