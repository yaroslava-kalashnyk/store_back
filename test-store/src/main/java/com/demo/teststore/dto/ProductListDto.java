package com.demo.teststore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductListDto {
    private List<ProductDto> productList;
    private long totalAmount;
}
