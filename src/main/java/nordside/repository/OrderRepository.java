package nordside.repository;

import nordside.model.order.ClientOrder;
import nordside.model.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface OrderRepository extends JpaRepository<ClientOrder,Integer> {

    @Query("select o from ClientOrder o where o.client.email =:email")
    List<ClientOrder> findByEmail(@Param("email") String email);

    @Query("select o from ClientOrder o where o.client.email =:email and o.status =:status")
    List<ClientOrder> getOrdersByEmailStatus(@Param("email") String email, @Param("status") OrderStatus status);

    @Query("select o from ClientOrder o where o.number_For1c=:number_for1c")
    Optional<ClientOrder> getOrderByNumberFor1c(@Param("number_for1c") String number_for1c);

}
