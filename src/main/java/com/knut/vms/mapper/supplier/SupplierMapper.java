package com.knut.vms.mapper.supplier;

import com.knut.vms.dto.supplier.SupplierDto;
import com.knut.vms.entity.supplier.Supplier;

public class SupplierMapper {

    public static SupplierDto mapToSupplierDto(Supplier supplier) {

        SupplierDto supplierDto = new SupplierDto();

        supplierDto.setId(supplier.getId());
        supplierDto.setName(supplier.getName());
        supplierDto.setPhone(supplier.getPhone());

        return  supplierDto;
    }

    public static Supplier mapToSupplier(SupplierDto supplierDto) {

        Supplier supplier = new Supplier();

        supplier.setId(supplierDto.getId());
        supplier.setName(supplierDto.getName());
        supplier.setPhone(supplierDto.getPhone());

        return supplier;
    }
}
