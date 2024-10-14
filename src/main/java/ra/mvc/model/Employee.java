package ra.mvc.model;

import jakarta.persistence.*;


@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    private String fullName;
    private String address;
    private String phone;
    private boolean status =true;

    @ManyToOne
    @JoinColumn(name = "departmentId", nullable = false)
    private Department department;

    // Constructors, getters, setters
    public Employee() {
    }

    public Employee(Integer id, String fullName, String address, String phone, Department department, boolean status) {
        this.employeeId = id;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.department = department;
        this.status = status;
    }

    public Integer getId() {
        return employeeId;
    }

    public void setId(Integer id) {
        this.employeeId = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
