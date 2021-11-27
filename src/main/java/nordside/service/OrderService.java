package nordside.service;

import nordside.LoggedUser;
import nordside.exceptions.NotFoundException;
import nordside.model.nomenclature.Nomenclature;
import nordside.model.order.ClientOrder;
import nordside.model.order.ClientOrderLine;
import nordside.model.order.ClientOrderTO;
import nordside.model.order.OrderStatus;
import nordside.model.price.PriceTable;
import nordside.model.price.PriceVariant;
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


    public List<ClientOrder> getAllOrders() {
        return orderRepository.findAll();
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

        List<ClientOrderLine> clientOrderLineList = clientOrderTO.getOrderLinesTable();

        Set<PriceTable> priceTableSet = new TreeSet<>();
        for (ClientOrderLine clientOrderLine: clientOrderLineList){

            Nomenclature nomenclature = nomenclatureRepository.findByName(clientOrderLine.getTitle()).orElse(null);
            Assert.notNull(nomenclature,Messages.NOMENCLATURE_NOT_FOUND);

            PriceTable priceTable = new PriceTable(
                    user.getPriceVariant(),
                    nomenclature,
                    clientOrderLine.getUnit(),
                    clientOrderLine.getPrice(),
                    clientOrderLine.getCount(),
                    clientOrderLine.getSumma()
            );
            priceTableSet.add(priceTable);
        }

        ClientOrder clientOrder = new ClientOrder(clientOrderTO.getDate(),
                "",
                user,
                clientOrderTO.getTotalAmount(),
                clientOrderTO.getTotalVolume(),
                clientOrderTO.getTotalWeight(),
                OrderStatus.NEW,
                priceTableSet);


        return clientOrder;
    }

    private ClientOrderTO convertToClientOrderTO(ClientOrder clientOrder){

        Set<PriceTable> priceTableSet = clientOrder.getPriceTables();

        List<ClientOrderLine> orderLinesTable = new ArrayList<>();
        for (PriceTable priceTable: priceTableSet){
            ClientOrderLine clientOrderLine = new ClientOrderLine(
                    clientOrder.getNumber_For1c(),
                    priceTable.getNomenclature().getName(),
                    priceTable.getUnit(),
                    priceTable.getCount(),
                    priceTable.getSumma()
            );
            orderLinesTable.add(clientOrderLine);
        }

        ClientOrderTO clientOrderTO = new ClientOrderTO(
                clientOrder.getId().toString(),
                clientOrder.getDate(),
                clientOrder.getNumber_For1c(),
                clientOrder.getTotalVolume(),
                orderLinesTable
            );

        return clientOrderTO;
    }
}
/*
//code in 1c
    private String code;
    //nomenclature title
    private String title;
    private String unit;
    private double count;
    private double summa;
 */