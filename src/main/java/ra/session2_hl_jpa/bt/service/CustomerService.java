package ra.session2_hl_jpa.bt.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.session2_hl_jpa.bt.model.Customer;
import ra.session2_hl_jpa.bt.repository.CustomerRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepository;

    public Set<Customer> getCustomersByIds(Set<Integer> ids) {
        return new HashSet<>(customerRepository.findAllById(ids));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).orElse(null);
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}
