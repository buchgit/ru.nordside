package nordside.service;

import nordside.model.nomenclature.NomenclatureCategory;
import nordside.repository.NomenclatureCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("nomenclature_category_service")
public class NomenclatureCategoryService {
    private final NomenclatureCategoryRepository repository;

    @Autowired
    public NomenclatureCategoryService(NomenclatureCategoryRepository repository) {
        this.repository = repository;
    }

    public List<NomenclatureCategory> getAll(){
        return repository.findAll();
    }
}
