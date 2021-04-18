package nordside.model.price;

import com.fasterxml.jackson.annotation.JsonBackReference;
import nordside.model.AbstractBaseEntity;
import nordside.model.AbstractNamedEntity;
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
    @JsonBackReference
    @JoinColumn(name = "nomenclature")
    private Nomenclature nomenclature;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    @JoinColumn(name = "unit")
    private Unit unit;

    @Column
    private double price;

}
