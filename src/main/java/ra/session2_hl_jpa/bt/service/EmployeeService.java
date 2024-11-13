package ra.session2_hl_jpa.bt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.session2_hl_jpa.bt.model.Department;
import ra.session2_hl_jpa.bt.model.Employee;
import ra.session2_hl_jpa.bt.repository.EmployeeRepo;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepository;
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> findByDepartment(Department department) {
        return employeeRepository.findByDepartment(department);
    }

}
