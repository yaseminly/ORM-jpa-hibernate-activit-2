package ma.emsi.univertity;

import ma.emsi.univertity.entities.Product;
import ma.emsi.univertity.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class UnivertityApplication implements CommandLineRunner {
    @Autowired
private ProductRepository productRepository;
    public static void main(String[] args)  {
        SpringApplication.run(UnivertityApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Product(null,"computer",4300,3));
        productRepository.save(new Product(null,"printer",1200,4));
        productRepository.save(new Product(null,"smart     phone",3200,3));
        List<Product> products=productRepository.findAll();
        products.forEach(p->{
            System.out.println(p.toString());
        });
       Product product=productRepository.findById(Long.valueOf(1)).get();
       System.out.println("***********");
       System.out.println(product.getId());
       System.out.println(product.getName());
       System.out.println(product.getQuantity());
        System.out.println("***********");

        List<Product> productList = productRepository.findByNameContaining("C");
        productList.forEach(p->{
            System.out.println(p);
        });
        System.out.println("----------------");
        List<Product> productList2 = productRepository.search("%C%");
        productList.forEach(p->{
            System.out.println(p);
        });

        System.out.println("----------------");
        List<Product> productList3 = productRepository.findByPriceGreaterThan(1000);
        productList.forEach(p->{
            System.out.println(p);
        });

        System.out.println("----------------");
        List<Product> productsList4 = productRepository.searchByprice(1000);
        productList.forEach(p->{
            System.out.println(p);
        });


    }

}
