package ra.webservice.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.webservice.student.entity.Account;

public interface IAccountRepository extends JpaRepository<Account, Integer> {
}
