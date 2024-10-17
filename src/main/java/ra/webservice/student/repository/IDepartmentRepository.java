package ra.webservice.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.webservice.student.entity.Department;

public interface IDepartmentRepository extends JpaRepository<Department, Integer> {
}
