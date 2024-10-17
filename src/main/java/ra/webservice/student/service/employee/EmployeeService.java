package ra.webservice.student.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.webservice.student.entity.Employee;
import ra.webservice.student.repository.IEmployeeRepository;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void changeEmployeeStatus(Integer id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        employee.setStatus(false);
        employeeRepository.save(employee);
    }

    @Override
    public Employee add(Employee employee) {
        employee.setEmployeeId(null);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee, Integer id) {
        employee.setEmployeeId(id);
        return employeeRepository.save(employee);
    }

    @Override
    public void remove(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findAllEmployeeByDepartmentId(Integer departmentId) {
        return employeeRepository.findAllByDepartmentId(departmentId);
    }
}
