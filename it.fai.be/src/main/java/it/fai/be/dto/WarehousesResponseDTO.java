package it.fai.be.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WarehousesResponseDTO extends ResponseDto{
    private List<WarehouseDTO> warehouses;
}
