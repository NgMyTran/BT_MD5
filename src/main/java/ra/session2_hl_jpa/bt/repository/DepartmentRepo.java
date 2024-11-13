package ra.session2_hl_jpa.bt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.session2_hl_jpa.bt.model.Department;

import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer> {
    List<Department> findByStatusTrue();
}
