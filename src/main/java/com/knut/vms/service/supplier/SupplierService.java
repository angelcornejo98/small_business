package com.knut.vms.service.supplier;



import com.knut.vms.dto.supplier.SupplierDto;

import java.util.List;

public interface SupplierService {

    // Create a supplier
    SupplierDto createSupplier(SupplierDto supplierDto);

    // Find a supplier
    SupplierDto getSupplierById(Long supplierId);

    // Find all suppliers
    List<SupplierDto> getAllSuppliers();

    // Update a supplier
    SupplierDto updateSupplier(Long supplierId, SupplierDto updatedSupplier);

    // Update some fields of a supplier
    SupplierDto partialUpdateSupplier(Long supplierId, SupplierDto supplierDto);

    // Delete a category
    void deleteSupplier(Long supplierId);
}
