package ra.webservice.student.service.employee;

import ra.webservice.student.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();
    Employee findById(Integer id);
    void changeEmployeeStatus(Integer id);
    Employee add(Employee employee);
    Employee update(Employee employee, Integer id);
    void remove(Integer id);
    List<Employee> findAllEmployeeByDepartmentId(Integer categoryId);
}
