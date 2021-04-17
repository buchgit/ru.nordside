package nordside.model.nomenclature;

import com.fasterxml.jackson.annotation.JsonBackReference;
import nordside.model.AbstractNamedEntity;

import javax.persistence.*;


@Entity
@Table(name = "unit")
public class Unit extends AbstractNamedEntity {

    private Nomenclature owner;

    @Column
    private double weight;

    @Column
    private double value;//объем

    @Column
    private double coefficient;

    public Unit() {
    }

    public Unit(Integer id, String name, Nomenclature owner, double weight, double value, double coefficient) {
        super(id, name);
        this.owner = owner;
        this.weight = weight;
        this.value = value;
        this.coefficient = coefficient;
    }

    @Column
    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "owner")
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
