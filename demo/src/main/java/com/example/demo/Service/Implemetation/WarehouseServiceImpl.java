package com.example.demo.Service.Implemetation;

import com.example.demo.DTO.Request.ReturnItemRequest;
import com.example.demo.DTO.Request.WarehouseRequest;
import com.example.demo.DTO.Response.WarehouseResponse;
import com.example.demo.Exception.WarehouseUnavailable;
import com.example.demo.Model.ProductItem;
import com.example.demo.Model.Return;
import com.example.demo.Model.ReturnItems;
import com.example.demo.Model.Warehouse;
import com.example.demo.Repository.WarehouseRepository;
import com.example.demo.Service.WarehouseService;
import com.example.demo.Transformer.ReturnTransformer;
import com.example.demo.Transformer.WarehouseTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public WarehouseResponse addWarehouse(WarehouseRequest warehouseRequest) {
        Warehouse warehouse = WarehouseTransformer.fromWarehouseRequestTOWarehouse(warehouseRequest);

        warehouse.setInventory(new ArrayList<>());
        warehouse.setOrderHistory(new ArrayList<>());
        warehouse.setReturnHistory(new ArrayList<>());

        Warehouse savedWarehouse = warehouseRepository.save(warehouse);

        return WarehouseTransformer.fromWarehouseTOWarehouseResponse(savedWarehouse);
    }

    @Override
    public WarehouseResponse getWarehouse(int warehouseNo) {

        Warehouse warehouse = warehouseRepository.findByWarehouseNo(warehouseNo);

        if (warehouse == null) {
            throw new WarehouseUnavailable("Warehouse unavailable.");
        }

        return WarehouseTransformer.fromWarehouseTOWarehouseResponse(warehouse);
    }

    @Override
    public String handleReturn(int warehouseNo, List<ReturnItemRequest> returnItemRequestList) {
        Warehouse warehouse = warehouseRepository.findByWarehouseNo(warehouseNo);

        if (warehouse == null) {
            throw new WarehouseUnavailable("Warehouse not found.");
        }

        List<ReturnItems> returnItems = new ArrayList<>();
        for (ReturnItemRequest returnItemRequest : returnItemRequestList) {
            returnItems.add(ReturnTransformer.fromReturnItemRequestToReturnItem(returnItemRequest));
        }


        Return newReturn = Return.builder()
                .warehouse(warehouse)
                .returnDate(LocalDateTime.now())
                .returnItems(returnItems)
                .build();

        warehouse.getReturnHistory().add(newReturn);


        for (ReturnItems R : returnItems) {

            if (warehouse.getInventory().contains(R.getProductId())) {
                ProductItem product = warehouse.getInventory().get(R.getProductId());
                product.setQuantity(product.getQuantity() + R.getQuantity());
            } else {
                ProductItem newProduct = ProductItem.builder()
                        .productName("Unknown")
                        .price(0.0)
                        .discription("Unknown")
                        .quantity(R.getQuantity())
                        .reorderPoint(0)
                        .build();

                warehouse.getInventory().add(newProduct);
            }
        }
        return "Successfully handled.";
    }
}
