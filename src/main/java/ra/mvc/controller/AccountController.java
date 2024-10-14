package ra.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ra.mvc.model.Account;
import ra.mvc.service.AccountService;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public String listDepartments(Model model) {
        List<Account> accounts = accountService.findAll();
        model.addAttribute("accounts", accounts);
        return "account/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Integer id) {
        accountService.deleteById(id);
        return "redirect:/account";
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("account", new Account());
        return "account/add";
    }
    @PostMapping("/add")
    public String addDepartment(@ModelAttribute Account account) {
        accountService.save(account);
        return "redirect:/account";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Account account = accountService.findById(id);
        model.addAttribute("account", account);
        return "account/edit";
    }
    @PostMapping("/update")
    public String updateDepartment(@ModelAttribute Account account) {
        accountService.save(account);
        return "redirect:/account";
    }
}
