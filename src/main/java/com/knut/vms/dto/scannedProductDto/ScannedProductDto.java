package com.knut.vms.dto.scannedProductDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScannedProductDto {

    private Long productId;
    private Integer quantity;
}
