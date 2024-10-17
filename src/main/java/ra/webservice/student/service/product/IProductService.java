package ra.webservice.student.service.product;

import ra.webservice.student.entity.Category;
import ra.webservice.student.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findAll();
    Product findById(Integer id);
    void changeProductStatus(Integer id);
    Product add(Product product);
    Product update(Product product, Integer id);
    void remove(Integer id);
    List<Product> findAllProductByCategoryId(Integer categoryId);
}
