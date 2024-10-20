package ra.jwt.service;

import ra.jwt.dto.request.AccountLogin;
import ra.jwt.dto.request.AccountRegister;
import ra.jwt.dto.response.JwtResponse;
import ra.jwt.entity.Account;

public interface IAccountService {
    void register(AccountRegister accountRegister);
    JwtResponse login(AccountLogin accountLogin);
}
