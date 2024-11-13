package ra.session2_hl_jpa.bt.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String technology;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean status;


    @ManyToMany(mappedBy = "projects") // Quan hệ hai chiều
    private Set<Customer> customers;
}
