package com.knut.vms.service.product;

import com.knut.vms.dto.product.ProductDto;

import java.util.List;

public interface ProductService {

    // Create a product
    ProductDto createProduct(ProductDto productDto);

    // Find a product
    ProductDto getProductById(Long productId);

    //Find a product by the BarCode
    ProductDto getByBarcode(String barcode);

    // Find ALL Products
    List<ProductDto> getAllProducts();

    // Update a supplier
    ProductDto updateProduct(Long productId, ProductDto updatedProduct);

    // Update some Fields of a product
    ProductDto partialUpdateProduct(Long productId, ProductDto productDto);

    // Delete a product
    void deleteProduct(Long productId);
}