package com.knut.vms.service.supplier;

import com.knut.vms.dto.supplier.SupplierDto;
import com.knut.vms.entity.product.Product;
import com.knut.vms.entity.supplier.Supplier;
import com.knut.vms.exception.ResourceNotFoundException;
import com.knut.vms.mapper.supplier.SupplierMapper;
import com.knut.vms.repository.product.ProductRepository;
import com.knut.vms.repository.supplier.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;
    private ProductRepository productRepository;

    @Override
    public SupplierDto createSupplier(SupplierDto supplierDto) {

        Supplier supplier = SupplierMapper.mapToSupplier(supplierDto);
        Supplier savedSupplier = supplierRepository.save(supplier);

        return SupplierMapper.mapToSupplierDto(savedSupplier);
    }

    @Override
    public SupplierDto getSupplierById(Long supplierId) {

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Supplier doesn't exist with the given id: " + supplierId)
                );

        return SupplierMapper.mapToSupplierDto(supplier);
    }

    // Get All Suppliers
    @Override
    public List<SupplierDto> getAllSuppliers() {

        List<Supplier> suppliers = supplierRepository.findAll();

        return suppliers.stream().map(SupplierMapper::mapToSupplierDto).toList();
    }


    // UPDATE Supplier
    @Override
    public SupplierDto updateSupplier(Long supplierId, SupplierDto updatedSupplier) {

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(
                () -> new ResourceNotFoundException("Supplier doesn't exist with given id: " + supplierId)
        );

        supplier.setName(updatedSupplier.getName());

        Supplier updatedSupplierObj = supplierRepository.save(supplier);

        return SupplierMapper.mapToSupplierDto(updatedSupplierObj);
    }

    // Partial Update Supplier
    @Override
    public SupplierDto partialUpdateSupplier(Long supplierId, SupplierDto supplierDto) {

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + supplierId)
                );

        // Non-null fields
        if (supplierDto.getName() != null) {
            supplier.setName(supplierDto.getName());
        }

        if (supplierDto.getPhone() != null) {
            supplier.setPhone(supplierDto.getPhone());
        }

        Supplier saved = supplierRepository.save(supplier);

        return SupplierMapper.mapToSupplierDto(saved);
    }

    // Delete a supplier
    @Override
    public void deleteSupplier(Long supplierId) {


        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow( () -> new ResourceNotFoundException("Supplier doesn't exist with the given id: " + supplierId)
                        );

        // if (productRepository.existsBySupplierId(supplierId)) {
        //     throw new RuntimeException("Cannot delete supplier with products");
        // }

        List<Product> products = productRepository.findBySupplierId(supplierId);

        for(Product product : products) {
            product.setSupplier(null);
        }

        productRepository.saveAll(products);

        supplierRepository.delete(supplier);
    }
}