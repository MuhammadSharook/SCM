package com.example.demo.Transformer;

import com.example.demo.DTO.Request.WarehouseRequest;
import com.example.demo.DTO.Response.WarehouseResponse;
import com.example.demo.Model.Warehouse;

import static ch.qos.logback.classic.spi.ThrowableProxyVO.build;

public class WarehouseTransformer {

    public static Warehouse fromWarehouseRequestTOWarehouse(WarehouseRequest warehouseRequest){
        return Warehouse.builder()
                .warehouseNo(warehouseRequest.getWarehouseNo())
                .warehouseName(warehouseRequest.getWarehouseName())
                .build();
    }

    public static WarehouseResponse fromWarehouseTOWarehouseResponse(Warehouse warehouse){
        return WarehouseResponse.builder()
                .warehouseNo(warehouse.getWarehouseNo())
                .warehouseName(warehouse.getWarehouseName())
                .productItemResponseList(ProductItemTransformer.fromListProductItemTOListProductItemResponse(warehouse.getInventory()))
                .build();

    }
}
