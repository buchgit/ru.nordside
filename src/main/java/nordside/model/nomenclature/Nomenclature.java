package nordside.model.nomenclature;

import com.fasterxml.jackson.annotation.JsonBackReference;
import nordside.model.AbstractNamedEntity;

import javax.persistence.*;

@Entity
@Table(name = "nomenclature")
public class Nomenclature extends AbstractNamedEntity {

    @Column
    private String code; //from 1C

    @Column
    private String fullName;

    @Column(name = "image_index")
    private String imageIndex;

    private Unit unit;

    private Unit packUnit;

    @Column(name = "product_country")
    private String productCountry;

    @Column
    private String description;

    public Nomenclature(Integer id, String name,
                        String fullName, String imageIndex,
                        Unit unit, Unit packUnit, String code,
                        String productCountry, String description) {
        super(id, name);
        this.fullName = fullName;
        this.imageIndex = imageIndex;
        this.unit = unit;
        this.packUnit = packUnit;
        this.code = code;
        this.productCountry = productCountry;
        this.description = description;
    }

    public Nomenclature() {

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

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Column(name = "pack_unit")
    @JsonBackReference
    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    public Unit getPackUnit() {
        return packUnit;
    }

    public void setPackUnit(Unit packUnit) {
        this.packUnit = packUnit;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
