package nordside.service;

import nordside.model.cart.CartItem;
import nordside.model.user.User;
import nordside.repository.CartRepository;
import nordside.repository.UserRepository;
import nordside.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

@Service("cart_service")
public class CartService {
    private CartRepository repository;
    private UserRepository userRepository;

    @Autowired
    public CartService(CartRepository repository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.repository = repository;
    }

    public List<CartItem>getCartItemByEmail(String email){
        Assert.notNull(email, Messages.EMAIL_IS_NULL);
        Assert.notEmpty(Collections.singleton(email),Messages.STATUS_IS_EMPTY);
        return repository.getCartItemByEmail(email);
    }

    @Transactional
    @Modifying
    public CartItem create(CartItem cartItem) {
        Assert.notNull(cartItem,Messages.CART_IS_NULL);
        User user = (User) userRepository.findByEmail(cartItem.getUser().getEmail()).orElse(null);
        if (user!=null){
            cartItem.setUser(user);
        }
        return repository.save(cartItem);
    }

//    @Transactional
//    @Modifying
//    public void update(CartItem cartItem) {
//        Assert.notNull(cartItem,Messages.CART_IS_NULL);
//        CartItem existing = repository.getCartItemByNomenclature(cartItem.getNumber_For1c()).orElse(null);
//        if (existing != null){
//            User user = (User)repository.findByEmail(existing.getClient().getEmail()).orElse(null);
//            existing.fillFrom(cartItem);
//            //юзера восстанавливаем того, который был изначально, иначе ошибка записи пользователя с уже занятой почтой
//            if (user!=null){
//                existing.setu(user);
//            }
//            repository.save(existing);
//        }else{
//            throw new NotFoundException(Messages.CART_NOT_FOUND);
//        }
//
//    }
}
