package ra.webservice.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.webservice.student.entity.Employee;
import ra.webservice.student.service.employee.IEmployeeService;
import ra.webservice.student.service.employee.EmployeeService;

import java.util.List;
@RestController
@RequestMapping("/api.tran.com/employees")
public class EmployeeController {
        @Autowired
        private IEmployeeService employeeService;

        @GetMapping
        public ResponseEntity<List<Employee>> getAllCategories() {
            List<Employee> employeeList = employeeService.findAll();
            return new ResponseEntity<>(employeeList, HttpStatus.OK);
        }

        //
        @GetMapping("/{id}")
        public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
            Employee employee = employeeService.findById(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }

        //add
        @PostMapping
        public  ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
            Employee cate = employeeService.add(employee);
            return new ResponseEntity<>(cate, HttpStatus.CREATED);
        }

        //update
        @PutMapping
        public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
            Employee cate = employeeService.findById(id);
            if (cate == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(employeeService.update(employee,id), HttpStatus.OK);
        }

        @PatchMapping("/{id}/status")
        public void patchEmployee(@PathVariable Integer id) {
            employeeService.changeEmployeeStatus(id);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Employee> deleteEmployee(@PathVariable Integer id) {
            Employee employee = employeeService.findById(id);
            if (employee == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            employeeService.remove(id);
            return new ResponseEntity<>(employee, HttpStatus.NO_CONTENT);
        }
    }

