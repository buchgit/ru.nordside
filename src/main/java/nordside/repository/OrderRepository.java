package nordside.repository;

import nordside.model.order.Order;
import nordside.model.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query("select o from Order o where o.client.email =:email")
    List<Order> findByEmail(@Param("email") String email);

    @Query("select o from Order o where o.client.email =:email and o.status =:status")
    List<Order> getOrdersByEmailStatus(@Param("email") String email, @Param("status") OrderStatus status);
}
