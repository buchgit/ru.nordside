package nordside.repository;

import nordside.model.nomenclature.NomenclatureCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface NomenclatureCategoryRepository extends JpaRepository<NomenclatureCategory,Integer> {

    //    @Query("select nc from NomenclatureCategory nc")
    //    List<NomenclatureCategory> getAll();

    //не нужно писать функции, так как из сервиса напрямую через findAll()
}
