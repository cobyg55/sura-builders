package com.sura.builders.common.mapper;

import com.sura.builders.common.request.InventoryRequest;
import com.sura.builders.common.response.InventoryResponse;
import com.sura.builders.domain.model.Inventory;
import com.sura.builders.domain.model.Product;

import java.util.Date;

public class InventoryMapper {

    public static InventoryResponse mapToInventoryResponse(Inventory inventory) {
        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setId(inventory.getId());
        inventoryResponse.setQuantity(inventory.getQuantity());
        inventoryResponse.setUnitCost(inventory.getUnitCost());
        inventoryResponse.setTotalCost(inventory.getTotalCost());
        inventoryResponse.setCreatedAt(inventory.getCreatedAt());
        return inventoryResponse;
    }

    public static Inventory mapToInventory(InventoryRequest inventoryRequest, Product product) {
        Inventory inventory = new Inventory();
        inventory.setQuantity(inventoryRequest.getQuantity());
        inventory.setUnitCost(inventoryRequest.getUnitCost());
        inventory.setTotalCost(inventoryRequest.getUnitCost() * inventoryRequest.getQuantity());
        inventory.setProduct(product);
        inventory.setCreatedAt(new Date());
        return inventory;
    }

}
