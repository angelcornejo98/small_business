package com.knut.vms.controller.sales;

import com.knut.vms.dto.saleRequestDto.SaleRequestDto;
import com.knut.vms.dto.saleResponseDto.SaleResponseDto;
import com.knut.vms.service.saleService.SaleService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@AllArgsConstructor
@CrossOrigin("*") // 👈 if you need frontend access
public class SalesController {

    private SaleService saleService;

    // =========================
    // CREATE SALE
    // =========================
    @PostMapping
    public ResponseEntity<SaleResponseDto> registerSale(
            @RequestBody SaleRequestDto request) {

        return ResponseEntity.ok(
                saleService.registerSale(request)
        );
    }

    // =========================
    // GET ALL SALES
    // =========================
    @GetMapping
    public ResponseEntity<List<SaleResponseDto>> getAllSales() {

        return ResponseEntity.ok(
                saleService.getAllSales() // ✅ correct call
        );
    }
}