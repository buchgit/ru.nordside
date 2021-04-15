package nordside.model.nomenclature;

import nordside.model.AbstractNamedEntity;

public class BaseUnit extends AbstractNamedEntity {

    private String code;   //код в 1С
    private double weight;
    private double packVolume;
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
