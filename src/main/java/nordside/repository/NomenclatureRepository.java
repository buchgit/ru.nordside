package nordside.repository;

import nordside.model.nomenclature.Nomenclature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface NomenclatureRepository extends JpaRepository<Nomenclature,Integer> {

    @Query("select n from Nomenclature n where n.nomenclatureCollection.id =:id")
    List<Nomenclature> getByCollection(@Param("id") Integer id);

    Optional<Nomenclature> findByName(String title);
}
