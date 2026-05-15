package com.knut.vms.repository.product;

import com.knut.vms.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findBySupplierId(Long supplierId);
    List<Product> findByCategoryId(Long categoryId);
    Optional<Product> findByBarcode(String barcode);
}

