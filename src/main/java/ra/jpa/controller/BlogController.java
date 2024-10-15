package ra.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.jpa.entity.Blog;
import ra.jpa.service.BlogService;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public String showAllBlogs(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Blog> blogs = blogService.findAll(pageable);
        model.addAttribute("blogs", blogs);
        model.addAttribute("currentPage", page);
        return "blog/list";
    }

    @GetMapping("/add")
    public String showAddBlogForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog/add";
    }

    @PostMapping("/add")
    public String saveBlog(@ModelAttribute Blog blog) {
        blogService.save(blog);
        return "redirect:/blog";
    }

    @GetMapping("/edit/{id}")
    public String showEditBlogForm(@PathVariable int id, Model model) {
        model.addAttribute("blog", blogService.findById(id).orElse(new Blog()));
        return "blog/edit";
    }

    @PostMapping("/update")
    public String updateBlog(@ModelAttribute Blog blog) {
        blogService.save(blog);
        return "redirect:/blog";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable int id) {
        blogService.deleteById(id);
        return "redirect:/blog";
    }
}
