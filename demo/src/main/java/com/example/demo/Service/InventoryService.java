package com.example.demo.Service;

import com.example.demo.DTO.Request.ProductRequest;
import com.example.demo.DTO.Response.ProductItemResponse;

import java.util.List;

public interface InventoryService {
    ProductItemResponse addProduct(ProductRequest productRequest);

    String removeProduct(int productId);

    ProductItemResponse updateProductQuantity(int productId,int quantity);

    List<ProductItemResponse> checkReorder();
}
