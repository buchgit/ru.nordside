package nordside.model.nomenclature;

import com.fasterxml.jackson.annotation.*;
import nordside.model.AbstractNamedEntity;
import nordside.model.cart.CartItem;
import nordside.model.price.PriceTable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
@Entity
@Table(name = "nomenclature")
public class Nomenclature extends AbstractNamedEntity {

    @Column
    @NotNull
    private String code; //from 1C

    @Column
    private String fullName;

    @Column(name = "image_index")
    private String imageIndex;

    @Column
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "category")
    private NomenclatureCategory category;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "nomenclature_collection")
    private NomenclatureCollection nomenclatureCollection;

    @Column
    private double length;

    @Column
    private double width;

    @Column
    private double size1;
    @Column
    private double size2;

    @Column
    private double diameter;

    @Column
    private double high;

    @Column(name = "pack_square")
    private double packSquare;

    @Column
    private String color;

    @Column(name = "pack_volume")
    private double packVolume;

    @Column(name = "pack_weight")
    private double packWeight;

    @Column
    private int countInPack;

    @Column
    private String unit;

    @OneToMany(mappedBy = "nomenclature",cascade = CascadeType.ALL)
    //TODO: @JsonManagedReference(value = "nomenclature") заком., потому что не возращается номенлатура в запросе
    @JsonIgnore //убрал "priceTable": null
    private Set<PriceTable> priceTable;

    @ManyToMany
    @JoinTable(name = "nomenclature_cart_item",joinColumns = @JoinColumn(name = "nomenclature_id"),
    inverseJoinColumns = @JoinColumn(name = "cart_item_id"))
    @JsonIgnore
    private Set<CartItem> cartItems = new HashSet<>();

    public Nomenclature() {

    }

    public Nomenclature(String code, String fullName, String imageIndex,
                        String description, NomenclatureCategory category, NomenclatureCollection nomenclatureCollection,
                        double length, double width,double size1,double size2, double diameter, double high, String color,
                        double packVolume, double packWeight,double packSquare, int countInPack, String unit) {
        this.code = code;
        this.fullName = fullName;
        this.imageIndex = imageIndex;
        this.description = description;
        this.category = category;
        this.nomenclatureCollection = nomenclatureCollection;
        this.length = length;
        this.width = width;
        this.size1 = size1;
        this.size2 = size2;
        this.diameter = diameter;
        this.high = high;
        this.color = color;
        this.packVolume = packVolume;
        this.packWeight = packWeight;
        this.countInPack = countInPack;
        this.packSquare = packSquare;
        this.unit = unit;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Nomenclature(Integer id, String name, String code,
                        String fullName, String imageIndex,
                        String description, NomenclatureCategory category,
                        NomenclatureCollection nomenclatureCollection, double length,
                        double width, double size1, double size2, double diameter, double high, String color,
                        double packVolume, double packWeight, double packSquare, int countInPack, String unit) {
        super(id, name);
        this.code = code;
        this.fullName = fullName;
        this.imageIndex = imageIndex;
        this.description = description;
        this.category = category;
        this.nomenclatureCollection = nomenclatureCollection;
        this.length = length;
        this.width = width;
        this.size1 = size1;
        this.size2 = size2;
        this.diameter = diameter;
        this.high = high;
        this.color = color;
        this.packVolume = packVolume;
        this.packWeight = packWeight;
        this.packSquare = packSquare;
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

    public NomenclatureCategory getCategory() {
        return category;
    }

    public void setCategory(NomenclatureCategory category) {
        this.category = category;
    }

    public NomenclatureCollection getNomenclatureCollection() {
        return nomenclatureCollection;
    }

    public void setNomenclatureCollection(NomenclatureCollection nomenclatureCollection) {
        this.nomenclatureCollection = nomenclatureCollection;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
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

    public double getSize1() {
        return size1;
    }

    public void setSize1(double size1) {
        this.size1 = size1;
    }

    public double getSize2() {
        return size2;
    }

    public void setSize2(double size2) {
        this.size2 = size2;
    }

    public double getSize3() {
        return diameter;
    }

    public void setSize3(double size3) {
        this.diameter = size3;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getVolume() {
        return packVolume;
    }

    public void setVolume(double packVolume) {
        this.packVolume = packVolume;
    }

    public double getPackWeight() {
        return packWeight;
    }

    public void setPackWeight(double packWeight) {
        this.packWeight = packWeight;
    }

    public double getPackSquare() {
        return packSquare;
    }

    public void setPackSquare(double packSquare) {
        this.packSquare = packSquare;
    }

    public double getPackVolume() {
        return packVolume;
    }

    public void setPackVolume(double packVolume) {
        this.packVolume = packVolume;
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
