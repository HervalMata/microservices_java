package com.herval.spring.controller;

import com.herval.events.ProductEvent;
import com.herval.spring.model.Product;
import com.herval.spring.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductController extends AbstractController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        ProductEvent producCreatedtEvent = new ProductEvent("Um produto foi criado", savedProduct);
        eventPublisher.publishEvent(producCreatedtEvent);
        return ResponseEntity.ok().body("Novo Produto foi salvo com ID:" + savedProduct.getId());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        Optional<Product> returnedProduct = productService.get(id);
        Product product = returnedProduct.get();
        ProductEvent producReturnedtEvent = new ProductEvent("Um produto foi listado", product);
        eventPublisher.publishEvent(producReturnedtEvent);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/product")
    public  @ResponseBody Page<Product> getProductsByPage(
            @RequestParam(value = "pageNumber", required = true, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = true, defaultValue = "20") Integer pageSize
    ) {
        Page<Product> pagedProducts = productService.getProductsByPage(pageNumber, pageSize);
        return pagedProducts;
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        checkResourceFound(this.productService.get(id));
        productService.update(id, product);
        return ResponseEntity.ok().body("O Produto foi atualizado com sucesso.");
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        checkResourceFound(this.productService.get(id));
        productService.delete(id);
        return ResponseEntity.ok().body("O Produto foi removido com sucesso.");
    }
}
