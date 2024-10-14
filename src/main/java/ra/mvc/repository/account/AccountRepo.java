package ra.mvc.repository.account;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import ra.mvc.model.Account;
import ra.mvc.service.EmployeeService;

import java.util.List;

@Component
public class AccountRepo implements IAccountRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Account> findAll() {
        TypedQuery<Account> query = entityManager.createQuery("from Account ", Account.class);
        return query.getResultList();
    }

    @Override
    public Account findById(Integer id) {
        TypedQuery<Account> query = entityManager.createQuery("from Account where id= :id", Account.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public void save(Account account) {
        if (account.getAccountId()==null){
            entityManager.persist(account);
        }else {
            Account cus = findById(account.getAccountId());
            cus.setFullName(account.getFullName());
            cus.setStatus(account.isStatus());
            entityManager.merge(cus);
        }
    }

    @Override
    public void deleteById(Integer id) {
       entityManager.remove(findById(id));
    }
}
