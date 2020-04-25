package com.demo.teststore.controller;

import com.demo.teststore.dto.CreateProductDto;
import com.demo.teststore.dto.ProductDto;
import com.demo.teststore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @GetMapping
    @RequestMapping("/{category}")
    public List<ProductDto> getProductsByCategory(@PathVariable("category") String category) {
        return productService.getProducts(category);
    }

    @PostMapping
    public void saveProduct(@ModelAttribute CreateProductDto product) {
        productService.saveProduct(product);
    }

    @DeleteMapping
    public void deleteProducts() {
        productService.deleteProducts();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") BigInteger id) {
        productService.deleteProduct(id);
    }
}
