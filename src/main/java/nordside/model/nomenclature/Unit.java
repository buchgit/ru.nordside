package nordside.model.nomenclature;


public class Unit extends BaseUnit {

    private Nomenclature owner;

    public Unit(Integer id, String name, String code, double weight, double packVolume, double coefficient, Nomenclature owner) {
        super(id, name, code, weight, packVolume, coefficient);
        this.owner = owner;
    }

    public Nomenclature getOwner() {
        return owner;
    }

    public void setOwner(Nomenclature owner) {
        this.owner = owner;
    }
}
