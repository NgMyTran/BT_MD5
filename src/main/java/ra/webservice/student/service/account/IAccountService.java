package ra.webservice.student.service.account;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import ra.webservice.student.entity.Account;

@Repository
public interface IAccountService {
    Page<Account> findAll(Integer page, Integer size);
    Account add(Account account);
    Account update(Account account, Integer id);
    void changeStatus(Integer id);
    void remove(Integer id);
}
