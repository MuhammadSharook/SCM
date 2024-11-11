package com.example.demo.Transformer;

import com.example.demo.DTO.Response.CartResponse;
import com.example.demo.DTO.Response.ProductItemResponse;
import com.example.demo.Model.Cart;
import com.example.demo.Model.ProductItem;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {

    public static CartResponse fromCartTOCartResponse(Cart cart) {
        List<ProductItemResponse> productItemResponseList = new ArrayList<>();

        for (ProductItem productItem : cart.getProductItems()) {
            productItemResponseList.add(ProductItemTransformer.fromProductItemTOProductItemResponse(productItem));
        }

        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .productItems(productItemResponseList)
                .build();
    }
}
