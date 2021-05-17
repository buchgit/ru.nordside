package nordside.model.nomenclature;

import com.fasterxml.jackson.annotation.*;
import nordside.model.AbstractNamedEntity;
import nordside.model.price.PriceTable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
@Entity
@Table(name = "nomenclature")
public class Nomenclature extends AbstractNamedEntity {

    @Column
    private String code; //from 1C

    @Column
    private String fullName;

    @Column(name = "image_index")
    private String imageIndex;

    @Column
    private String description;

    @Column
    private String section;

    @Column
    private String subsection;

    @Column
    private double length;

    @Column
    private double width;

    @Column
    private double high;

    @Column
    private double color;

    @Column
    private double volume;

    @Column
    private int countInPack;

    @Column
    private String unit;




    @OneToMany(mappedBy = "nomenclature",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    //TODO: @JsonManagedReference(value = "nomenclature") заком., потому что не возращается номенлатура в запросе
    @JsonIgnore //убрал "priceTable": null
    private Set<PriceTable> priceTable;


    public Nomenclature() {

    }

    public Nomenclature(String code, String fullName, String imageIndex,
                        String description, String section, String subsection,
                        double length, double width, double high, double color,
                        double volume, int countInPack, String unit) {
        this.code = code;
        this.fullName = fullName;
        this.imageIndex = imageIndex;
        this.description = description;
        this.section = section;
        this.subsection = subsection;
        this.length = length;
        this.width = width;
        this.high = high;
        this.color = color;
        this.volume = volume;
        this.countInPack = countInPack;
        this.unit = unit;
    }

    public Nomenclature(Integer id, String name, String code,
                        String fullName, String imageIndex,
                        String description, String section,
                        String subsection, double length,
                        double width, double high, double color,
                        double volume, int countInPack, String unit) {
        super(id, name);
        this.code = code;
        this.fullName = fullName;
        this.imageIndex = imageIndex;
        this.description = description;
        this.section = section;
        this.subsection = subsection;
        this.length = length;
        this.width = width;
        this.high = high;
        this.color = color;
        this.volume = volume;
        this.countInPack = countInPack;
        this.unit = unit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getColor() {
        return color;
    }

    public void setColor(double color) {
        this.color = color;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getCountInPack() {
        return countInPack;
    }

    public void setCountInPack(int countInPack) {
        this.countInPack = countInPack;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Set<PriceTable> getPriceTable() {
        return priceTable;
    }

    public void setPriceTable(Set<PriceTable> priceTable) {
        this.priceTable = priceTable;
    }
}
