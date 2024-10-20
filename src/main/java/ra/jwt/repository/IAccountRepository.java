package ra.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.jwt.entity.Account;

import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Account, Long> {
Optional<Account> findByUsername(String userName);
}
