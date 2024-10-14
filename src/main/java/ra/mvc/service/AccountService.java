package ra.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.mvc.model.Account;
import ra.mvc.model.Employee;
import ra.mvc.repository.account.AccountRepo;
import ra.mvc.repository.account.IAccountRepo;
import ra.mvc.repository.employee.IEmployeeRepo;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private IAccountRepo accountRepo;


    public List<Account> findAll() {
        return accountRepo.findAll();
    }
    @Transactional
    public void save(Account account) {
        accountRepo.save(account);
    }

    @Transactional
    public void deleteById(Integer id) {accountRepo.deleteById(id);}

    public Account findById(Integer id) {
        return accountRepo.findById(id);
    }
}
