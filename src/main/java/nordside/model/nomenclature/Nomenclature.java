package nordside.model.nomenclature;

import nordside.model.AbstractNamedEntity;

public class Nomenclature extends AbstractNamedEntity {

    private Unit unit;
    private Unit pack;

    private String fullName;

    private String imageIndex;
    private String category;
    private Nomenclature parent;
    private NomenclatureGroup nomenclatureGroup;

    private String productCountry;
    private String description;

    public Nomenclature(Unit unit, Unit pack, String fullName, String imageIndex, String category, Nomenclature parent, NomenclatureGroup nomenclatureGroup, String productCountry, String description) {
        this.unit = unit;
        this.pack = pack;
        this.fullName = fullName;
        this.imageIndex = imageIndex;
        this.category = category;
        this.parent = parent;
        this.nomenclatureGroup = nomenclatureGroup;
        this.productCountry = productCountry;
        this.description = description;
    }

    public Nomenclature(Integer id, String name, Unit unit, Unit pack, String fullName, String imageIndex, String category, Nomenclature parent, NomenclatureGroup nomenclatureGroup, String productCountry, String description) {
        super(id, name);
        this.unit = unit;
        this.pack = pack;
        this.fullName = fullName;
        this.imageIndex = imageIndex;
        this.category = category;
        this.parent = parent;
        this.nomenclatureGroup = nomenclatureGroup;
        this.productCountry = productCountry;
        this.description = description;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Unit getPack() {
        return pack;
    }

    public void setPack(Unit pack) {
        this.pack = pack;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImageIndex() {
        return imageIndex;
    }

    public void setImageIndex(String imageIndex) {
        this.imageIndex = imageIndex;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Nomenclature getParent() {
        return parent;
    }

    public void setParent(Nomenclature parent) {
        this.parent = parent;
    }

    public NomenclatureGroup getNomenclatureGroup() {
        return nomenclatureGroup;
    }

    public void setNomenclatureGroup(NomenclatureGroup nomenclatureGroup) {
        this.nomenclatureGroup = nomenclatureGroup;
    }

    public String getProductCountry() {
        return productCountry;
    }

    public void setProductCountry(String productCountry) {
        this.productCountry = productCountry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
