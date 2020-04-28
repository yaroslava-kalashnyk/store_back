package com.demo.teststore.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;

@Data
public class CreateProductDto {

  private String name;
  private BigDecimal price;
  private String category;
  private MultipartFile image;
}
