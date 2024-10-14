package ra.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ra.mvc.model.Department;
import ra.mvc.service.DepartmentService;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String listDepartments(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "department/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable int id) {
        departmentService.delete(id);
        return "redirect:/department";
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("department", new Department());
        return "department/add";
    }
    @PostMapping("/add")
    public String addDepartment(@ModelAttribute Department department) {
        departmentService.save(department);
        return "redirect:/department";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Department department = departmentService.findById(id);
        model.addAttribute("department", department);
        return "department/edit";
    }
    @PostMapping("/update")
    public String updateDepartment(@ModelAttribute Department department) {
        departmentService.save(department);
        return "redirect:/department";
    }
}
