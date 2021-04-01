package nordside.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {

    static final String REST_URL = "/";

    @GetMapping("/")
    public void getById(){

    }

}
