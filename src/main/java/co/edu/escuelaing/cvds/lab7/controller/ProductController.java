package co.edu.escuelaing.cvds.lab7.controller;

import co.edu.escuelaing.cvds.lab7.model.Product;
import co.edu.escuelaing.cvds.lab7.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api")
public class ProductController {
    private final ProductService productService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService= productService;
    }

    @GetMapping("/product")
    @ResponseBody
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Optional<Product> product = productService.getProduct(id);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product savedEmployee = productService.addProduct(product);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/product")
    public ResponseEntity<Product> updateProduct(
            @RequestBody Product updatedProduct
    ){
        int productId = updatedProduct.getProductId();

        Optional<Product> existingProduct = productService.getProduct(productId);
        if (existingProduct.isPresent()) {

            Product product = productService.updateProduct(updatedProduct);
            return ResponseEntity.ok(product);
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id){

        productService.deleteProduct(id);

        return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
    }


    @PutMapping("/product/{id}")
    public ResponseEntity<Product>  patchProduct(
            @PathVariable("id") int id,
            @RequestBody Product product
    ){
        product.setProductId(id);
        Product updatedProduct = productService.updateProduct(product);
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
    }




}
