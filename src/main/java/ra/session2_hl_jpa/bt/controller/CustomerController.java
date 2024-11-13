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
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProjectService projectService;


    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customer/list";
    }

    @GetMapping("/add")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("projects", projectService.getAllProjects()); // Lấy danh sách dự án
        return "customer/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditCustomerForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        model.addAttribute("projects", projectService.getAllProjects()); // Lấy danh sách dự án
        return "customer/form";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, @RequestParam(required = false) Set<Integer> projectIds) {
        // Nếu có các dự án được chọn, thêm chúng vào customer
        if (projectIds != null) {
            Set<Project> selectedProjects = projectService.getProjectsByIds(projectIds);
            customer.setProjects(selectedProjects);
        }
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }


    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id) {
        Customer customer = customerService.getCustomerById(id);

        // Kiểm tra nếu khách hàng có dự án nào không
        if (customer.getProjects().isEmpty()) {
            // Nếu không có dự án, xóa khách hàng
            customerService.deleteCustomer(id);
        } else {
            // Nếu có dự án, chỉ thay đổi trạng thái
            customer.setStatus(false);
            customerService.saveCustomer(customer);
        }
        return "redirect:/customers";
    }

}
