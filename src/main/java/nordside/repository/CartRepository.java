package nordside.repository;

import nordside.model.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface CartRepository extends JpaRepository<CartItem,Integer> {

    @Query("select ci from CartItem ci where ci.user.email=:email")
    List<CartItem> getCartItemByEmail(@Param("email") String email);

//    @Query("select ci from CartItem ci where ci.nomenclature=:id")
//    List<CartItem> getCartItemByNomenclature(@Param("nomenclature_id") Integer id);


}
