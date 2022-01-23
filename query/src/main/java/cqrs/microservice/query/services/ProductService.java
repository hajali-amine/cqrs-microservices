package cqrs.microservice.query.services;

import cqrs.microservice.query.models.Product;
import cqrs.microservice.query.queries.GetProductsQuery;
import cqrs.microservice.query.repositories.ProductRepository;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class ProductService {
    private ProductRepository productRepository;
    private QueryGateway queryGateway;

    public ProductService(ProductRepository productRepository, QueryGateway queryGateway) {
        this.productRepository = productRepository;
        this.queryGateway = queryGateway;
    }

    public List<Product> getProd(){
        GetProductsQuery getProductsQuery = new GetProductsQuery();
        System.out.println("hi");
        List<Product> productList = this.queryGateway.query(getProductsQuery, ResponseTypes.
                multipleInstancesOf(Product.class)).join();
        System.out.println("hiii");
        productList.forEach(System.out::println);
        return productList;
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }
}
