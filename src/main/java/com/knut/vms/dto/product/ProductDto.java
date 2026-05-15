package com.knut.vms.dto.product;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String barcode;
    private BigDecimal unitPrice;
    private Integer stock;

    // Added fields
    private Long categoryId;
    private String categoryName;

    private Long supplierId;
    private String supplierName;
}
