package nordside.model.nomenclature;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import nordside.model.AbstractNamedEntity;
import nordside.model.price.PriceTable;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "unit")
public class Unit extends AbstractNamedEntity {

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "owner")
    private Nomenclature owner;

    @Column
    private double weight;

    @Column
    private double value;//объем

    @Column
    private double coefficient;

    @OneToMany(mappedBy = "unit",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonManagedReference
    private Set<PriceTable> priceTable;

    public Unit() {
    }

    public Unit(Integer id, String name, Nomenclature owner, double weight, double value, double coefficient) {
        super(id, name);
        this.owner = owner;
        this.weight = weight;
        this.value = value;
        this.coefficient = coefficient;
    }


    public Nomenclature getOwner() {
        return owner;
    }

    public void setOwner(Nomenclature owner) {
        this.owner = owner;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}
