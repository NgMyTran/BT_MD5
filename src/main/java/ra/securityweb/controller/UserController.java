package ra.securityweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping
    public String home(){
        return  "index";
    }
    @GetMapping("/user")
    public String user() {
        return "user";
    }
}
