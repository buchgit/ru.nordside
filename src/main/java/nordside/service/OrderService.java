package nordside.service;

import nordside.model.order.Order;
import nordside.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}
