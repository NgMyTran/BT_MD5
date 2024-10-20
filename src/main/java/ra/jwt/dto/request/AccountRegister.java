package ra.jwt.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.Set;


@Data
@Builder
public class AccountRegister {
    private String fullName;
    private String userName;
    private String password;
    private Set<String> roles;
}
