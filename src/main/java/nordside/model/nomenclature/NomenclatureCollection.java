package nordside.model.nomenclature;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nordside.model.AbstractNamedEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "nomenclature_collection")
public class NomenclatureCollection extends AbstractNamedEntity {

    @OneToMany(mappedBy = "nomenclatureCollection",fetch = FetchType.LAZY)
    @JsonIgnore //убрал nomenclature = null из json
    private Set<Nomenclature> nomenclature;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "category")
    @JsonIgnore
    private NomenclatureCategory category;

}
