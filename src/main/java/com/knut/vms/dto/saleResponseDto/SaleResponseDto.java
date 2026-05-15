package com.knut.vms.dto.saleResponseDto;

import com.knut.vms.dto.SaleItemResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponseDto {

    private Long saleId;
    private BigDecimal total;
    private LocalDateTime createdAt;

    private List<SaleItemResponseDto> items;
}
