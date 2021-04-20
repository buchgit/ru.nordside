package nordside.model.nomenclature;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import nordside.model.AbstractNamedEntity;
import nordside.model.price.PriceTable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "nomenclature")
public class Nomenclature extends AbstractNamedEntity {

    @Column
    private String code; //from 1C

    @Column
    private String fullName;

    @Column(name = "image_index")
    private String imageIndex;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "owner_unit")
    private Unit unit;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "owner_pack_unit")
    private PackUnit packUnit;

    @Column(name = "product_country")
    private String productCountry;

    @Column
    private String description;

    @ManyToOne
    @JsonBackReference(value = "nomenclature_group")
    @JoinColumn(name = "nomenclature_group")
    private NomenclatureGroup nomenclatureGroup;

    @OneToMany(mappedBy = "nomenclature",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    //TODO: @JsonManagedReference(value = "nomenclature") заком., потому что не возращается номенлатура в запросе
    private Set<PriceTable> priceTable;

    public Nomenclature(Integer id, String name, String fullName, String imageIndex,
                        Unit unit, PackUnit packUnit, String code, String productCountry, String description) {
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public  PackUnit getPackUnit(){
        return packUnit;
    }

    public void setPackUnit(PackUnit packUnit) {
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
