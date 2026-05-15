package com.knut.vms.repository.saleItemRepository;

import com.knut.vms.entity.saleItem.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem,Long> {
}
