package com.knut.vms.service.saleService;

import com.knut.vms.dto.SaleItemResponseDto;
import com.knut.vms.dto.saleRequestDto.SaleRequestDto;
import com.knut.vms.dto.saleResponseDto.SaleResponseDto;
import com.knut.vms.dto.scannedProductDto.ScannedProductDto;
import com.knut.vms.entity.product.Product;
import com.knut.vms.entity.sale.Sale;
import com.knut.vms.entity.saleItem.SaleItem;
import com.knut.vms.exception.ResourceNotFoundException;
import com.knut.vms.repository.product.ProductRepository;
import com.knut.vms.repository.saleItemRepository.SaleItemRepository;
import com.knut.vms.repository.saleRepository.SaleRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SaleService {

    private ProductRepository productRepository;
    private SaleRepository saleRepository;
    private SaleItemRepository saleItemRepository;

    // =========================
    // CREATE SALE
    // =========================
    @Transactional
    public SaleResponseDto registerSale(SaleRequestDto request) {

        Sale sale = new Sale();
        sale.setCreatedAt(LocalDateTime.now());
        sale.setTotalAmount(BigDecimal.ZERO);

        sale = saleRepository.save(sale);

        BigDecimal total = BigDecimal.ZERO;
        List<SaleItem> items = new ArrayList<>();

        for (ScannedProductDto item : request.getItems()) {

            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            // Check stock
            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Not enough stock for: " + product.getProductName());
            }

            // Reduce stock
            product.setStock(product.getStock() - item.getQuantity());

            // Create sale item
            SaleItem saleItem = new SaleItem();
            saleItem.setSale(sale);
            saleItem.setProduct(product);
            saleItem.setQuantity(item.getQuantity());
            saleItem.setUnitPrice(product.getUnitPrice());

            saleItemRepository.save(saleItem);

            items.add(saleItem);

            // Add to total
            total = total.add(
                    product.getUnitPrice()
                            .multiply(BigDecimal.valueOf(item.getQuantity()))
            );
        }

        sale.setItems(items);
        sale.setTotalAmount(total);

        saleRepository.save(sale);

        return mapToResponse(sale);
    }

    // =========================
    // GET ALL SALES
    // =========================
    public List<SaleResponseDto> getAllSales() {
        return saleRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // =========================
    // MAPPER
    // =========================
    private SaleResponseDto mapToResponse(Sale sale) {

        List<SaleItemResponseDto> items = sale.getItems()
                .stream()
                .map(item -> {

                    Product product = item.getProduct();

                    BigDecimal subtotal = item.getUnitPrice()
                            .multiply(BigDecimal.valueOf(item.getQuantity()));

                    return new SaleItemResponseDto(
                            product.getId(),
                            product.getProductName(),
                            product.getCategory() != null ? product.getCategory().getName() : "Sin categoría",
                            item.getQuantity(),
                            item.getUnitPrice(),
                            subtotal
                    );
                })
                .toList();

        return new SaleResponseDto(
                sale.getId(),
                sale.getTotalAmount(),
                sale.getCreatedAt(),
                items
        );
    }
}