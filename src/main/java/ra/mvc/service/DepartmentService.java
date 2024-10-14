package ra.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.mvc.model.Department;
import ra.mvc.model.Employee;
import ra.mvc.repository.department.IDepartmentRepo;
import ra.mvc.repository.employee.IEmployeeRepo;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private IDepartmentRepo departmentRepo;
    @Autowired
    private IEmployeeRepo employeeRepo;

    public List<Department> findAll() {
        return departmentRepo.findAll();
    }
    @Transactional
    public void save(Department department) {
        departmentRepo.save(department);
    }

    public Department findById(Integer id) {
        return departmentRepo.findById(id);
    }


@Transactional
public void delete(Integer id) {
    List<Employee> employees = employeeRepo.findByDepartmentId(id);
    if (employees != null) {
        for (Employee employee : employees) {
            employeeRepo.deleteById(employee.getId());
        }
    }
    departmentRepo.deleteById(id);
}

    public List<Employee> findEmployeesByDepartmentId(Integer departmentId) {
        return employeeRepo.findByDepartmentId(departmentId);
    }
}
