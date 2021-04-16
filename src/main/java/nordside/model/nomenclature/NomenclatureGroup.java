package nordside.model.nomenclature;

import nordside.model.AbstractNamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nomenclature_group")
public class NomenclatureGroup extends AbstractNamedEntity {

    public NomenclatureGroup() {
    }

    public NomenclatureGroup(Integer id, String name) {
        super(id, name);
    }

}
