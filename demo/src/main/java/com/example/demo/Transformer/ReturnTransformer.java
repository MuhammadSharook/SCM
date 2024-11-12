package com.example.demo.Transformer;

import com.example.demo.DTO.Request.ReturnItemRequest;
import com.example.demo.Model.ReturnItems;

public class ReturnTransformer {

    public static ReturnItems fromReturnItemRequestToReturnItem(ReturnItemRequest returnItemRequest){
        return ReturnItems.builder()
                .quantity(returnItemRequest.getQuantity())
                .productId(returnItemRequest.getProductId())
                .build();
    }

}
