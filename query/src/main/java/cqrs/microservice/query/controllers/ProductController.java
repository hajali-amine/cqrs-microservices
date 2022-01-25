package cqrs.microservice.query.controllers;

import cqrs.microservice.query.models.Product;
import cqrs.microservice.query.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/query")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<Product> getProducts(){
        return this.productService.getProducts();
    }

    @GetMapping("/{ref}")
    public Product getProductByRef(@PathVariable String ref){
        return this.productService.getProductByRef(ref);
    }

    @DeleteMapping("/purge")
    public List<Product> purgeDb(){
        return this.productService.purgeDb();
    }
}
