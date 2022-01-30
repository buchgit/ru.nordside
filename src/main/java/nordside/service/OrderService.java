package nordside.service;

import nordside.LoggedUser;
import nordside.exceptions.NotFoundException;
import nordside.model.nomenclature.Nomenclature;
import nordside.model.order.*;
import nordside.model.price.PriceTable;
import nordside.model.user.User;
import nordside.repository.NomenclatureRepository;
import nordside.repository.OrderRepository;
import nordside.repository.UserRepository;
import nordside.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

@Service("orderService")
public class OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private NomenclatureRepository nomenclatureRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, NomenclatureRepository nomenclatureRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.nomenclatureRepository = nomenclatureRepository;
    }


    public List<ClientOrderTO> getAllOrders() {
        List<ClientOrder> clientOrderList = orderRepository.findAll();
        List<ClientOrderTO> clientOrderTOList = new ArrayList<>();
        for (ClientOrder clientOrder:clientOrderList){
            ClientOrderTO clientOrderTO = convertToClientOrderTO(clientOrder);
            clientOrderTOList.add(clientOrderTO);
        }
        return clientOrderTOList;
    }

    public List<ClientOrderTO> getOrdersByEmail(String email) {
        Assert.notNull(email,Messages.EMAIL_IS_NULL);
        Assert.notEmpty(Collections.singleton(email),Messages.EMAIL_IS_EMPTY);
        List<ClientOrder> clientOrderList = orderRepository.findByEmail(email.toLowerCase());
        List<ClientOrderTO> clientOrderTOList = new ArrayList<>();
        for (ClientOrder clientOrder:clientOrderList){
            ClientOrderTO clientOrderTO = convertToClientOrderTO(clientOrder);
            clientOrderTOList.add(clientOrderTO);
        }
        return clientOrderTOList;
    }

    public List<ClientOrder> getOrdersByEmailStatus(String email, String status) {
        Assert.notNull(email, Messages.EMAIL_IS_NULL);
        Assert.notEmpty(Collections.singleton(email),Messages.STATUS_IS_EMPTY);
        Assert.notNull(status, Messages.STATUS_IS_NULL);
        Assert.notEmpty(Collections.singleton(status),Messages.STATUS_IS_EMPTY);

        return orderRepository.getOrdersByEmailStatus(email, OrderStatus.valueOf(status.toUpperCase()));
    }

    @Transactional
    @Modifying
    public ClientOrder create(ClientOrderTO clientOrderTO, LoggedUser loggedUser) {
        Assert.notNull(clientOrderTO,Messages.ORDER_IS_NULL);
        String email = loggedUser.getUsername();
        User user = (User)userRepository.findByEmail(email).orElse(null);
        if (user!=null){
            return orderRepository.save(convertToClientOrder(clientOrderTO, user));
        }
        return null;
    }

    @Transactional
    @Modifying
    public void update(ClientOrder clientOrder) {
        Assert.notNull(clientOrder,Messages.ORDER_IS_NULL);
        ClientOrder existing = orderRepository.getOrderByNumberFor1c(clientOrder.getNumber_For1c()).orElse(null);
        if (existing != null){
            User user = (User)userRepository.findByEmail(existing.getClient().getEmail()).orElse(null);
            existing.fillFrom(clientOrder);
            //юзера восстанавливаем того, который был изначально, иначе ошибка записи пользователя с уже занятой почтой
            if (user!=null){
                existing.setClient(user);
            }
            orderRepository.save(existing);
        }else{
            throw new NotFoundException(Messages.ORDER_NOT_FOUND);
        }

    }

    private ClientOrder convertToClientOrder(ClientOrderTO clientOrderTO, User user){

        double totalAmount = 0.00 ,totalVolume = 0.00,totalWeight = 0.00;

        ClientOrder clientOrder = new ClientOrder(new Date(),
                "",
                user,
                totalAmount,
                totalVolume,
                totalWeight,
                OrderStatus.NEW,
                null);

        List<ClientOrderLineTO> clientOrderLineList = clientOrderTO.getOrderLinesTable();

        Set<ClientOrderLine> clientOrderLineSet = new TreeSet<>();
        for (ClientOrderLineTO clientOrderLineTO: clientOrderLineList){

            Nomenclature nomenclature = nomenclatureRepository.findByName(clientOrderLineTO.getTitle()).orElse(null);
            Assert.notNull(nomenclature,Messages.NOMENCLATURE_NOT_FOUND);

            if (nomenclature.getCountInPack() != 0) {
                totalWeight += nomenclature.getPackWeight() / nomenclature.getCountInPack();
                totalVolume += nomenclature.getPackVolume() / nomenclature.getCountInPack();
            }

            ClientOrderLine clientOrderLine = new ClientOrderLine(
                    clientOrder,
                    nomenclature,
                    clientOrderLineTO.getUnit(),
                    clientOrderLineTO.getCount(),
                    clientOrderLineTO.getSumma()
            );
            totalAmount += clientOrderLineTO.getSumma();
            clientOrderLineSet.add(clientOrderLine);
        }

        clientOrder.setClientOrderLineTable(clientOrderLineSet);
        clientOrder.setTotalAmount(totalAmount);
        clientOrder.setTotalVolume(totalVolume);
        clientOrder.setTotalWeight(totalWeight);

        return clientOrder;
    }

    private ClientOrderTO convertToClientOrderTO(ClientOrder clientOrder){

        Set<ClientOrderLine> clientOrderLines = clientOrder.getClientOrderLineTable();

        List<ClientOrderLineTO> clientOrderLineTOTable = new ArrayList<>();
        for (ClientOrderLine clientOrderLine: clientOrderLines){
            ClientOrderLineTO clientOrderLineTO = new ClientOrderLineTO(
                    clientOrderLine.getNomenclature().getName(),
                    clientOrderLine.getUnit(),
                    clientOrderLine.getCount(),
                    clientOrderLine.getSumma()
            );
            clientOrderLineTOTable.add(clientOrderLineTO);
        }

        return new ClientOrderTO(
                clientOrder.getDate(),
                clientOrder.getTotalAmount(),
                clientOrderLineTOTable
            );
    }
}
