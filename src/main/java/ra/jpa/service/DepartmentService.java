package ra.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.jpa.entity.Department;
import ra.jpa.entity.Employee;
import ra.jpa.repository.DepartmentRepo;
import ra.jpa.repository.EmployeeRepo;

import java.util.List;
@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepository;
    @Autowired
    private EmployeeService employeeService;

    public List<Department> findActiveDepartments() {
        return departmentRepository.findByStatusTrue();
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findById(int id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public boolean delete(int id) {
        Department department = findById(id);
        if (department != null) {
            // Lấy danh sách nhân viên thuộc phòng ban này
            List<Employee> employees = employeeService.findByDepartment(department);
            for (Employee employee : employees) {
                employeeService.deleteById(employee.getId());
            }
            department.setStatus(false);
            departmentRepository.save(department);
            return true;
        }
        return false; // Không tìm thấy phòng ban để xóa
    }
}
