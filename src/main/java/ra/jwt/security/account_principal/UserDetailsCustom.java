package ra.jwt.security.account_principal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ra.jwt.entity.Account;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDetailsCustom implements UserDetails {
    private Long id;
    private String fullName;
    private String password;
    private String userName;
    private boolean status;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetails getUserDetails(Account users) {
        return UserDetailsCustom.builder()
                .id(users.getId())
                .fullName(users.getFullName())
                .password(users.getPassword())
                .authorities(users.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).toList())
                .build();
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  this.authorities;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status;
    }



}
