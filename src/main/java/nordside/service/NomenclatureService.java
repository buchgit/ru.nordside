package nordside.service;

import nordside.model.nomenclature.Nomenclature;
import nordside.repository.NomenclatureRepository;
import nordside.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service("nomenclatureService")
public class NomenclatureService {

    private final NomenclatureRepository nomenclatureRepository;

    @Autowired
    public NomenclatureService(NomenclatureRepository nomenclatureRepository) {
        this.nomenclatureRepository = nomenclatureRepository;
    }

    @Transactional
    @Modifying
    public int updateNomenclature (List<Nomenclature> nomenclatureList){
        Assert.notEmpty(nomenclatureList, Messages.NOMENCLATURE_LIST_IS_EMPTY);
        return nomenclatureRepository.saveAll(nomenclatureList).size();
    }
}
