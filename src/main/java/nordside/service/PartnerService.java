package nordside.service;

import nordside.model.Partner;
import nordside.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("partner_service")
public class PartnerService {
    private PartnerRepository repository;

    @Autowired
    public PartnerService(PartnerRepository repository) {
        this.repository = repository;
    }

    public List<Partner> getAllPartners(){
        return repository.findAll();
    }
}
