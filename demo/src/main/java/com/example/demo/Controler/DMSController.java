package com.example.demo.Controler;

import com.example.demo.DTO.Request.ProductRequest;
import com.example.demo.DTO.Request.ReturnItemRequest;
import com.example.demo.DTO.Request.WarehouseRequest;
import com.example.demo.DTO.Response.ProductItemResponse;
import com.example.demo.DTO.Response.WarehouseResponse;
import com.example.demo.Service.InventoryService;
import com.example.demo.Service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dms")
public class DMSController {

    private final InventoryService inventoryService;
    private final WarehouseService warehouseService;

    @Autowired
    public DMSController(InventoryService inventoryService,
                         WarehouseService warehouseService) {
        this.inventoryService = inventoryService;
        this.warehouseService = warehouseService;
    }

    @PostMapping("/add/product")
    public ResponseEntity addProduct(@RequestBody ProductRequest productRequest, @RequestParam int warehouseNo) {
        ProductItemResponse response = inventoryService.addProduct(productRequest, warehouseNo);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-product/{productId}")
    public ResponseEntity removeProduct(@PathVariable("int productId") int productId) {

        try {
            String response = inventoryService.removeProduct(productId);
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-quantity/product/{productId}")
    public ResponseEntity updateProductQuantity(@PathVariable("productId") int productId, @RequestParam int quantity) {
        try {
            ProductItemResponse response = inventoryService.updateProductQuantity(productId, quantity);
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("check/product/stock")
    public ResponseEntity checkReorder() {
        List<ProductItemResponse> response = inventoryService.checkReorder();

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/add/warehouse")
    public ResponseEntity addWarehouse(@RequestBody WarehouseRequest warehouseRequest) {

        WarehouseResponse response = warehouseService.addWarehouse(warehouseRequest);

        return new ResponseEntity(response, HttpStatus.CREATED);

    }

    @GetMapping("/find/warehouse/{warehouseNo}")
    public ResponseEntity getWarehouse(@PathVariable("warehouseNo") int warehouseNo) {

        try {
            WarehouseResponse response = warehouseService.getWarehouse(warehouseNo);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/handle-returns/warehouse/{warehouseNo}")
    public ResponseEntity handleReturn(@PathVariable("warehouseNo") int warehouseNo, List<ReturnItemRequest> returnItemRequestList) {
        try {
            String response = warehouseService.handleReturn(warehouseNo, returnItemRequestList);
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
