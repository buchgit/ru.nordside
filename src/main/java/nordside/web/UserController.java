package nordside.web;

import nordside.model.User;
import nordside.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserController.REST_URL)
public class UserController {

    static final String REST_URL = "/user";

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @GetMapping(value = "/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getByEmail(@RequestParam String email) {
        return userService.getByEmail(email);
    }


}
