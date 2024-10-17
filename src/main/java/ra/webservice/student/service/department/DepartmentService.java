package ra.webservice.student.service.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.webservice.student.entity.Department;
import ra.webservice.student.entity.Employee;
import ra.webservice.student.repository.IDepartmentRepository;
import ra.webservice.student.service.employee.EmployeeService;

import java.util.List;

@Service
public class DepartmentService implements IDepartmentService {
    @Autowired
    private IDepartmentRepository departmentRepository;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department add(Department department) {
        department.setDepartmentId(null);
        return departmentRepository.save(department);
    }

    @Override
    public Department update(Department department, Integer id) {
        department.setDepartmentId(id);
        return departmentRepository.save(department);
    }

    @Override
    public Department findById(Integer id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Integer id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public void changeDepartmentStatus(Integer id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        List<Employee> employees = employeeService.findAllEmployeeByDepartmentId(id);
        for (Employee employee : employees) {
            employeeService.remove(employee.getEmployeeId());
        }
        department.setStatus(!department.isStatus());
        departmentRepository.save(department);
    }

}
