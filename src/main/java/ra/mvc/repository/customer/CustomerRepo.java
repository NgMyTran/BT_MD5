package ra.mvc.repository.customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ra.mvc.model.Customer;
import ra.mvc.repository.customer.ICustomerRepo;

import java.util.List;
@Repository
public class CustomerRepo implements ICustomerRepo {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = entityManager.createQuery("from Customer ", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(Long id) {
        TypedQuery<Customer> query = entityManager.createQuery("from Customer where id= :id", Customer.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public void save(Customer customer) {
        if (customer.getId()==null){
            entityManager.persist(customer);
        }else {
            Customer cus = findById(customer.getId());
            cus.setName(customer.getName());
            cus.setDob(customer.getDob());
            entityManager.merge(cus);
        }
    }
    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }
}