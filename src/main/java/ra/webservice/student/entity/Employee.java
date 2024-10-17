package ra.webservice.student.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    private String fullName;
    private String address;
    private String phone;
    private boolean status = true;


    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false )
    private Department department;
}
