package ra.webservice.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ra.webservice.student.entity.Product;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    //?1 đại diện cho tham số đầu tiên của phương thức findAllProductByCategoryId, tức là categoryId.
    @Query("SELECT p FROM Product p WHERE p.category.categoryId = ?1")
    List<Product> findAllProductByCategoryId(Integer categoryId);
}
