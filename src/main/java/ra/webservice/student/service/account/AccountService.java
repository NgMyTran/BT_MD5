package ra.webservice.student.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ra.webservice.student.entity.Account;
import ra.webservice.student.repository.IAccountRepository;

@Service
public class AccountService implements IAccountService{

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public Account add(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account update(Account account, Integer id) {
        account.setId(id);
        return accountRepository.save(account);
    }

    @Override
    public void changeStatus(Integer id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            account.setStatus(!account.isStatus()); // Toggle trạng thái
            accountRepository.save(account);
        }
    }

    @Override
    public void remove(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Page<Account> findAll(Integer page, Integer size) {
        return accountRepository.findAll(PageRequest.of(page, size));
    }
}
