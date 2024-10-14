package ra.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.mvc.model.Product;
import ra.mvc.repository.product.IProductRepo;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private IProductRepo productRepo;

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Transactional
    public void save(Product product) {
        productRepo.save(product);
    }

    public Product findById(Long id) {
       return productRepo.findById(id);
    }

    @Transactional
    public void delete(Long id) {
        productRepo.deleteById(id);
    }
}
