package nordside.service;

import nordside.model.nomenclature.Nomenclature;
import nordside.repository.NomenclatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service("nomenclatureService")
public class NomenclatureService {

    private NomenclatureRepository nomenclatureRepository;

    @Autowired
    public NomenclatureService(NomenclatureRepository nomenclatureRepository) {
        this.nomenclatureRepository = nomenclatureRepository;
    }

    public int updateNomenclature (List<Nomenclature> nomenclatureList){
        Assert.notEmpty(nomenclatureList,"Nomenclature list is empty!");
        return nomenclatureRepository.saveAll(nomenclatureList).size();
    }
}
