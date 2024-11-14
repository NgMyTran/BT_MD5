package ra.securityweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(username,password);
        Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);
        // xư li
        return "redirect:/";
    }
    @GetMapping("/403")
    public String error403() {
        return "403";
    }
}
