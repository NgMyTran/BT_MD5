package ra.session2_hl_jpa.bt.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.session2_hl_jpa.bt.model.Customer;
import ra.session2_hl_jpa.bt.model.Project;
import ra.session2_hl_jpa.bt.service.CustomerService;
import ra.session2_hl_jpa.bt.service.ProjectService;

import java.util.Set;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String listProjects(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "project/list";
    }

    @GetMapping("/add")
    public String showAddProjectForm(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "project/form";
    }

    @PostMapping("/save")
    public String saveProject(@ModelAttribute("project") Project project, @RequestParam(required = false) Set<Integer> customerIds) {
        // Nếu có các khách hàng được chọn, thêm họ vào project
        if (customerIds != null) {
            Set<Customer> selectedCustomers = customerService.getCustomersByIds(customerIds);
            project.setCustomers(selectedCustomers);
        }
        projectService.saveProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/edit/{id}")
    public String showEditProjectForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("project", projectService.getProjectById(id));
        model.addAttribute("customers", customerService.getAllCustomers());
        return "project/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable("id") Integer id) {
        Project project = projectService.getProjectById(id);

        // Xóa liên kết dự án khỏi danh sách dự án của khách hàng
        for (Customer customer : project.getCustomers()) {
            customer.getProjects().remove(project);
            customerService.saveCustomer(customer); // Cập nhật lại customer
        }

        projectService.deleteProject(id);
        return "redirect:/projects";
    }

}

