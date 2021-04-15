package nordside.web;

import nordside.model.User;
import nordside.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test/")
public class TestController {

    @Autowired
    UserService userService;

    @GetMapping(value = "email",produces = MediaType.APPLICATION_JSON_VALUE)
    public User test(){
        return userService.getByEmail("user@gmail.com");
    }

}
