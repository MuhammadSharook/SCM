package com.example.demo.Transformer;

import com.example.demo.DTO.Request.ProductRequest;
import com.example.demo.DTO.Response.ProductItemResponse;
import com.example.demo.Model.ProductItem;

import java.util.ArrayList;
import java.util.List;

public class ProductItemTransformer {

    public static ProductItemResponse fromProductItemTOProductItemResponse(ProductItem productItem) {
        return ProductItemResponse.builder()
                .productName(productItem.getProductName())
                .discription(productItem.getDiscription())
                .requiredQuantity(productItem.getQuantity())
                .price(productItem.getPrice())
                .build();
    }

    public static ProductItem fromProductRequestTOProductItem(ProductRequest productRequest) {
        return ProductItem.builder()
                .productName(productRequest.getProductName())
                .discription(productRequest.getDiscription())
                .price(productRequest.getPrice())
                .category(productRequest.getCategory())
                .quantity(productRequest.getRequiredQuantity())
                .build();
    }

    public static List<ProductItemResponse> fromListProductItemTOListProductItemResponse(List<ProductItem> productItemList) {
        List<ProductItemResponse> responseList = new ArrayList<>();

        for (ProductItem productItem : productItemList) {
            responseList.add(fromProductItemTOProductItemResponse(productItem));
        }

        return responseList;
    }
}
