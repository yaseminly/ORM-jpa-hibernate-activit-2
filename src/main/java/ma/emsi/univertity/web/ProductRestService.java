package ma.emsi.univertity.web;


import ma.emsi.univertity.entities.Product;
import ma.emsi.univertity.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ProductRestService {
    @Autowired
    private ProductRepository productRepository;
    //consulter tous les produits
    @GetMapping("/products")
    public List<Product> products(){
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Product findProduct(@PathVariable Long id){
        return productRepository.findById(id).get();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/products/search/{keyword}")
    public List<Product> searchProducts(@PathVariable String keyword) {
        return productRepository.findByNameContaining(keyword);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());

        return productRepository.save(product);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "Produit supprimé avec succès";
    }
}