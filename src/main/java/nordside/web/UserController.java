package nordside.web;

import nordside.model.nomenclature.Nomenclature;
import nordside.model.price.PriceTable;
import nordside.model.user.Role;
import nordside.model.user.User;
import nordside.service.PriceTableService;
import nordside.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static nordside.utils.ValidationUtil.getStringResponseEntity;

@RestController
@RequestMapping(UserController.REST_URL)
public class UserController {

    static final String REST_URL = "rest/user/";

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    private PriceTableService priceTableService;

    @Autowired
    public UserController(UserService userService, PriceTableService priceTableService) {
        this.userService = userService;
        this.priceTableService = priceTableService;
    }

    @PostMapping(value = "registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return getStringResponseEntity(result, logger);
        } else {
            Set<Role> roles = new HashSet<Role>();
            roles.add(Role.USER);
            user.setRoles(roles);
            User createdUser = userService.create(user);
            logger.info("create(user) {} ", createdUser.getName());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "auth/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public User authByEmail(@RequestParam String email) {
        return userService.getByEmail(email);
    }

    @GetMapping(value = "nomenclature/all")
//    public Integer getFullPriceByUser(@Valid @RequestBody User user, BindingResult result){
//        if (result.hasErrors()){
//            //TO DO
//            logger.error("error user binding ");
//            return 111;
//        }else{
//            return priceTableService.getFullPriceByUser(user).size();
//        }
//    }

    //its worked
//    public Integer getFullPriceByUser(@RequestParam String email){
//        return priceTableService.getFullPriceByUser(userService.getByEmail(email)).size();
//    }

    public List<PriceTable> getFullPriceByUser(@RequestParam String email){
        return priceTableService.getFullPriceByUser(userService.getByEmail(email));
    }

}
