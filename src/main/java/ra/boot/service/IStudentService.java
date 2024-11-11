package ra.boot.service;

import org.springframework.web.multipart.MultipartFile;
import ra.boot.model.dto.StudentCreateDto;
import ra.boot.model.dto.StudentEditDto;
import ra.boot.model.entity.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<Student> findAll();
    void add(StudentCreateDto request);
    Optional<Student> findById(Integer id);
    void update(Integer id, StudentEditDto request);
    void delete(Integer id);
    String saveImage(MultipartFile file);
}
