package nordside.repository;

import nordside.model.price.PriceTable;
import nordside.model.price.PriceVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface PriceTableRepository extends JpaRepository<PriceVariant,Integer> {

    @Query("select pt from PriceTable pt where pt.priceVariant=(select u.price_variant from User u where u.id=:userId)")
    public List<PriceTable> getFullPriceByUser(Integer userId);


}
