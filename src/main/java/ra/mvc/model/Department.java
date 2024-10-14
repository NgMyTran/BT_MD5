package ra.mvc.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departmentId;
    private String name;
    private boolean status = true;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Employee> employee;

    // Constructors, getters, setters
    public Department() {
    }

    public Department(int id, String name, boolean status) {
        this.departmentId = id;
        this.name = name;
        this.status = status;
    }

    public Integer getId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }

    public boolean isStatus() {
        return status;
    }

    public Set<Employee> getEmployees() {
        return employee;
    }

    public void setId(int id) {
        this.departmentId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setEmployees(Set<Employee> employee) {
        this.employee = employee;
    }
}
