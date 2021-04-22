package nordside.repository;

import nordside.model.nomenclature.Nomenclature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface NomenclatureRepository extends JpaRepository<Nomenclature,Integer> {

}