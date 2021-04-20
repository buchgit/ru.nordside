package nordside.model.nomenclature;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import nordside.model.AbstractNamedEntity;
import nordside.model.price.PriceTable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "unit")
public class Unit extends AbstractNamedEntity {

    @OneToOne
    @JsonBackReference(value = "owner_unit")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "owner_id")
    private Nomenclature owner;

    @Column
    private double weight;

    @Column
    private double volume;//объем

    @Column
    private double coefficient;

    @OneToMany(mappedBy = "unit",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    //TODO @JsonManagedReference(value = "unit") ////закомм., потому что не возвращ. ед.изм в запросу
    private Set<PriceTable> priceTable;

    public Unit() {
    }

    public Unit(Integer id, String name, Nomenclature owner, double weight, double volume, double coefficient) {
        super(id, name);
        this.owner = owner;
        this.weight = weight;
        this.volume = volume;
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

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}
