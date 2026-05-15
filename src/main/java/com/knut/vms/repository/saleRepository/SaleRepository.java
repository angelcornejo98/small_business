package com.knut.vms.repository.saleRepository;

import com.knut.vms.entity.sale.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,Long> {
}
