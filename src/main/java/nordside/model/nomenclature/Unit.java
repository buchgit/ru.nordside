package nordside.model.nomenclature;

import nordside.model.AbstractNamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "unit")
public class Unit extends AbstractNamedEntity {

    @Column
    @OneToOne
    @JoinColumn(columnDefinition = "id")
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
}
