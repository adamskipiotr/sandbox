package com.pada.sandbox.document;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
     return productService.getAllProducts();
    }

    @PostMapping
    public void addNewProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

}
