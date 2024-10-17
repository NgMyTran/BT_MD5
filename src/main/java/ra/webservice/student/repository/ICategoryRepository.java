package ra.webservice.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.webservice.student.entity.Category;
import ra.webservice.student.entity.Product;

import java.util.List;

public interface ICategoryRepository  extends JpaRepository<Category, Integer> {
}
