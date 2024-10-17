package ra.webservice.student.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import ra.webservice.student.entity.Student;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentResponse {
    private List<Student> data;
    private HttpStatus httpStatus;

}
