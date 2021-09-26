package nordside.service;

import nordside.model.user.User;
import nordside.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

import static nordside.utils.ValidationUtil.checkNotFound;

@Service("userService")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService extends CustomUserDetailService {

    private UserRepository userRepository;

//    @Autowired
//    public UserService(UserRepository userRepository) {
//        super();
//        this.userRepository = userRepository;
//    }

    private CustomPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, CustomPasswordEncoder passwordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //public static PasswordEncoder passwordEncoder() {
    //    return new BCryptPasswordEncoder();
    //}

    //@CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        Assert.notNull(user, "user is null, error");
        return prepareAndSave(user);
    }

//    //@CacheEvict(value = "users", allEntries = true)
//    public User update(User user, int id) {
//        Assert.notNull(user, "user is null, error");
//        return checkNotFoundWithId(prepareAndSave(user), id);
//    }
//
//    //@CacheEvict(value = "users", allEntries = true)
//    public void delete(int id) {
//        checkNotFoundWithId(userRepository.delete(id), id);
//    }
//
//    public User getById(int id) {
//        return userRepository.findById(id).orElse(null);
//    }
//

    public User getByEmail(String email) {
        Assert.notNull(email, "email is null, error");
        return checkNotFound(userRepository.getByEmail(email), email);
    }

    //
//    //@CacheEvict(value = "users", allEntries = true)
//    @Transactional
//    public void enable(int id, boolean enabled) {
//        User user = getById(id);
//        user.setEnabled(enabled);
//    }
//
//    @Override

    public static User prepareToSave(User user, CustomPasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

    private User prepareAndSave(User user) {
        return userRepository.save(prepareToSave(user, passwordEncoder));
    }


    public List<User> createAll(List<User> userList) {
        return userRepository.saveAll(userList);
    }
}
