package ra.webservice.student.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.webservice.student.entity.Product;
import ra.webservice.student.repository.ICategoryRepository;
import ra.webservice.student.entity.Category;
import ra.webservice.student.repository.IProductRepository;
import ra.webservice.student.service.product.ProductService;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private ProductService productService;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category add(Category category) {
        category.setCategoryId(null);
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category, Integer id) {
        category.setCategoryId(id);
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void changeCategoryStatus(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        List<Product> products = productService.findAllProductByCategoryId(id);
        for (Product product : products) {
            productService.remove(product.getProductId());
        }
        category.setStatus(!category.isStatus());
        categoryRepository.save(category);
    }

}
