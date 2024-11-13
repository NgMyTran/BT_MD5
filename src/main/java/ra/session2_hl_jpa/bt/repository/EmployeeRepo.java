package ra.session2_hl_jpa.bt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.session2_hl_jpa.bt.model.Department;
import ra.session2_hl_jpa.bt.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    List<Employee> findByDepartment(Department department);
}
