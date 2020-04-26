package com.demo.teststore.controller;

import com.demo.teststore.dto.CreateProductDto;
import com.demo.teststore.dto.ProductsDto;
import com.demo.teststore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

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
    public ProductsDto getProducts(@RequestParam(defaultValue="0") int page,
                                   @RequestParam(defaultValue = "10") int size) {
        return productService.getProducts(page, size);
    }

    @GetMapping
    @RequestMapping("/{category}")
    public ProductsDto getProductsByCategory(@PathVariable("category") String category,
                                                  @RequestParam(defaultValue="0") int page,
                                                  @RequestParam int size) {
        return productService.getProducts(category, page, size);
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
