package com.knut.vms.controller.product;

import com.knut.vms.dto.product.ProductDto;
import com.knut.vms.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    // Build "Add Product REST API"
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto savedProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }


    // Build "Get Product REST API"
    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {

        ProductDto productDto = productService.getProductById(productId);

        return ResponseEntity.ok(productDto);
    }

    // Build GET ALL Products REST API
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProduct() {

        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Built Get with Barcode
    @GetMapping("/barcode/{code}")
    public ResponseEntity<ProductDto> getByBarcode(@PathVariable String code) {

        ProductDto productDto = productService.getByBarcode(code);

        return ResponseEntity.ok(productDto);

    }



    // Build UPDATE Products REST API
    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long productId,
                                                       @RequestBody ProductDto updatedProduct){

        ProductDto productDto = productService.updateProduct(productId, updatedProduct);

        return ResponseEntity.ok(productDto);
    }

    // Build PARTIAL UPDATE Products REST API
    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> partialUpdate(@PathVariable("id") Long productId,
                                                    @RequestBody ProductDto productDto) {

        ProductDto updated = productService.partialUpdateProduct(productId, productDto);

        return ResponseEntity.ok(updated);
    }


    // Build Delete Products REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId) {

        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully");
    }
}