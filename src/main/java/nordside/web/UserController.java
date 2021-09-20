package nordside.web;

import nordside.model.Partner;
import nordside.model.nomenclature.Nomenclature;
import nordside.model.nomenclature.NomenclatureCategory;
import nordside.model.nomenclature.NomenclatureCollection;
import nordside.model.order.Order;
import nordside.model.price.PriceTable;
import nordside.model.user.Role;
import nordside.model.user.User;
import nordside.service.*;
import nordside.web.jwt.JwtProvider;
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
    private final UserService userService;
    private final PriceTableService priceTableService;
    private final OrderService orderService;
    private final NomenclatureCategoryService nomenclatureCategoryService;
    private final NomenclatureCollectionService nomenclatureCollectionService;
    private final NomenclatureService nomenclatureService;
    private final PartnerService partnerService;
    private JwtProvider jwtProvider;

    @Autowired
    public UserController(UserService userService,
                          PriceTableService priceTableService,
                          OrderService orderService,
                          NomenclatureCategoryService nomenclatureCategoryService ,
                          NomenclatureCollectionService nomenclatureCollectionService,
                          NomenclatureService nomenclatureService,
                          PartnerService partnerService,
                          JwtProvider jwtProvider) {
        this.userService = userService;
        this.priceTableService = priceTableService;
        this.orderService = orderService;
        this.nomenclatureCategoryService = nomenclatureCategoryService;
        this.nomenclatureCollectionService = nomenclatureCollectionService;
        this.nomenclatureService = nomenclatureService;
        this.partnerService = partnerService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping(value = "registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return getStringResponseEntity(result, logger);
        } else {
            Set<Role> roles = new HashSet<>();
            roles.add(Role.USER);
            user.setRoles(roles);
            User createdUser = userService.create(user);
            logger.info("create(user) {} ", createdUser.getName());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

//    @PostMapping("/register")
//    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
//        UserEntity u = new UserEntity();
//        u.setPassword(registrationRequest.getPassword());
//        u.setLogin(registrationRequest.getLogin());
//        userService.saveUser(u);
//        return "OK";
//    }

//    @PostMapping("/auth")
//    public ResponseToken auth(@RequestBody User user) {
//        LoggedUser loggedUser = userService.loadUserByUsername(user.getEmail());
//        String email = loggedUser.getUsername();
//        String token = jwtProvider.generateToken(email);
//        return new ResponseToken(token);
//    }

    @GetMapping(value = "auth/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public User authByEmail(@RequestParam String email) {
        return userService.getByEmail(email);
    }

    //prices
    @GetMapping(value = "nomenclature/all")
    public List<PriceTable> getFullPriceByUser(@RequestParam String email){
        return priceTableService.getFullPriceByUser(userService.getByEmail(email));
    }

    //orders
    @GetMapping(value = "order/all")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping(value = "order/email")
    public List<Order> getOrdersByEmail(@RequestParam String email){
        return orderService.getOrdersByEmail(email);
    }

    @GetMapping(value = "order/email/status")
    public List<Order> getOrdersByEmailStatus(@RequestParam String email, @RequestParam String status){
        return orderService.getOrdersByEmailStatus(email, status);
    }

    @PostMapping(value = "order/create")
    public ResponseEntity<String> createOrder(@Valid @RequestBody Order order,BindingResult result){
        if (result.hasErrors()){
            return getStringResponseEntity(result, logger);
        }else {
            Order created = orderService.create(order);
            logger.info("created order number "+ created.getNumber_For1c());
            return new ResponseEntity<> (HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "order/update",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrder(@Valid @RequestBody Order order){
        orderService.update(order);
    }

    @GetMapping(value = "category/all")
    public List<NomenclatureCategory> getAllNomenclatureCategory(){
        return nomenclatureCategoryService.getAll();
    }

    @GetMapping(value = "collection/category/{id}")
    public List<NomenclatureCollection> getCollectionByCategory(@PathVariable String id){
        Integer categoryId = Integer.parseInt(id);
        return nomenclatureCollectionService.getByCategory(categoryId);
    }

    @GetMapping(value = "nomenclature/collection/{id}")
    public List<Nomenclature> getNomenclatureByCollection(@PathVariable String id){
        Integer collectionId = Integer.parseInt(id);
        return nomenclatureService.getByCollection(collectionId);
    }

    @GetMapping(value = "partner/all")
    public List<Partner> getAllPartner(){
        return partnerService.getAllPartners();
    }

}
