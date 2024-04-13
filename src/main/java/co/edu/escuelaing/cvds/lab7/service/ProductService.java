package co.edu.escuelaing.cvds.lab7.service;

import co.edu.escuelaing.cvds.lab7.model.Product;
import co.edu.escuelaing.cvds.lab7.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public  Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProduct(int id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Product product) {
        System.out.println(product);
        if (productRepository.findById(product.getProductId()).isPresent()) {
            return productRepository.save(product);
        }
        return null;
    }



    public void deleteProduct(int product_id) {
        productRepository.deleteById(product_id);
    }

}