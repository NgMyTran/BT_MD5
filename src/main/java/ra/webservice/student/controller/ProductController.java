package ra.webservice.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.webservice.student.entity.Product;
import ra.webservice.student.service.product.IProductService;

import java.util.List;

@RestController
@RequestMapping("/api.tran.com/products")
public class ProductController {
    @Autowired
    private IProductService productService;


    @GetMapping
    public ResponseEntity<List<Product>> getAllCategories() {
        List<Product> productList = productService.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    //
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Product product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    //add
    @PostMapping
    public  ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product cate = productService.add(product);
        return new ResponseEntity<>(cate, HttpStatus.CREATED);
    }

    //update
    @PutMapping
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        Product cate = productService.findById(id);
        if (cate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.update(product,id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public void patchProduct(@PathVariable Integer id) {
        productService.changeProductStatus(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(product, HttpStatus.NO_CONTENT);
    }
}
