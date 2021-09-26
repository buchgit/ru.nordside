package nordside.service;

import nordside.LoggedUser;
import nordside.model.user.User;
import nordside.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public CustomUserDetailService(UserRepository repository) {
        this.repository = repository;
    }

//    public CustomUserDetailService() {
//
//    }

    @Override
    public LoggedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new LoggedUser(user);
    }
}
