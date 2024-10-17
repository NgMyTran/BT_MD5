package ra.webservice.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.webservice.student.entity.Employee;

import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByDepartmentId(Integer departmentId);
}
