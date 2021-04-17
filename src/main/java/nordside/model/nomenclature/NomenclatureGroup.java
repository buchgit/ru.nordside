package nordside.model.nomenclature;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import nordside.model.AbstractNamedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "nomenclature_group")
public class NomenclatureGroup extends AbstractNamedEntity {

    @Column
    private int level;

    @OneToMany(mappedBy = "nomenclature_group")
    @JsonManagedReference
    private List<Nomenclature> nomenclatureList;

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

    public List<Nomenclature> getNomenclatureList() {
        return nomenclatureList;
    }

    public void setNomenclatureList(List<Nomenclature> nomenclatureList) {
        this.nomenclatureList = nomenclatureList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
