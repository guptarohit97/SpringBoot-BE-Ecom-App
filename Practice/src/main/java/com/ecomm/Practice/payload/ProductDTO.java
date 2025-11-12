package com.ecomm.Practice.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductDTO {
    private Long productId;
    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 3, message = "Product name must contain at least 3 characters")
    private  String productName;
    private String image;
    @NotBlank(message = "Description cannot be blank")
    @Size(min = 6, message = "Product description must contain at least 6 characters")
    private String description;
    private Integer quantity;
    private double price;
    private double discount;
    private double specialPrice;

}
