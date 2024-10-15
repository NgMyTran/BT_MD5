package ra.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.jpa.entity.Department;
import ra.jpa.entity.Employee;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
List<Employee> findByDepartment(Department department);
}
