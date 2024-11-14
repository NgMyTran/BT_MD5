package ra.restfulApi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.restfulApi.exception.NotFoundElementException;
import ra.restfulApi.model.dto.req.ProductCreateDto;
import ra.restfulApi.model.dto.res.ResponseDto;
import ra.restfulApi.model.entity.Product;
import ra.restfulApi.service.IProductService;

import java.util.List;

@RestController
@RequestMapping("api.com/v1/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    // Lấy thông tin sản phẩm theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Product>> getById(@PathVariable Long id) throws NotFoundElementException {
        return ResponseEntity.ok(productService.getById(id));
    }


    // Thêm mới
    @PostMapping()
    public ResponseEntity<ResponseDto<Product>> createProducts(@Valid @RequestBody ProductCreateDto request){
        return new ResponseEntity<>(productService.createProduct(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<Product>> updateProduct(@PathVariable Long id, @RequestBody Product product) throws NotFoundElementException {
        // Kiểm tra xem sản phẩm có tồn tại không
        ResponseDto<Product> existingProduct = productService.getById(id);
        if (existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Trả về 404 nếu không tìm thấy sản phẩm
        }
        //đảm bảo ID không thay đổi
        product.setId(id);
        Product updatedProduct = productService.updateProduct(product);
        // Trả về ResponseDto với thông tin sản phẩm đã cập nhật
        return ResponseEntity.ok(new ResponseDto<>(200, HttpStatus.OK, updatedProduct));
    }

    // Xóa sản phẩm
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteProduct(@PathVariable Long id) throws NotFoundElementException {
        ResponseDto<Product> existingProduct = productService.getById(id);
        if (existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        productService.deleteProduct(id);
        // Trả về ResponseDto với mã trạng thái 204 khi xóa thành công
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto<>(204, HttpStatus.NO_CONTENT, null));
    }

}
