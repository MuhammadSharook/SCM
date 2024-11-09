package com.example.demo.Transformer;

import com.example.demo.DTO.Request.ProductRequest;
import com.example.demo.DTO.Response.ProductItemResponse;
import com.example.demo.Model.ProductItem;

public class ProductItemTransformer {

    public static ProductItemResponse fromProductItemTOProductItemResponse(ProductItem productItem)
    {
        return ProductItemResponse.builder()
                .productName(productItem.getProductName())
                .discription(productItem.getDiscription())
                .requiredQuantity(productItem.getRequiredQuantity())
                .price(productItem.getPrice())
                .build();
    }

    public static ProductItem fromProductRequestTOProductItem(ProductRequest productRequest){
        return ProductItem.builder()
                .productName(productRequest.getProductName())
                .discription(productRequest.getDiscription())
                .price(productRequest.getPrice())
                .category(productRequest.getCategory())
                .requiredQuantity(productRequest.getRequiredQuantity())
                .build();
    }
}
