package com.example.demo.Controler;

import com.example.demo.DTO.Request.ProductRequest;
import com.example.demo.DTO.Response.ProductItemResponse;
import com.example.demo.Exception.ProductUnavailableException;
import com.example.demo.Model.ProductItem;
import com.example.demo.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dms")
public class DMSController {

    private final InventoryService inventoryService;

    @Autowired
    public DMSController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/add/product")
    public ResponseEntity addProduct(@RequestBody ProductRequest productRequest){
        ProductItemResponse response = inventoryService.addProduct(productRequest);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-product/{productId}")
    public ResponseEntity removeProduct(@PathVariable("int productId")int productId){

        try {
            String response = inventoryService.removeProduct(productId);
            return new ResponseEntity(response,HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-quantity/product/{productId}")
    public ResponseEntity updateProductQuantity(@PathVariable("productId")int productId,@RequestParam int quantity){
        try {
            ProductItemResponse response = inventoryService.updateProductQuantity(productId,quantity);
            return new ResponseEntity(response,HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("check/product/stock")
    public ResponseEntity checkReorder(){
        List<ProductItemResponse> response = inventoryService.checkReorder();

        return new ResponseEntity(response,HttpStatus.OK);
    }
}
