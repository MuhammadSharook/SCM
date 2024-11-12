package com.example.demo.Service.Implemetation;

import com.example.demo.DTO.Request.ProductRequest;
import com.example.demo.DTO.Response.ProductItemResponse;
import com.example.demo.Exception.ProductUnavailableException;
import com.example.demo.Model.ProductItem;
import com.example.demo.Repository.InventoryRepository;
import com.example.demo.Service.InventoryService;
import com.example.demo.Transformer.ProductItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public ProductItemResponse addProduct(ProductRequest productRequest) {
        ProductItem productItem = ProductItemTransformer.fromProductRequestTOProductItem(productRequest);

        ProductItem savedProductItem = inventoryRepository.save(productItem);

        return ProductItemTransformer.fromProductItemTOProductItemResponse(savedProductItem);
    }

    @Override
    public String removeProduct(int productId) {
        Optional<ProductItem> optionalProductItem = inventoryRepository.findById(productId);

        if(optionalProductItem.isEmpty()){
            throw new ProductUnavailableException("Product unavailable.");
        }

        ProductItem productItem = optionalProductItem.get();

        inventoryRepository.delete(productItem);

        return "Successfully deleted.";
    }

    @Override
    public ProductItemResponse updateProductQuantity(int productId,int quantity) {
        Optional<ProductItem> optionalProductItem = inventoryRepository.findById(productId);

        if(optionalProductItem.isEmpty()){
            throw new ProductUnavailableException("Product unavailable.");
        }

        ProductItem productItem = optionalProductItem.get();

        productItem.setQuantity(quantity);

        ProductItem savedProduct = inventoryRepository.save(productItem);

        return ProductItemTransformer.fromProductItemTOProductItemResponse(savedProduct);

    }

    @Override
    public List<ProductItemResponse> checkReorder() {

        List<ProductItem> productLists = new ArrayList<>();

        for (ProductItem productItem : inventoryRepository.findAll()){
            if(productItem.getQuantity() <= productItem.getReorderPoint()){
                productLists.add(productItem);
            }
        }

        return ProductItemTransformer.fromListProductItemTOListProductItemResponse(productLists);
    }


}
