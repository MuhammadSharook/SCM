package com.example.demo.Service.Implemetation;

import com.example.demo.DTO.Request.ProductRequest;
import com.example.demo.DTO.Response.ProductItemResponse;
import com.example.demo.Exception.ProductUnavailableException;
import com.example.demo.Model.ProductItem;
import com.example.demo.Model.Warehouse;
import com.example.demo.Repository.InventoryRepository;
import com.example.demo.Repository.WarehouseRepository;
import com.example.demo.Service.InventoryService;
import com.example.demo.Transformer.ProductItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final WarehouseRepository warehouseRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository,
                                WarehouseRepository warehouseRepository) {
        this.inventoryRepository = inventoryRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public ProductItemResponse addProduct(ProductRequest productRequest, int warehouseNo) {
        ProductItem productItem = ProductItemTransformer.fromProductRequestTOProductItem(productRequest);

        Warehouse warehouse = warehouseRepository.findByWarehouseNo(warehouseNo);
        warehouse.getInventory().add(productItem);
        warehouseRepository.save(warehouse);

        ProductItem savedProductItem = inventoryRepository.save(productItem);

        return ProductItemTransformer.fromProductItemTOProductItemResponse(savedProductItem);
    }

    @Override
    public String removeProduct(int productId) {
        Optional<ProductItem> optionalProductItem = inventoryRepository.findById(productId);

        if (optionalProductItem.isEmpty()) {
            throw new ProductUnavailableException("Product unavailable.");
        }

        ProductItem productItem = optionalProductItem.get();

        Warehouse warehouse = warehouseRepository.findByWarehouseNo(productItem.getWarehouse().getWarehouseNo());
        warehouse.getInventory().remove(productItem);

        warehouseRepository.save(warehouse);
        inventoryRepository.delete(productItem);

        return "Successfully deleted.";
    }

    @Override
    public ProductItemResponse updateProductQuantity(int productId, int quantity) {
        Optional<ProductItem> optionalProductItem = inventoryRepository.findById(productId);

        if (optionalProductItem.isEmpty()) {
            throw new ProductUnavailableException("Product unavailable.");
        }

        ProductItem productItem = optionalProductItem.get();

        Warehouse warehouse = warehouseRepository.findByWarehouseNo(productItem.getWarehouse().getWarehouseNo());

        for (ProductItem p : warehouse.getInventory()) {
            if (p.equals(productItem)) {
                p.setQuantity(quantity);
            }
        }

        productItem.setQuantity(quantity);

        warehouseRepository.save(warehouse);


        ProductItem savedProduct = inventoryRepository.save(productItem);

        return ProductItemTransformer.fromProductItemTOProductItemResponse(savedProduct);

    }

    @Override
    public List<ProductItemResponse> checkReorder() {

        List<ProductItem> productLists = new ArrayList<>();

        for (ProductItem productItem : inventoryRepository.findAll()) {
            if (productItem.getQuantity() <= productItem.getReorderPoint()) {
                productLists.add(productItem);
            }
        }

        return ProductItemTransformer.fromListProductItemTOListProductItemResponse(productLists);
    }


}
