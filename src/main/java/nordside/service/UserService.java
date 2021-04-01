package nordside.service;

import nordside.LoggedUser;
import nordside.model.User;
import nordside.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
//
//    //@CacheEvict(value = "users", allEntries = true)
//    public User create(User user) {
//        Assert.notNull(user, "user is null, error");
//        return prepareAndSave(user);
//    }
//
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
//    public User getByEmail(String email) {
//        Assert.notNull(email, "email is null, error");
//        return checkNotFound(userRepository.getByEmail(email), email);
//    }
//
//    //@CacheEvict(value = "users", allEntries = true)
//    @Transactional
//    public void enable(int id, boolean enabled) {
//        User user = getById(id);
//        user.setEnabled(enabled);
//    }
//
//    @Override
    public LoggedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new LoggedUser(user);
    }
//
//    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
//        String password = user.getPassword();
//        user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
//        user.setEmail(user.getEmail().toLowerCase());
//        return user;
//    }
//
//    private User prepareAndSave(User user) {
//        return userRepository.save(prepareToSave(user, passwordEncoder));
//    }
}
