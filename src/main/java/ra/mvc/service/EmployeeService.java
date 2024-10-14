package ra.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.mvc.model.Employee;
import ra.mvc.model.Product;
import ra.mvc.repository.employee.IEmployeeRepo;
import ra.mvc.repository.product.IProductRepo;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private IEmployeeRepo employeerepo;

    public List<Employee> findAll() {
        return employeerepo.findAll();
    }

    @Transactional
    public void save(Employee employee) {
        employeerepo.save(employee);
    }

    public Employee findById(Integer id) {
        return employeerepo.findById(id);
    }

    @Transactional
    public void delete(Integer id) {
        employeerepo.deleteById(id);
    }
}
