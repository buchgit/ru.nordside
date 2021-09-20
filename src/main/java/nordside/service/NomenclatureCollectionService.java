package nordside.service;

import nordside.model.nomenclature.NomenclatureCollection;
import nordside.repository.NomenclatureCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("nomenclature_collection_service")
public class NomenclatureCollectionService {

    private final NomenclatureCollectionRepository repository;

    @Autowired
    public NomenclatureCollectionService(NomenclatureCollectionRepository repository) {
        this.repository = repository;
    }

    public List<NomenclatureCollection> getByCategory(Integer categoryId){
        return repository.getByCategory(categoryId);
    }
}
