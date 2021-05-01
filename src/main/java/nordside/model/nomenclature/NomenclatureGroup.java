package nordside.model.nomenclature;

import com.fasterxml.jackson.annotation.*;
import nordside.model.AbstractNamedEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
@Entity
@Table(name = "nomenclature_group")
public class NomenclatureGroup extends AbstractNamedEntity {

    @Column
    private int level;

    @OneToMany(mappedBy = "nomenclatureGroup")
    @JsonIgnore //убрал "nomenclatureSet": null,
    private List<Nomenclature> nomenclatureSet;

    @Column
    private String code;

    public NomenclatureGroup() {
    }

    public NomenclatureGroup(Integer id, String name, int level , String code) {
        super(id, name);
        this.level = level;
        this.code = code;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Nomenclature> getNomenclatureSet() {
        return nomenclatureSet;
    }

    public void setNomenclatureSet(List<Nomenclature> nomenclatureSet) {
        this.nomenclatureSet = nomenclatureSet;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
