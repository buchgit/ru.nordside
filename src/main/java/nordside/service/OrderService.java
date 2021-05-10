package nordside.service;

import nordside.exceptions.NotFoundException;
import nordside.model.order.Order;
import nordside.model.order.OrderStatus;
import nordside.model.user.User;
import nordside.repository.OrderRepository;
import nordside.repository.UserRepository;
import nordside.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

@Service("orderService")
public class OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
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

    @Transactional
    @Modifying
    public Order create(Order order) {
        Assert.notNull(order,Messages.ORDER_IS_NULL);
        User user = (User)userRepository.findByEmail(order.getClient().getEmail()).orElse(null);
        if (user!=null){
            order.setClient(user);
        }

        return orderRepository.save(order);
    }

    @Transactional
    @Modifying
    public void update(Order order) {
        Assert.notNull(order,Messages.ORDER_IS_NULL);
        Order existing = orderRepository.getOrderByNumberFor1c(order.getNumber_For1c()).orElse(null);
        if (existing != null){
            User user = (User)userRepository.findByEmail(existing.getClient().getEmail()).orElse(null);
            existing.fillFrom(order);
            //юзера восстанавливаем того, который был изначально, иначе ошибка записи пользователя с уже занятой почтой
            if (user!=null){
                existing.setClient(user);
            }
            orderRepository.save(existing);
        }else{
            throw new NotFoundException(Messages.ORDER_NOT_FOUND);
        }

    }
}
