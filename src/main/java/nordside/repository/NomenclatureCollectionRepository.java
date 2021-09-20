package nordside.repository;

import nordside.model.nomenclature.NomenclatureCategory;
import nordside.model.nomenclature.NomenclatureCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional(readOnly = true)
public interface NomenclatureCollectionRepository extends JpaRepository<NomenclatureCollection,Integer> {

    @Query("select nc from NomenclatureCollection nc where nc.category.id =:id_category")
    List<NomenclatureCollection> getByCategory(@Param("id_category") Integer categoryId);

}
