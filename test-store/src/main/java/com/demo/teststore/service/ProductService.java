package com.demo.teststore.service;

import com.demo.teststore.document.Product;
import com.demo.teststore.dto.CreateProductDto;
import com.demo.teststore.dto.ProductDto;
import com.demo.teststore.dto.ProductListDto;
import com.demo.teststore.repository.ProductRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductListDto getProducts(int page, int size) {
        List<ProductDto> productDtoList = StreamSupport.stream(productRepository.findAll(PageRequest.of(page, size))
                .spliterator(), true)
                .map(this::fromDocument)
                .collect(Collectors.toList());
        long totalAmount = productRepository.count();
        return new ProductListDto(productDtoList, totalAmount);
    }

    public ProductListDto getProducts(String category, int page, int size) {
        List<ProductDto> productDtoList = productRepository.findByCategory(category, PageRequest.of(page, size))
                .parallelStream()
                .map(this::fromDocument)
                .collect(Collectors.toList());
        long totalAmount = productRepository.countByCategory(category);
        return new ProductListDto(productDtoList, totalAmount);
    }

    public void saveProduct(CreateProductDto createProductDto) {
        try {
            productRepository.save(toDocument(createProductDto));
        } catch (IOException e) {
            log.error("Failed to create product with name {}", createProductDto.getName());
        }
    }

    public void deleteProducts() {
        productRepository.deleteAll();
    }

    public void deleteProduct(BigInteger id) {
        productRepository.deleteById(id);
    }

    private ProductDto fromDocument(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setImageBase64(product.getImageBase64());
        dto.setPrice(product.getPrice());
        return dto;
    }

    private Product toDocument(CreateProductDto dto) throws IOException {
        Product product = new Product();
        product.setName(dto.getName());
        product.setImageBase64(multiPartFileToBase64(dto.getImage()));
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
        return product;
    }

    private String multiPartFileToBase64(MultipartFile file) throws IOException {
        Binary binary = new Binary(BsonBinarySubType.BINARY, file.getBytes());
        return new String(Base64.getEncoder().encode(binary.getData()));
    }
}
