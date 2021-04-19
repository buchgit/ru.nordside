package nordside.service;

import nordside.model.price.PriceTable;
import nordside.model.price.PriceVariant;
import nordside.model.user.User;
import nordside.repository.PriceTableRepository;
import nordside.repository.PriceVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.text.Collator;
import java.util.List;
import java.util.stream.Collectors;

import static nordside.utils.ValidationUtil.checkNotFound;

@Service
public class PriceTableService {

    private PriceTableRepository priceTableRepository;

    @Autowired
    public PriceTableService(PriceTableRepository priceTableRepository) {
        this.priceTableRepository = priceTableRepository;
    }

    public List<PriceTable> getFullPriceByUser(User user) {
        Assert.notNull(user,"Error, user is null");
        return priceTableRepository.getFullPriceByUser(user.getId());
    }
}
