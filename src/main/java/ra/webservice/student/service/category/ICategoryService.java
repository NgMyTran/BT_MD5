package ra.webservice.student.service.category;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.webservice.student.entity.Category;

import java.util.Optional;

@Repository
public interface ICategoryService{
    List<Category> findAll();
    Category findById(Integer id);
    Category add(Category category);
    Category update(Category category, Integer id);
    void changeCategoryStatus(Integer id);
    void remove(Integer id);
}