package ra.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.mvc.model.Product;
import ra.mvc.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String showList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/product";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "/product/add";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute Product product) {
    productService.save(product);
    return "redirect:/product";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "/product/edit";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/product";
    }
}
