package nordside.model.nomenclature;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nordside.model.AbstractNamedEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "nomenclature_category")
public class NomenclatureCategory extends AbstractNamedEntity {

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    @JsonIgnore //убрал nomenclature = null из json
    private Set<Nomenclature> nomenclature;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<NomenclatureCollection> nomenclatureCollection;

}
