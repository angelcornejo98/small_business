package com.knut.vms.dto.saleRequestDto;

import com.knut.vms.dto.scannedProductDto.ScannedProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleRequestDto {

    private List<ScannedProductDto> items;
}
