package ra.jpa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;
    private String address;
    private String phone;
    private LocalDate dateOfBirth;
    private boolean status=true;
    @ManyToOne
    @JoinColumn(columnDefinition = "departmentId")
    private Department department;

    public Integer getDepartmentId() {
        return department != null ? department.getDepartmentId() : null;
    }
}


