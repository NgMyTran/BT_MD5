package ra.restfulApi.service;

import ra.restfulApi.exception.NotFoundElementException;
import ra.restfulApi.model.dto.req.ProductCreateDto;
import ra.restfulApi.model.dto.res.ResponseDto;
import ra.restfulApi.model.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    ResponseDto<Product> createProduct(ProductCreateDto request);
    ResponseDto<Product> getById(Long id) throws NotFoundElementException;
    Product updateProduct(Product product);
    void deleteProduct(Long id);
    List<Product> getAllByStatusIsTrue();
}
