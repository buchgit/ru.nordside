package nordside.service;

import nordside.model.nomenclature.Nomenclature;
import nordside.model.nomenclature.NomenclatureGroup;
import nordside.repository.NomenclatureGroupRepository;
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
    private final NomenclatureGroupRepository nomenclatureGroupRepository;

    @Autowired
    public NomenclatureService(NomenclatureRepository nomenclatureRepository, NomenclatureGroupRepository nomenclatureGroupRepository) {
        this.nomenclatureRepository = nomenclatureRepository;
        this.nomenclatureGroupRepository = nomenclatureGroupRepository;
    }

    @Transactional
    @Modifying
    public int updateNomenclature (List<Nomenclature> nomenclatureList){
        Assert.notEmpty(nomenclatureList, Messages.NOMENCLATURE_LIST_IS_EMPTY);
        for(Nomenclature n:nomenclatureList){
            NomenclatureGroup nomenclatureGroup = nomenclatureGroupRepository.getByCode(n.getNomenclatureGroup().getCode());
            if (nomenclatureGroup!=null){
                n.setNomenclatureGroup(nomenclatureGroup);

            }
            nomenclatureRepository.save(n);
        }
        return 0;
        //return nomenclatureRepository.saveAll(nomenclatureList).size();
    }
}
