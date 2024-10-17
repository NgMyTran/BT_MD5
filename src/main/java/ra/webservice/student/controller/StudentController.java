package ra.webservice.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.webservice.student.dto.response.StudentResponse;
import ra.webservice.student.entity.Student;
import ra.webservice.student.repository.IStudentRepository;

import java.util.List;

@RestController
@RequestMapping("/api.tran.com/students")
public class StudentController {

    @Autowired
    private IStudentRepository iStudentRepository;

    @GetMapping
    public ResponseEntity<StudentResponse> getAll() {
        List<Student> students = iStudentRepository.findAll();
        //contructor
//        return new ResponseEntity<>(new StudentResponse(students,HttpStatus.OK), HttpStatus.OK);
        //phg thuc tinhx
    return ResponseEntity.ok().body(new StudentResponse(students,HttpStatus.OK));
    }
}
