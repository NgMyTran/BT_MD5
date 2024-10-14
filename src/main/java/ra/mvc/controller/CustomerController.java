package ra.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.mvc.model.Customer;
import ra.mvc.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping
    public String showListCustomers(Model model){
        model.addAttribute("customers",customerService.findAll());
        return "customers/list";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        customerService.deleteById(id);
        return "redirect:/customer";
    }
    @GetMapping("/add")
    public String add(){
        return "customers/add";
    }
    @PostMapping("/add")
    public String doAdd(@ModelAttribute Customer customer){
        customerService.save(customer);
        return "redirect:/customer";
    }
    @PostMapping("/update")
    public String doUpdate(@ModelAttribute Customer customer){
        customerService.save(customer);
        return "redirect:/customer";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Customer cus = customerService.findById(id);
        model.addAttribute("customer",cus);
        return "customers/edit";
    }
}