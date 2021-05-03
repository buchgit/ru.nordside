package nordside.repository;

import nordside.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface OrderRepository extends JpaRepository<Order,Integer> {

    //TODO: сделать запрос с отбором товара
    @Query("select o from Order o")
    List<Order> getAllWithMerchandise();
}
