package ra.mvc.repository.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import ra.mvc.model.Product;

import java.util.List;

@Component
public class ProductRepo implements IProductRepo {
   @PersistenceContext
   private EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p", Product.class);
        return query.getResultList();
    }

    @Override
    public Product findById(Long id) {
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p where id = :id", Product.class);
        return query.setParameter("id", id).getSingleResult();
    }

    @Override
    public void save(Product product) {
        if(product.getId() == null) {
            entityManager.persist(product);
        }else {
            Product pro = findById(product.getId());
            pro.setName(product.getName());
            pro.setStock(product.getStock());
            pro.setPrice(product.getPrice());
            pro.setStatus(product.getStatus());
            entityManager.merge(pro);
        }
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }
}
