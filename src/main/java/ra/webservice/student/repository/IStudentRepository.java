package ra.webservice.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.webservice.student.entity.Student;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student, Long> {
List<Student> findAll();
}
