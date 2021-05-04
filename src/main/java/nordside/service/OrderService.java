package nordside.service;

import nordside.model.order.Order;
import nordside.model.order.OrderStatus;
import nordside.repository.OrderRepository;
import nordside.utils.Messages;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service("orderService")
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByEmail(String email) {
        Assert.notNull(email,Messages.EMAIL_IS_NULL);
        Assert.notEmpty(Collections.singleton(email),Messages.EMAIL_IS_EMPTY);
        return orderRepository.findByEmail(email.toLowerCase());
    }

    public List<Order> getOrdersByEmailStatus(String email, String status) {
        Assert.notNull(email, Messages.EMAIL_IS_NULL);
        Assert.notEmpty(Collections.singleton(email),Messages.STATUS_IS_EMPTY);
        Assert.notNull(status, Messages.STATUS_IS_NULL);
        Assert.notEmpty(Collections.singleton(status),Messages.STATUS_IS_EMPTY);

        return orderRepository.getOrdersByEmailStatus(email, OrderStatus.valueOf(status.toUpperCase()));
    }
}
