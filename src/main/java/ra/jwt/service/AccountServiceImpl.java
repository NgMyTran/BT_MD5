package ra.jwt.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.jwt.dto.request.AccountLogin;
import ra.jwt.dto.request.AccountRegister;
import ra.jwt.dto.response.JwtResponse;
import ra.jwt.entity.Account;
import ra.jwt.entity.RoleName;
import ra.jwt.entity.Roles;
import ra.jwt.repository.IAccountRepository;
import ra.jwt.security.account_principal.UserDetailsCustom;
import ra.jwt.security.jwt.JwtProvider;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private final IAccountRepository userRepository;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationProvider authenticationProvider;
    private final JwtProvider jwtProvider;

    @Override
    public void register(AccountRegister accountRegister) {
        Set<Roles> roles = new HashSet<>();
        if (accountRegister.getRoles().isEmpty()) {
            roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
        } else {
            accountRegister.getRoles().forEach(
                    role -> {
                        switch (role) {
                            case "admin":
                                roles.add(roleService.findByRoleName(RoleName.ROLE_ADMIN));
                            case "user":
                                roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
                                break;
                            default:
                                throw new RuntimeException("role not found");
                        }
                    }
            );
        }
        Account users = Account.builder()
                .fullName(accountRegister.getFullName())
                .password(passwordEncoder.encode(accountRegister.getPassword()))
                .userName(accountRegister.getUserName())
                .roles(roles)
                .build();
        userRepository.save(users);
    }

    @Override
    public JwtResponse login(AccountLogin accountLogin) {
        Authentication authentication;
        try {
            authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(accountLogin.getUserName(), accountLogin.getPassword()));
        } catch (AuthenticationException e) {
            throw new RuntimeException("Username or Password is incorrect");
        }
        UserDetailsCustom userPrincipal = (UserDetailsCustom) authentication.getPrincipal();
        return JwtResponse.builder()
                .accessToken(jwtProvider.generateToken(userPrincipal))
                .fullName(userPrincipal.getFullName())
                .userName(userPrincipal.getUsername())
                .roles(userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .build();
    }

}
