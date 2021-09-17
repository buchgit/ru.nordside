package nordside.model.nomenclature;

import nordside.model.AbstractNamedEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "nomenclature_category")
public class NomenclatureCategory extends AbstractNamedEntity {

    @OneToOne(mappedBy = "category")
    private Nomenclature nomenclature;
}
