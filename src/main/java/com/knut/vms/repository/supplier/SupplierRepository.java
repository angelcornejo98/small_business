package com.knut.vms.repository.supplier;

import com.knut.vms.entity.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
