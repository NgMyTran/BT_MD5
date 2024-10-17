package ra.webservice.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.webservice.student.entity.Category;
import ra.webservice.student.service.category.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api.tran.com/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public  ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    //
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
        Category category = categoryService.findById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    //add
    @PostMapping
    public  ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category cate = categoryService.add(category);
        return new ResponseEntity<>(cate, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        Category cate = categoryService.findById(id);
        if (cate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryService.update(category,id), HttpStatus.OK);
    }

    //change status
    @PatchMapping("/{id}/status")
    public void patchCategory(@PathVariable Integer id) {
        categoryService.changeCategoryStatus(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Integer id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.remove(id);
        return new ResponseEntity<>(category, HttpStatus.NO_CONTENT);
    }
}
