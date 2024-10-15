package ra.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.jpa.entity.Department;
import ra.jpa.entity.Employee;
import ra.jpa.service.DepartmentService;
import ra.jpa.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String showAllEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employee/list";
    }

    @GetMapping("/add")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.findActiveDepartments());
        return "employee/add";
    }
//    @PostMapping("/add")
//    public String saveEmployee(Employee employee) {
//        employeeService.save(employee);
//        return "redirect:/employee";
//    }
@PostMapping("/add")
public String saveEmployee(@ModelAttribute Employee employee) {
    // Tìm Department từ departmentId
    if (employee.getDepartment() != null) {
        Department department = departmentService.findById(employee.getDepartmentId());
        employee.setDepartment(department);
    }
    employeeService.save(employee);
    return "redirect:/employee";
}

    @GetMapping("/edit/{id}")
    public String showEditEmployeeForm(@PathVariable int id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        model.addAttribute("departments", departmentService.findAll()); // Thêm dòng này
        return "employee/edit";
    }
//    @GetMapping("/edit/{id}")
//    public String showEditEmployeeForm(@PathVariable int id, Model model) {
//        model.addAttribute("employee", employeeService.findById(id));
//        return "employee/edit";
//    }
//    @PostMapping("/update")
//    public String updateEmployee(Employee employee) {
//        employeeService.save(employee);
//        return "redirect:/employee";
//    }
@PostMapping("/update")
public String updateEmployee(@ModelAttribute Employee employee) {
    if (employee.getDepartmentId() != null) {
        Department department = departmentService.findById(employee.getDepartmentId());
        employee.setDepartment(department);
    }
    employeeService.save(employee);
    return "redirect:/employee";
}
    @GetMapping("/delete/{id}")
    public String toggleStatus(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            employee.setStatus(!employee.isStatus());
            employeeService.save(employee);
        }
        return "redirect:/employee";
    }
}
