package nordside.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/*")
public class RootController {

    static final String REST_URL = "/";

    @GetMapping("/user/1")
    public String root1(){
        return "index";
    }
    @GetMapping("/user/2")
    public String root2(){
        return "index";
    }

}
