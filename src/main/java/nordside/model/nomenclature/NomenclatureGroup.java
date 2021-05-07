package nordside.model.nomenclature;

import com.fasterxml.jackson.annotation.*;
import nordside.model.AbstractNamedEntity;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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

//    @Override
//    public int hashCode() {
//        HashCodeBuilder hcb = new HashCodeBuilder();
//        hcb.append(code);
//        return hcb.toHashCode();
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (!(obj instanceof NomenclatureGroup)) {
//            return false;
//        }
//        NomenclatureGroup that = (NomenclatureGroup) obj;
//        EqualsBuilder eb = new EqualsBuilder();
//        eb.append(code, that.code);
//        return eb.isEquals();
//    }
}
