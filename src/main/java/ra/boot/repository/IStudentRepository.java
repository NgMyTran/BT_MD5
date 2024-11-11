package ra.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.boot.model.entity.Student;

public interface IStudentRepository extends JpaRepository<Student,Integer> {
    boolean existsByPhone(String phone);
}
