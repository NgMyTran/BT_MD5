package ra.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.jpa.entity.Department;

import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer> {
    List<Department> findByStatusTrue();
}
