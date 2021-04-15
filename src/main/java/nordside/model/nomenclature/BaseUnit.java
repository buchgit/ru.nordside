package nordside.model.nomenclature;

import nordside.model.AbstractNamedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "base_unit")
public class BaseUnit extends AbstractNamedEntity {

    @Column(name = "code")
    @NotNull
    private String code;   //код в 1С

    @Column(name = "weight")
    private double weight;

    @Column(name = "pack_volume")
    private double packVolume;

    @Column(name = "coefficient")
    private double coefficient;

    public BaseUnit() {
    }

    public BaseUnit(Integer id, String name, String code, double weight, double packVolume, double coefficient) {
        super(id, name);
        this.code = code;
        this.weight = weight;
        this.packVolume = packVolume;
        this.coefficient = coefficient;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPackVolume() {
        return packVolume;
    }

    public void setPackVolume(double packVolume) {
        this.packVolume = packVolume;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}
