package nordside.web;

import nordside.LoggedUser;
import nordside.model.Partner;
import nordside.model.cart.CartItem;
import nordside.model.nomenclature.Nomenclature;
import nordside.model.nomenclature.NomenclatureCategory;
import nordside.model.nomenclature.NomenclatureCollection;
import nordside.model.order.ClientOrder;
import nordside.model.order.ClientOrderTO;
import nordside.model.price.PriceTable;
import nordside.model.user.Role;
import nordside.model.user.User;
import nordside.service.*;
import nordside.web.jwt.Credentials;
import nordside.web.jwt.JwtProvider;
import nordside.web.jwt.ResponseTokenPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final CartService cartService;
    private JwtProvider jwtProvider;

    @Autowired
    public UserController(UserService userService,
                          PriceTableService priceTableService,
                          OrderService orderService,
                          NomenclatureCategoryService nomenclatureCategoryService ,
                          NomenclatureCollectionService nomenclatureCollectionService,
                          NomenclatureService nomenclatureService,
                          PartnerService partnerService,
                          CartService cartService,
                          JwtProvider jwtProvider) {
        this.userService = userService;
        this.priceTableService = priceTableService;
        this.orderService = orderService;
        this.nomenclatureCategoryService = nomenclatureCategoryService;
        this.nomenclatureCollectionService = nomenclatureCollectionService;
        this.nomenclatureService = nomenclatureService;
        this.partnerService = partnerService;
        this.cartService = cartService;
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

    //by login and password in the request body
    @PostMapping(value = "auth",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseTokenPair auth(@RequestBody Credentials credentials) {
        Logger logger = LoggerFactory.getLogger("auth");
        logger.info("auth");
        String email = credentials.getEmail();
        String accessToken = jwtProvider.generateToken(email, false);
        String refreshToken = jwtProvider.generateToken(email, true);
        return new ResponseTokenPair(accessToken, refreshToken);
    }

    //by refresh token
    @PostMapping(value = "refresh")
    public ResponseTokenPair auth(@AuthenticationPrincipal LoggedUser loggedUser) {
        Logger logger = LoggerFactory.getLogger("refresh");
        logger.info("refresh");
        String email = loggedUser.getPassword();
        String accessToken = jwtProvider.generateToken(email, false);
        String refreshToken = jwtProvider.generateToken(email, true);
        return new ResponseTokenPair(accessToken, refreshToken);
    }

    @GetMapping(value = "auth/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public User authByEmail(@RequestParam String email) {
        return userService.getByEmail(email);
    }

    //prices общий
    @GetMapping(value = "nomenclature/all")
    public List<PriceTable> getFullPriceByUser(@RequestParam String email){
        return priceTableService.getFullPriceByUser(userService.getByEmail(email));
    }

    //prices по токену
    @GetMapping(value = "personal/nomenclature/collection/{id}")
    public List<PriceTable> getPersonalUserPrice(@PathVariable String id, @AuthenticationPrincipal LoggedUser loggedUser){
        String email = loggedUser.getUsername();
        Integer collectionId = Integer.parseInt(id);
        return priceTableService.getPriceByCollectionIdByUser(userService.getByEmail(email),collectionId);
    }

    //clientOrders
    @GetMapping(value = "order/all")
    public List<ClientOrderTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping(value = "order/personal")
    public List<ClientOrderTO> getOrdersByEmail(@AuthenticationPrincipal LoggedUser loggedUser){
        String email = loggedUser.getUsername();
        return orderService.getOrdersByEmail(email);
    }

    @GetMapping(value = "order/email/status")
    public List<ClientOrder> getOrdersByEmailStatus(@RequestParam String email, @RequestParam String status){
        return orderService.getOrdersByEmailStatus(email, status);
    }

    @PostMapping(value = "order/create")
    public String createOrder(@Valid @RequestBody ClientOrderTO clientOrderTO, BindingResult result, @AuthenticationPrincipal LoggedUser loggedUser){
        if (result.hasErrors()){
            return "Binding result error";
        }else {
            ClientOrder created = orderService.create(clientOrderTO, loggedUser);
            logger.info("created order number "+ created.getNumber_For1c());
            return (HttpStatus.CREATED.toString());
        }
    }

    @PutMapping(value = "order/update",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrder(@Valid @RequestBody ClientOrder clientOrder){
        orderService.update(clientOrder);
    }

    @GetMapping(value = "category/all")
    public List<NomenclatureCategory> getAllNomenclatureCategory(@AuthenticationPrincipal LoggedUser loggedUser){

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

    @GetMapping(value = "cart/email")
    public List<CartItem> getCartItemByEmail(@RequestParam String email){
        return cartService.getCartItemByEmail(email);
    }

    @PostMapping(value = "cart/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createOrder(@Valid @RequestBody CartItem cartItem,BindingResult result){
        if (result.hasErrors()){
            return getStringResponseEntity(result, logger);
        }else {
            CartItem created = cartService.create(cartItem);
            logger.info("added into cart "+ created.getNomenclature().size() + "positions");
            return new ResponseEntity<> (HttpStatus.CREATED);
        }
    }

}
