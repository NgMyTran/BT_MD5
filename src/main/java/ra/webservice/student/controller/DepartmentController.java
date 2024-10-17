package ra.webservice.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.webservice.student.entity.Department;
import ra.webservice.student.service.department.IDepartmentService;

import java.util.List;


@RestController
@RequestMapping("/api.tran.com/departments")
public class DepartmentController {
        @Autowired
        private IDepartmentService departmentService;

        @GetMapping
        public ResponseEntity<List<Department>> getAllCategories() {
            List<Department> categories = departmentService.findAll();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }

        //
        @GetMapping("/{id}")
        public ResponseEntity<Department> DepartmentById(@PathVariable Integer id) {
            Department department = departmentService.findById(id);
            return new ResponseEntity<>(department, HttpStatus.OK);
        }

        //add
        @PostMapping
        public  ResponseEntity<Department> Department(@RequestBody Department department) {
            Department cate = departmentService.add(department);
            return new ResponseEntity<>(cate, HttpStatus.CREATED);
        }

        //update
        @PutMapping("/{id}")
        public ResponseEntity<Department> Department(@PathVariable Integer id, @RequestBody Department department) {
            Department cate = departmentService.findById(id);
            if (cate == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(departmentService.update(department,id), HttpStatus.OK);
        }

        //change status
        @PatchMapping("/{id}/status")
        public ResponseEntity<Void> Department(@PathVariable Integer id) {
            departmentService.changeDepartmentStatus(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Department> deleteDepartment(@PathVariable Integer id) {
            Department department = departmentService.findById(id);
            if (department == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            departmentService.remove(id);
            return new ResponseEntity<>(department, HttpStatus.NO_CONTENT);
        }
    }
