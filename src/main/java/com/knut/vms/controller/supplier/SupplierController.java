package com.knut.vms.controller.supplier;

import com.knut.vms.dto.supplier.SupplierDto;
import com.knut.vms.service.supplier.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("api/suppliers")
public class SupplierController {

    private SupplierService supplierService;

    // Build "Add Supplier REST API"
    @PostMapping
    public ResponseEntity<SupplierDto> createSupplier(@RequestBody SupplierDto supplierDto) {
        SupplierDto savedSupplier = supplierService.createSupplier(supplierDto);

        return new ResponseEntity<>(savedSupplier, HttpStatus.CREATED);
    }

    // Build "Get Supplier REST API"
    @GetMapping("{id}")
    public ResponseEntity<SupplierDto> getSupplierById(@PathVariable("id") Long supplierId) {

        SupplierDto supplierDto = supplierService.getSupplierById(supplierId);

        return ResponseEntity.ok(supplierDto);
    }

    // Build GET ALL Suppliers REST API
    @GetMapping
    public ResponseEntity<List<SupplierDto>> getAllSuppliers() {

        List<SupplierDto> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    // Build UPDATE Suppliers REST API
    @PutMapping("{id}")
    public ResponseEntity<SupplierDto> updateSupplier(@PathVariable("id") Long supplierId,
                                                      @RequestBody SupplierDto updatedSupplier){

        SupplierDto supplierDto = supplierService.updateSupplier(supplierId, updatedSupplier);

        return ResponseEntity.ok(supplierDto);
    }

    // Build PARTIAL UPDATE Products REST API
    @PatchMapping("/{id}")
    public ResponseEntity<SupplierDto> partialUpdate(@PathVariable("id") Long supplierId,
                                                    @RequestBody SupplierDto supplierDto) {

        SupplierDto updated = supplierService.partialUpdateSupplier(supplierId, supplierDto);

        return ResponseEntity.ok(updated);
    }

    // Build Delete Categories REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable("id") Long supplierId) {

        supplierService.deleteSupplier(supplierId);
        return ResponseEntity.ok("Supplier deleted successfully");
    }

}
