package ra.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.jwt.dto.request.AccountLogin;
import ra.jwt.dto.request.AccountRegister;
import ra.jwt.dto.response.JwtResponse;
import ra.jwt.service.IAccountService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final IAccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> handleLogin(@RequestBody AccountLogin userLogin) {
        return new ResponseEntity<>(accountService.login(userLogin), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> handleRegister(@RequestBody AccountRegister userRegister) {
        accountService.register(userRegister);
        return new ResponseEntity<>("Register successfully", HttpStatus.CREATED);
    }
}
