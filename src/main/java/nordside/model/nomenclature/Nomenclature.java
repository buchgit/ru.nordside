package nordside.model.nomenclature;

import nordside.model.AbstractNamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "nomenclature")
public class Nomenclature extends AbstractNamedEntity {

    @Column
    private String fullName;

    @Column
    private String imageIndex;

    @Column
    @OneToOne
    @JoinColumn(columnDefinition = "nomenclature_id",nullable = false)
    private Nomenclature parent;

    @Column
    @OneToOne
    @JoinColumn(columnDefinition = "nomenclature_group_id",nullable = false)
    private NomenclatureGroup nomenclatureGroup;

    @Column
    private String productCountry;

    @Column
    private String description;

    public Nomenclature(String fullName, String imageIndex,  Nomenclature parent, NomenclatureGroup nomenclatureGroup, String productCountry, String description) {
        this.fullName = fullName;
        this.imageIndex = imageIndex;
        this.parent = parent;
        this.nomenclatureGroup = nomenclatureGroup;
        this.productCountry = productCountry;
        this.description = description;
    }

    public Nomenclature(Integer id, String name, String fullName, String imageIndex,  Nomenclature parent, NomenclatureGroup nomenclatureGroup, String productCountry, String description) {
        super(id, name);
        this.fullName = fullName;
        this.imageIndex = imageIndex;
        this.parent = parent;
        this.nomenclatureGroup = nomenclatureGroup;
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
