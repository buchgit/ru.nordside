package nordside.service;

import nordside.model.nomenclature.Nomenclature;
import nordside.model.price.PriceTable;
import nordside.model.user.User;
import nordside.repository.PriceTableRepository;
import nordside.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class PriceTableService {

    private PriceTableRepository priceTableRepository;

    @Autowired
    public PriceTableService(PriceTableRepository priceTableRepository) {
        this.priceTableRepository = priceTableRepository;
    }

    public List<PriceTable> getFullPriceByUser(User user) {
        Assert.notNull(user, Messages.USER_IS_NULL);
        return priceTableRepository.getFullPriceByUser(user.getId());
    }

    public int updatePriceTable(List<PriceTable> priceTableList) {
        Assert.notEmpty(priceTableList,Messages.LIST_IS_EMPTY);
        for (PriceTable pt:priceTableList){
            Nomenclature nomenclature = pt.getNomenclature();
        }
        return priceTableRepository.saveAll(priceTableList).size();
    }
}
