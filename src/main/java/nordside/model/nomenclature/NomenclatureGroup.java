package nordside.model.nomenclature;

import nordside.model.AbstractNamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nomenclature_group")
public class NomenclatureGroup extends AbstractNamedEntity {

    private int level;


    public NomenclatureGroup() {
    }

    public NomenclatureGroup(Integer id, String name, int level) {
        super(id, name);
        this.level = level;
    }

}
