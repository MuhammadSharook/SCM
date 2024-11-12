package com.example.demo.Service;

import com.example.demo.DTO.Request.ReturnItemRequest;
import com.example.demo.DTO.Request.WarehouseRequest;
import com.example.demo.DTO.Response.WarehouseResponse;

import java.util.List;

public interface WarehouseService {
    WarehouseResponse addWarehouse(WarehouseRequest warehouseRequest);

    WarehouseResponse getWarehouse(int warehouseNo);

    String handleReturn(int warehouseNo, List<ReturnItemRequest> returnItemRequestList);
}
