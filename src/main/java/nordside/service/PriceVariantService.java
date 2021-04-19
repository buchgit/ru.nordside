package nordside.service;

import nordside.model.price.PriceVariant;
import nordside.repository.PriceVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceVariantService {

    private PriceVariantRepository priceVariantRepository;

    @Autowired
    public PriceVariantService(PriceVariantRepository priceVariantRepository) {
        this.priceVariantRepository = priceVariantRepository;
    }

    //TO DO  add call this method into any controller
    List<PriceVariant> getAllPriceVariants(){
        return priceVariantRepository.findAll();
    }
}
