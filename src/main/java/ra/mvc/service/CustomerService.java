package ra.mvc.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.mvc.model.Customer;
import ra.mvc.repository.ICustomerRepo;

import java.util.List;

@Service

public class CustomerService {
    @Autowired
    private ICustomerRepo customerRepo;
    @Transactional
    public void save(Customer customer){
        customerRepo.save(customer);
    }
    public List<Customer> findAll(){
        return customerRepo.findAll();
    }
    public Customer findById(Long id){
        return customerRepo.findById(id);
    }
    @Transactional
    public  void deleteById(Long id){
        customerRepo.deleteById(id);
    }
}