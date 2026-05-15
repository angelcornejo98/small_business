package com.knut.vms.mapper.Product;

import com.knut.vms.dto.product.ProductDto;
import com.knut.vms.entity.category.Category;
import com.knut.vms.entity.product.Product;
import com.knut.vms.entity.supplier.Supplier;

public class ProductMapper {

    public static ProductDto mapToProductDto(Product product) {

        ProductDto productDto = new ProductDto();

        // Filling the non-relation fields
        productDto.setId(product.getId());
        productDto.setName(product.getProductName());
        productDto.setBarcode(product.getBarcode());
        productDto.setUnitPrice(product.getUnitPrice());
        productDto.setStock(product.getStock());

        // Filling the fields with Relationships
        Category category = product.getCategory();
        Supplier supplier = product.getSupplier();

        if (category != null) {
            productDto.setCategoryId(category.getId());
            productDto.setCategoryName(category.getName());
        }

        if (supplier != null) {
            productDto.setSupplierId(supplier.getId());
            productDto.setSupplierName(supplier.getName());
        }

        return productDto;
    }

    public static Product mapToProduct(ProductDto productDto, Category category, Supplier supplier) {

        Product product = new Product();

        product.setId(productDto.getId());
        product.setProductName(productDto.getName());
        product.setBarcode(productDto.getBarcode());
        product.setUnitPrice(productDto.getUnitPrice());
        product.setStock(productDto.getStock());

        product.setCategory(category);
        product.setSupplier(supplier);

        return product;
    }
}
