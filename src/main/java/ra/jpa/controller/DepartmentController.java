package ra.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.jpa.entity.Department;
import ra.jpa.service.DepartmentService;
import ra.jpa.service.DepartmentService;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String showAllEmployees(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "department/list";
    }

    @GetMapping("/add")
    public String showAddDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "department/add";
    }
    @PostMapping("/add")
    public String saveDepartment(Department department) {
        departmentService.save(department);
        return "redirect:/department";
    }
    @GetMapping("/edit/{id}")
    public String showEditDepartmentForm(@PathVariable int id, Model model) {
        model.addAttribute("department", departmentService.findById(id));
        return "department/edit";
    }
    @PostMapping("/update")
    public String updateDepartment(Department department) {
        departmentService.save(department);
        return "redirect:/department";
    }

    @GetMapping("/delete/{id}")
    public String changeStatus(@PathVariable int id) {
       departmentService.delete(id);
        return "redirect:/department";
    }
}
