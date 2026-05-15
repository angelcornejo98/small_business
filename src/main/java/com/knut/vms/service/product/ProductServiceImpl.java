package com.knut.vms.service.product;

import com.knut.vms.dto.product.ProductDto;
import com.knut.vms.entity.category.Category;
import com.knut.vms.entity.product.Product;
import com.knut.vms.entity.supplier.Supplier;
import com.knut.vms.exception.ResourceNotFoundException;
import com.knut.vms.mapper.Product.ProductMapper;
import com.knut.vms.repository.category.CategoryRepository;
import com.knut.vms.repository.product.ProductRepository;
import com.knut.vms.repository.supplier.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        // Look relationship from the DB
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow( () ->
                        new ResourceNotFoundException("Category not found"));

        // Look for relationship from the DB
        Supplier supplier = supplierRepository.findById(productDto.getSupplierId())
                .orElseThrow( () ->
                        new ResourceNotFoundException(("Supplier not found"))
                );

        // Map DTO to Entity
        Product product = ProductMapper.mapToProduct(productDto, category, supplier);

        // Save the product
        Product savedProduct = productRepository.save(product);


        return ProductMapper.mapToProductDto(savedProduct);
    }

    @Override
    public ProductDto getProductById(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Supplier doesn't exist with the given id: " + productId)
                );

        return ProductMapper.mapToProductDto(product);
    }

    @Override
    public ProductDto getByBarcode(String barcode) {

        Product product = productRepository.findByBarcode(barcode)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        return ProductMapper.mapToProductDto(product);
    }


    // Get All Products
    @Override
    public List<ProductDto> getAllProducts() {

        List<Product> products = productRepository.findAll();

        return products.stream().map((product) -> ProductMapper.mapToProductDto(product))
                .collect(Collectors.toList());
    }

    // Update Product
    @Override
    public ProductDto updateProduct(Long productId, ProductDto updatedProduct) {

        Product product = productRepository.findById(productId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product doesn't exist with given id: " + productId)
                );

        product.setBarcode(updatedProduct.getBarcode());
        product.setProductName(updatedProduct.getName());
        product.setStock(updatedProduct.getStock());
        product.setUnitPrice(updatedProduct.getUnitPrice());



            Category category = categoryRepository.findById(updatedProduct.getCategoryId())
                    .orElseThrow( () ->
                            new ResourceNotFoundException("Category not found with id: " + updatedProduct.getCategoryId())
                            );
            product.setCategory(category);


            Supplier supplier = supplierRepository.findById(updatedProduct.getSupplierId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Supplier not found with id: "+ updatedProduct.getSupplierId())
                    );
            product.setSupplier((supplier));


        Product updated = productRepository.save(product);

        return ProductMapper.mapToProductDto(updated);
    }

    // Update some fields of product
    @Override
    public ProductDto partialUpdateProduct(Long productId, ProductDto productDto) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId)
                );

        // Non-null fields
        if (productDto.getName() != null) {
            product.setProductName(productDto.getName());
        }

        if (productDto.getBarcode() != null) {
            product.setBarcode(productDto.getBarcode());
        }

        if (productDto.getUnitPrice() != null) {
            product.setUnitPrice(productDto.getUnitPrice());
        }

        if(productDto.getStock() != null) {
            product.setStock(productDto.getStock());
        }

        // Relationships

        if (productDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDto.getCategoryId())
                    .orElseThrow(()  -> new ResourceNotFoundException(
                            "Category not found with id: " + productDto.getCategoryId()
            ));

            product.setCategory(category);
        }

        if (productDto.getSupplierId() != null) {
            Supplier supplier = supplierRepository.findById(productDto.getSupplierId())
                    .orElseThrow(()  -> new ResourceNotFoundException(
                            "Supplier not found with id: " + productDto.getSupplierId()
                    ));

            product.setSupplier(supplier);
        }

        Product saved = productRepository.save(product);

        return ProductMapper.mapToProductDto(saved);
    }

    // Delete a product
    @Override
    public void deleteProduct(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product doesn't exist with the given id: " + productId)
                );

        productRepository.deleteById(productId);
    }


}
