package ra.restfulApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ra.restfulApi.exception.DupplicateException;
import ra.restfulApi.exception.NotFoundElementException;
import ra.restfulApi.model.dto.req.ProductCreateDto;
import ra.restfulApi.model.dto.res.ResponseDto;
import ra.restfulApi.model.entity.Product;
import ra.restfulApi.repository.IProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ResponseDto<Product> createProduct(ProductCreateDto request) {
        if (productRepository.existsByName(request.getName())){
            throw  new DupplicateException("Tên sản phẩm đa tôn tại");
        }
        // biên đổi thành entity
        Product product = Product.builder()
                .name(request.getName())
                .stock(request.getStock())
                .image(request.getImage())
                .price(request.getPrice())
                .description(request.getDescription())
                .status(true)
                .build();
        Product newProduct = productRepository.save(product);
        return new ResponseDto<>(201, HttpStatus.CREATED,newProduct);
    }

    @Override
    public ResponseDto<Product> getById(Long id) throws NotFoundElementException {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundElementException("Product id not found"));
        return new ResponseDto<>(200, HttpStatus.OK,product);
    }

    @Override
    public List<Product> getAllByStatusIsTrue() {
        return productRepository.getListProductsByStatusIsTrue();
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
