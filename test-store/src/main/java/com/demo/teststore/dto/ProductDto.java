package com.demo.teststore.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class ProductDto {

    private BigInteger id;
    private String name;
    private BigDecimal price;
    private String imageInBase64;
}
