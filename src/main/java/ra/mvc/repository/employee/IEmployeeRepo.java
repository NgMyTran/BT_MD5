package ra.mvc.repository.employee;

import ra.mvc.model.Employee;
import ra.mvc.repository.IRepository;

import java.util.List;

public interface IEmployeeRepo extends IRepository<Employee, Integer> {
    List<Employee> findByDepartmentId(Integer departmentId);

}
